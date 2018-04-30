package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;

public class RunSolution implements Callable {
    private int rows;
    private int cols;
    private List<Edge> toSearch = new ArrayList<>();
    private PuzzlePiece[][] solvedPuzzle = new PuzzlePiece[][]{};
    private Puzzle puzzle;
    private List<PuzzlePiece> puzzlePieces;
    private Stack<Integer> piecesUsed;
    private static Edge leftStraight = new Edge("left", 0);
    private static Edge topStraight = new Edge("top", 0);
    private boolean isRotate = true;


    public RunSolution(Puzzle puzzle, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.puzzle = puzzle;
        puzzlePieces = puzzle.getPuzzle();
        initPuzzle(rows, cols);
    }

    public void puzzleSolution() {

        if (!puzzle.getSolved().get()) {

            toSearch.set(0, leftStraight);
            toSearch.set(1, topStraight);
            List<PuzzlePiece> listTL = getSpecificPieces();
            for (PuzzlePiece first : listTL) {
                initPuzzle(rows, cols);
                solvedPuzzle[0][0] = first;
                piecesUsed.push(first.getId());
                boolean res = solvePuzzleRecursion(first, 0, 0, rows, cols);
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

        if (col == cols - 1 && row == rows - 1) {
            if (validateSolution()) {
                return true;
            } else {
                return false;
            }

        } else {
            initToSearch();
            if (col == cols - 1 && row <= rows - 2) {
                col = 0;
                current = solvedPuzzle[row][col];
                toSearch.set(0, current.getBottom().getMatch());
                toSearch.set(1, new Edge("left", 0));
                if (row == rows - 2) {
                    toSearch.set(2, new Edge("bottom", 0));
                }
                ++row;
                changeDirection = true;
            } else if (row == 0) {
                toSearch.set(0, current.getRight().getMatch());
                toSearch.set(1, new Edge("top", 0));
                if (col == cols - 2) {
                    toSearch.set(2, new Edge("right", 0));
                }
            } else if (row > 0 && row < rows - 1) {
                toSearch.set(0, current.getRight().getMatch());
                toSearch.set(1, solvedPuzzle[row - 1][col + 1].getBottom().getMatch());
                if (col == cols - 2) {
                    toSearch.set(2, new Edge("right", 0));
                }
            } else if (row == rows - 1) {
                toSearch.set(0, current.getRight().getMatch());
                toSearch.set(1, new Edge("bottom", 0));
                if (col == cols - 2) {
                    toSearch.set(2, new Edge("right", 0));
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

        List<PuzzlePiece> updatedList = new ArrayList<>();
        for (PuzzlePiece p : puzzlePieces) {

            if (!piecesUsed.contains(p.getId())) {
                updatedList.add(p);
            }
        }

        if (isRotate) {
            updatedList = rotateAll(updatedList);
        }

        List<PuzzlePiece> specificEdges = new ArrayList<>();

        for (PuzzlePiece p : updatedList) {

            boolean addToList = true;
            for (Edge e : toSearch) {
                if (e != null && !p.listOfEdges.contains(e)) {
                    addToList = false;
                }
            }
            if (addToList) {
                specificEdges.add(p);
            }
        }
//        specificEdges = getUniqueShapes(specificEdges);
        return specificEdges;
    }


    public boolean validateSolution() {
        return verifyThatSolutionContainsAllPieces() && PuzzleValidation.checkSum(solvedPuzzle);
    }


//    ========================================================================
//                         Private methods
//    ========================================================================

    public List<PuzzlePiece> getUniqueShapes(List<PuzzlePiece> inputList) {
        List<PuzzlePiece> uniquePieces = new ArrayList<>(inputList);
        List<PuzzlePiece> checkDup = new ArrayList<>(inputList);


            for (int i = 1; i < checkDup.size(); i++) {
                PuzzlePiece p1 = checkDup.get(i);
                checkDup.remove(p1);
                if (checkDupPiece(p1, checkDup)) {
                    uniquePieces.remove(p1);
                    i--;
                }
            }

        return uniquePieces;
    }


    private boolean checkDupPiece(PuzzlePiece p, List<PuzzlePiece> uniquePieces) {
        for (PuzzlePiece piece : uniquePieces) {
            if (p.listOfEdgesEquals(piece)) {
                return true;
            }
        }
        return false;
    }

    public List<PuzzlePiece> rotateAll(List<PuzzlePiece> list) {
        List<PuzzlePiece> allPieces = new ArrayList<>();


        for (PuzzlePiece p : list) {
            allPieces.add(p);
            PuzzlePiece p90 = p.firstRotate(p);
            PuzzlePiece p180 = p.secondRotate(p);
            PuzzlePiece p270 = p.thirdRotate(p);
            if (!p.isAllEdgesEquals()) {
                if (!p.isOposEdgesEquals(p)) {
                     allPieces.add(p90);
                     allPieces.add(p180);
                     allPieces.add(p270);
                } else {
                     allPieces.add(p90);
                }
            }

        }

        return allPieces;
    }

    private void initPuzzle(int rows, int cols) {
        solvedPuzzle = new PuzzlePiece[rows][cols];
        piecesUsed = new Stack<>();
        initToSearch();
    }

    private void initToSearch() {
        toSearch.add(null);
        toSearch.add(null);
        toSearch.add(null);
        toSearch.add(null);
        for (int i = 0; i < toSearch.size(); i++) {
            toSearch.set(i, null);
        }
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

    @Override
    public PuzzlePiece[][] call() throws Exception {
        System.out.println("Thread " + Thread.currentThread().getId());
        puzzleSolution();
        if (puzzle.getSolved().get()) {
            return getSolvedPuzzle();
        } else {
            return null;
        }

    }
}
