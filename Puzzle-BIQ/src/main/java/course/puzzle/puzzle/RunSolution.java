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
    private Puzzle puzzle;
    private List<PuzzlePiece> puzzlePieces;
    private Stack<Integer> piecesUsed;
    private Map<PuzzleShape, List<PuzzlePiece>> puzzleShapeListMap;

    public RunSolution(Puzzle puzzle, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.puzzle = puzzle;
        puzzlePieces = puzzle.getPuzzle();
        puzzleShapeListMap = puzzle.getAllPiecesMap();
        initPuzzle(rows, cols);
    }
    public RunSolution(Puzzle puzzle, int rows, int cols, long timeout) {
        this.rows = rows;
        this.cols = cols;
        this.puzzle = puzzle;
        TIMEOUT_MILLISECONDS = timeout;
        puzzlePieces = puzzle.getPuzzle();
        puzzleShapeListMap = puzzle.getAllPiecesMap();
        initPuzzle(rows, cols);
    }

    @Override
    public PuzzlePiece[][] call() throws Exception {
        System.out.println(printToLog("started"));
        puzzleSolution();
        if (isTimeout.get()) {
            System.out.println(printToLog("exceed timeout"));
            return null;
        }
        if (puzzle.getSolved().get()) {
            System.out.println(printToLog(" : puzzle is solved"));
            return getSolvedPuzzle();
        } else {
            return null;
        }

    }

    public void puzzleSolution() {

        if(puzzle.getSolved().get()){
            System.out.println(printToLog("puzzle is already solved!"));
            Thread.currentThread().interrupt();
       }
        else {
            toSearch.setLeft(0);
            toSearch.setTop(0);
            List<PuzzlePiece> listTL = getSpecificPieces();
            for (PuzzlePiece first : listTL) {
                initPuzzle(rows, cols);
                solvedPuzzle[0][0] = first;
                piecesUsed.push(first.getId());

                boolean res = solvePuzzleRecursion(first, 0, 0, rows, cols);
                if (isTimeout.get()) {
                    break;
                }
                puzzle.getSolved().set(res);
                if (puzzle.getSolved().get()) {

                    return;
                } else {
                    piecesUsed.pop();
                    initPuzzle(rows, cols);
                }
            }
        }


    }


    public PuzzlePiece[][] getSolvedPuzzle() {
        return solvedPuzzle;
    }

    private boolean solvePuzzleRecursion(PuzzlePiece current, int row, int col, int rows, int cols) {
        boolean changeDirection = false;
        if ((System.nanoTime() - start.get()) / 1000000 > TIMEOUT_MILLISECONDS) {
            isTimeout.set(true);
            return isTimeout.get();
        }

        if (col == cols - 1 && row == rows - 1) {
            if (validateSolution() || isTimeout.get()) {
                return true;
            } else {
                return false;
            }

        } else {
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

    private String printToLog(String message){
        return new Timestamp(System.currentTimeMillis()) + ": Thread " + Thread.currentThread().getId() + " " + cols + "x" + rows + " " + message;
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


}
