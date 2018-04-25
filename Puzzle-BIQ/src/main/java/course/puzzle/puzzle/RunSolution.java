package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.List;

public class RunSolution implements Runnable {
    private int rows;
    private int cols;
    private List<Edge> toSearch = new ArrayList<>();
    private PuzzlePiece[][] solvedPuzzle = new PuzzlePiece[][]{};
    private List<PuzzlePiece> puzzle;
    private List<PuzzlePiece> piecesUsed;
    private static Edge leftStraight = new Edge("left", 0);
    private static Edge topStraight = new Edge("top", 0);
    private boolean isRotate = false;


    public RunSolution(List<PuzzlePiece> puzzle, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.puzzle = puzzle;
    }

    public boolean puzzleSolution() {

        boolean hasSolution = false;
        List<PuzzlePiece> listTL = PuzzleValidation.getSpecificPieces(puzzle, leftStraight, topStraight);
        for (PuzzlePiece first : listTL) {
            initPuzzle(rows, cols);
            solvedPuzzle[0][0] = first;
            piecesUsed.add(first);
            hasSolution = solvePuzzleRecursion(first, 0, 0, rows, cols);
            if (hasSolution) {
                return hasSolution;
            } else {
                piecesUsed.remove(first);
            }
        }
        return hasSolution;

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

            List<PuzzlePiece> list = getSpecificPieces(puzzle, toSearch, piecesUsed);

            if (list.size() > 0) {
                for (PuzzlePiece p : list) {
                    piecesUsed.add(p);
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


    public List<PuzzlePiece> getSpecificPieces(List<PuzzlePiece> puzzle, List<Edge> edgeToSearch, List<PuzzlePiece> used) {

        List<PuzzlePiece> updatedList = new ArrayList<>();
        for (PuzzlePiece p : puzzle) {
            if (!used.contains(p)) {
                updatedList.add(p);
            }
        }

        if (isRotate) {
            updatedList = rotateAll();
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


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. Command");
        puzzleSolution();
        System.out.println(solvedPuzzle.toString());
    }

//    ========================================================================
//                         Private methods
//    ========================================================================

    private List<PuzzlePiece> rotateAll() {
        List<PuzzlePiece> allPieces = new ArrayList<>();

        for (PuzzlePiece p : puzzle) {
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
        if (!(solvedPuzzle.length * solvedPuzzle[0].length == puzzle.size())) {
            return false;
        }
//        for(PuzzlePiece p : puzzle){
//            if(Arrays.binarySearch(solvedPuzzle, p.getId()) == 0){
//                return false;
//            }
//        }
        for (int i = 0; i < solvedPuzzle.length; i++) {
            for (int j = 0; j < solvedPuzzle[0].length; j++) {
                if (!puzzle.contains(solvedPuzzle[i][j])) {
                    return false;
                }
            }
        }

        return true;
    }
}
