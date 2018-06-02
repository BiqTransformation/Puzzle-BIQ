package course.puzzle.puzzle;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.Callable;

import static course.puzzle.puzzle.PuzzlePiece.JOKER;

/**
 * @author Svetlana
 * Class RunSolution execute recursion to solve the puzzle with specific number of rows and columnes
 * The class is Callable to return the solved  puzzle board
 */
public class RunSolution implements Callable {

    private int rows;
    private int cols;
    private long TIMEOUT_MILLISECONDS;
    private ThreadLocal<Long> start = ThreadLocal.withInitial(() -> System.nanoTime());
    private ThreadLocal<Boolean> isTimeout = ThreadLocal.withInitial(() -> false);

    private PuzzleShape toSearch = new PuzzleShape(new int[]{JOKER, JOKER, JOKER, JOKER});
    private PuzzlePiece[][] solvedPuzzle = new PuzzlePiece[][]{};
    private List<PuzzlePiece> puzzlePieces;
    private Stack<Integer> piecesUsed;
    private Map<PuzzleShape, List<PuzzlePiece>> puzzleShapeListMap;
    private Puzzle puzzle;


    public RunSolution(Puzzle puzzle, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.puzzle = puzzle;
        puzzlePieces = puzzle.getPuzzle();
        puzzleShapeListMap = puzzle.indexer();
        initPuzzle(rows, cols);
    }

    public RunSolution(Puzzle puzzle, int rows, int cols, long timeout) {
        this.rows = rows;
        this.cols = cols;
        TIMEOUT_MILLISECONDS = timeout;
        puzzlePieces = puzzle.getPuzzle();
        puzzleShapeListMap = puzzle.indexer();
        initPuzzle(rows, cols);
    }

    @Override
    public PuzzlePiece[][] call() {
        solvedPuzzle = null;

        System.out.println(logMessage(" : started"));
        puzzleSolution();
        if (solvedPuzzle != null) {
            System.out.println(logMessage(" : puzzle is solved"));
           return solvedPuzzle;
        } else {

            return null;
        }

    }


    public void puzzleSolution() {

        toSearch.setLeft(0);
        toSearch.setTop(0);
        List<PuzzlePiece> listTL = getSpecificPieces();
        for (PuzzlePiece first : listTL) {
            initPuzzle(rows, cols);
            solvedPuzzle[0][0] = first;
            piecesUsed.push(first.getId());

            boolean result = solvePuzzleRecursion(first, 0, 0, rows, cols);

            if (result) {

                return;
            } else {
                piecesUsed.pop();
                initPuzzle(rows, cols);
            }
        }

    }


    private boolean solvePuzzleRecursion(PuzzlePiece current, int row, int col, int rows, int cols) {
        boolean changeDirection = false;
        if ((System.nanoTime() - start.get()) / 1000 / 1000 > TIMEOUT_MILLISECONDS) {
            System.out.println(logMessage(" : did not find solution during " + TIMEOUT_MILLISECONDS + " millisecond"));
            solvedPuzzle = null;
            isTimeout.set(true);
            return isTimeout.get();
        }
        if (Thread.interrupted()) {
            System.out.println(logMessage(" : puzzle is already solved, exit"));
            solvedPuzzle = null;
            return true;
        }
        if (col == cols - 1 && row == rows - 1) {
            if (validateSolution() || isTimeout.get()) {
                return true;
            } else {
                return false;
            }

        } else {
            PrepareSearchRequirement prepareSearchRequirement = new PrepareSearchRequirement(current, row, col, rows, cols, changeDirection).invoke();
            row = prepareSearchRequirement.getRow();
            col = prepareSearchRequirement.getCol();
            changeDirection = prepareSearchRequirement.isChangeDirection();

            List<PuzzlePiece> list = getSpecificPieces();

            if (list.size() > 0) {
                for (PuzzlePiece p : list) {
                    piecesUsed.push(p.getId());
                    if (!changeDirection) {
                        solvedPuzzle[row][++col] = p;
                    } else {
                        solvedPuzzle[row][col] = p;
                    }
                    current = p;

                    boolean solved = solvePuzzleRecursion(current, row, col, rows, cols);

                    if (solved) {
                        return true;
                    } else {
                        piecesUsed.pop();
                        --col;
                        if (col < 0) {
                            col = 0;

                        }

                    }
                }
            }
        }

        return false;
    }


    public List<PuzzlePiece> getSpecificPieces() {

        List<PuzzlePiece> specificEdges = new ArrayList<>();

        for (Map.Entry<PuzzleShape, List<PuzzlePiece>> entry : puzzleShapeListMap.entrySet()) {
            List<PuzzlePiece> list = puzzleShapeListMap.get(entry.getKey());
            for (PuzzlePiece p : list) {

                boolean isMatch = toSearch.isMatch(p);
                if (isMatch) {
                    if (!piecesUsed.contains(p.getId())) {
                        specificEdges.add(p);
                        break;
                    }
                }
            }
        }

        return specificEdges;
    }


    public boolean validateSolution() {
        return verifyThatSolutionContainsAllPieces() && PuzzleValidation.checkSum(solvedPuzzle);
    }


    //    ========================================================================
//                         Private methods
//    ========================================================================


    private void initPuzzle(int rows, int cols) {
        solvedPuzzle = new PuzzlePiece[rows][cols];
        piecesUsed = new Stack<>();
        initToSearch();
    }

    private void initToSearch() {
        toSearch = new PuzzleShape(new int[]{JOKER, JOKER, JOKER, JOKER});
    }

    private String logMessage(String message) {
        String log = new Timestamp(System.currentTimeMillis()) + ": Thread " + Thread.currentThread().getId() + " " + rows + "x" + cols + " " + message;
//        puzzle.addError(log);
        return log;
    }

    private boolean verifyThatSolutionContainsAllPieces() {
        if (!(solvedPuzzle.length * solvedPuzzle[0].length == puzzlePieces.size())) {
            return false;
        }
//        for(PuzzlePiece p : puzzlePieces){
//            if(Arrays.binarySearch(solvedPuzzle, p.getId()) == 0){
//                return false;
//            }
//        }
        for (int i = 0; i < solvedPuzzle.length; i++) {
            for (int j = 0; j < solvedPuzzle[0].length; j++) {
                if (!puzzlePieces.contains(solvedPuzzle[i][j])) {
                    return false;
                }
            }
        }

        return true;
    }


    private class PrepareSearchRequirement {
        private PuzzlePiece current;
        private int row;
        private int col;
        private int rows;
        private int cols;
        private boolean changeDirection;

        public PrepareSearchRequirement(PuzzlePiece current, int row, int col, int rows, int cols, boolean changeDirection) {
            this.current = current;
            this.row = row;
            this.col = col;
            this.rows = rows;
            this.cols = cols;
            this.changeDirection = changeDirection;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public boolean isChangeDirection() {
            return changeDirection;
        }

        public PrepareSearchRequirement invoke() {
            initToSearch();
            if (col == cols - 1 && row <= rows - 2) {
                col = 0;
                current = solvedPuzzle[row][col];
                toSearch.setLeft(0);
                toSearch.setTop(0 - current.getBottomValue());
                if (row == rows - 2) {
                    toSearch.setBottom(0);
                }
                ++row;
                changeDirection = true;
            } else if (row == 0) {
                toSearch.setLeft(0 - current.getRightValue());
                toSearch.setTop(0);
                if (col == cols - 2) {
                    toSearch.setRight(0);
                }
            } else if (row > 0 && row < rows - 1) {
                toSearch.setLeft(0 - current.getRightValue());
                toSearch.setTop(0 - solvedPuzzle[row - 1][col + 1].getBottomValue());
                if (col == cols - 2) {
                    toSearch.setRight(0);
                }
            } else if (row == rows - 1) {
                toSearch.setLeft(0 - current.getRightValue());
                toSearch.setBottom(0);
                if (col == cols - 2) {
                    toSearch.setRight(0);
                }
            }
            return this;
        }
    }
}
