package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public class RunSolution implements Callable {
    private int rows;
    private int cols;
    private List<Edge> toSearch = new ArrayList<>();
    private PuzzlePiece[][] solvedPuzzle = new PuzzlePiece[][]{};
    private Puzzle puzzle;
    private List<PuzzlePiece> puzzlePieces;
    private List<Integer> piecesUsed;
    private static Edge leftStraight = new Edge("left", 0);
    private static Edge topStraight = new Edge("top", 0);
    private boolean isRotate = false;


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
            List<PuzzlePiece> listTL = getSpecificPieces(puzzlePieces, toSearch, piecesUsed);
            for (PuzzlePiece first : listTL) {
                initPuzzle(rows, cols);
                solvedPuzzle[0][0] = first;
                piecesUsed.add(first.getId());
                boolean res = solvePuzzleRecursion(first, 0, 0, rows, cols);
                puzzle.getSolved().set(res);
                if (puzzle.getSolved().get()) {
                   return;
                } else {
                    piecesUsed.remove(first);
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

            List<PuzzlePiece> list = getSpecificPieces(puzzlePieces, toSearch, piecesUsed);

            if (list.size() > 0) {
                for (PuzzlePiece p : list) {
                    piecesUsed.add(p.getId());
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
                        piecesUsed.remove(p);
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


    public List<PuzzlePiece> getSpecificPieces(List<PuzzlePiece> puzzle, List<Edge> edgeToSearch, List<Integer> used) {

        List<PuzzlePiece> updatedList = new ArrayList<>();
        for (PuzzlePiece p : puzzle) {

            if (!used.contains(p.getId())) {
                updatedList.add(p);
            }
        }

        if (isRotate) {
            updatedList = rotateAll(updatedList);
        }

        List<PuzzlePiece> specificEdges = new ArrayList<>();


        for (PuzzlePiece p : updatedList) {

            boolean addToList = true;
            for (Edge e : edgeToSearch) {
                if (e != null && !p.listOfEdges.contains(e)) {
                    addToList = false;
                }
            }
            if (addToList) {
                specificEdges.add(p);
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

    private List<PuzzlePiece> rotateAll(List<PuzzlePiece> listToRotate) {
        List<PuzzlePiece> allPieces = new ArrayList<>();

        for (PuzzlePiece p : listToRotate) {
            allPieces.add(p);
            if (!p.isAllEdgesEquals()) {
                PuzzlePiece temp1 = firstRotate(p);
                allPieces.add(temp1);
                if (!p.isOposEdgesEquals(p)) {
                    PuzzlePiece temp2 = secondRotate(p);
                    allPieces.add(temp2);
                }
                PuzzlePiece temp3 = thirdRotate(p);
                allPieces.add(temp3);
            }
        }

        return allPieces;
    }


    private PuzzlePiece firstRotate(PuzzlePiece p) {
        PuzzlePiece p1 = new PuzzlePiece(p.getId(), p.getBottomValue(), p.getLeftValue(), p.getTopValue(), p.getRightValue());
        p1.setRotateEdge(90);
        return p1;
    }

    private PuzzlePiece secondRotate(PuzzlePiece p) {
        PuzzlePiece p1 = new PuzzlePiece(p.getId(), p.getRightValue(), p.getBottomValue(), p.getLeftValue(), p.getTopValue());
        p1.setRotateEdge(180);
        return p1;
    }

    private PuzzlePiece thirdRotate(PuzzlePiece p) {
        PuzzlePiece p1 = new PuzzlePiece(p.getId(), p.getBottomValue(), p.getLeftValue(), p.getTopValue(), p.getRightValue());
        p1.setRotateEdge(270);
        return p1;
    }

    private void initPuzzle(int rows, int cols) {
        solvedPuzzle = new PuzzlePiece[rows][cols];
        piecesUsed = new ArrayList<>();
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
        if(puzzle.getSolved().get()){
            return getSolvedPuzzle();
        }
        else{
            return null;
        }

    }
}
