package course.puzzle.puzzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RunSolution implements Runnable {
    private int rows;
    private int cols;
    private List<Edge> toSearch = new ArrayList<>();
    private static ThreadLocal<PuzzlePiece[][]> solvedPuzzle;
    private static ThreadLocal<List<PuzzlePiece>> puzzle = ThreadLocal.withInitial(() -> new ArrayList<>());
    private static Edge leftStraight = new Edge("left", 0);
    private static Edge topStraight = new Edge("top", 0);


    public RunSolution(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        solvedPuzzle.set(new PuzzlePiece[rows][cols]);
    }

    public boolean puzzleSolution() {

        boolean hasSolution = false;
        List<PuzzlePiece> listTL = PuzzleValidation.getSpecificPieces(puzzle.get(), leftStraight, topStraight);
        for (PuzzlePiece first : listTL) {
            initPuzzle(rows, cols);
            solvedPuzzle.get()[0][0] = first;
            first.setUsed(true);
            hasSolution = solvePuzzleRecursion(first, 0, 0, rows, cols);
        }
        return hasSolution;

    }

    private void initPuzzle(int rows, int cols) {
        solvedPuzzle.set(new PuzzlePiece[rows][cols]);
        for (PuzzlePiece p : puzzle.get()) {
            p.setUsed(false);
        }
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

    public PuzzlePiece[][] getSolvedPuzzle() {
        return solvedPuzzle.get();
    }

    private boolean solvePuzzleRecursion(PuzzlePiece current, int row, int col, int rows, int cols) {
        boolean changeDirection = false;

        if (col == cols - 1 && row == rows - 1) {
            if (checkSum()) {
                return true;
            } else {
                return false;
            }

        } else {
            initToSearch();
            if (col == cols - 1 && row <= rows - 2) {
                col = 0;
                current = solvedPuzzle.get()[row][col];
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
                toSearch.set(1, solvedPuzzle.get()[row - 1][col + 1].getBottom().getMatch());
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

            List<PuzzlePiece> list = PuzzleValidation.getSpecificPieces(puzzle.get(), toSearch);

            if (list.size() > 0) {
                for (PuzzlePiece p : list) {
                    p.setUsed(true);
                    if (!changeDirection) {
                        solvedPuzzle.get()[row][++col] = p;
                    } else {
                        solvedPuzzle.get()[row][col] = p;
                    }
                    current = p;

                    boolean solved = solvePuzzleRecursion(current, row, col, rows, cols);

                    if (solved) {
                        return true;
                    } else {
                        p.setUsed(false);
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


    private boolean verifyThatSolutionContainsAllPieces() {
        boolean isValid = solvedPuzzle.get().length * solvedPuzzle.get()[0].length == puzzle.get().size();
        for (int i = 0; i < solvedPuzzle.get().length; i++) {
            for (int j = 0; j < solvedPuzzle.get()[0].length; j++) {
                if (!puzzle.get().contains(solvedPuzzle.get()[i][j])) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }


    public boolean validateSolution() throws IOException {
        return verifyThatSolutionContainsAllPieces() && checkSum();
    }


    public boolean checkSum() {
        boolean isAllPiecesMatch = false;
        if (solvedPuzzle != null) {
            int rowsSum = 0;
            for (int row = 0; row < solvedPuzzle.get().length; row++) {
                rowsSum += getRowSum(row);
            }
            int colsSum = 0;
            for (int col = 0; col < solvedPuzzle.get()[0].length; col++) {
                colsSum += getColsSum(col);
            }

            isAllPiecesMatch = ((rowsSum + colsSum) == 0);
        }
        return isAllPiecesMatch;

    }

    private int getRowSum(int row) {
        int sum = solvedPuzzle.get()[row][0].getLeftValue();

        for (int i = 0; i < solvedPuzzle.get()[0].length - 1; i++) {
            sum += solvedPuzzle.get()[row][i].getRightValue() + solvedPuzzle.get()[row][i + 1].getLeftValue();
        }
        return sum;
    }

    private int getColsSum(int col) {
        int sum = solvedPuzzle.get()[0][col].getTopValue();

        for (int i = 0; i < solvedPuzzle.get().length - 1; i++) {
            sum += solvedPuzzle.get()[i][col].getBottomValue() + solvedPuzzle.get()[i + 1][col].getTopValue();

        }
        return sum;
    }

    @Override
    public void run() {

    }
}
