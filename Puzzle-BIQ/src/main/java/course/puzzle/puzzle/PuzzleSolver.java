package course.puzzle.puzzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PuzzleSolver {

    //members
    private List<PuzzlePiece> puzzle;
    private PuzzlePiece[][] solvedPuzzle;
    private Map<Integer, Integer> solutions = new LinkedHashMap<>();
    private Puzzle puzzleInstance;
    private int numOfThreads;

    private List<Edge> toSearch = new ArrayList<>();

    private static Edge leftStraight = new Edge("left", 0);
     private static Edge topStraight = new Edge("top", 0);



    public PuzzleSolver(Puzzle puzzleInstance,int numOfThreads) {
        this.numOfThreads=numOfThreads;
    	this.puzzleInstance = puzzleInstance;
        puzzle = puzzleInstance.getPuzzle();
    }

    public Map<Integer, Integer> getPossibleSolutions() {

        int puzzleSize = puzzle.size();
        if (puzzleSize == 1) {
            solutions.put(1, 1);
        } else if (puzzleSize > 1) {
            solutions.put(1, puzzleSize);
            solutions.put(puzzleSize, 1);

            for (int i = 2; i < puzzleSize; i++) {
                if (puzzleSize % i == 0) {
                    int num = puzzleSize / i;
                    solutions.put(i, num);
                }
            }
        }
        return solutions;
    }

    public PuzzlePiece[][] findSolution() {
        getPossibleSolutions();

        for (Map.Entry<Integer, Integer> s : solutions.entrySet()) {

            int rows = s.getKey();
            int cols = s.getValue();

            if (rows == cols && rows == 1) {

                return new PuzzlePiece[][]{{puzzle.get(0)}, {}};

            } else {
                if (PuzzleValidation.validateNumberOfStraightEdges(puzzle, rows, cols)) {

                    if (puzzleSolution(rows, cols)) {
                        return solvedPuzzle;
                    }
                } else {
                    puzzleInstance.addError("Number of straight edges does not match for this solution: " + rows + "x" + cols);
                }

            }
        }
        solvedPuzzle = null;
        return solvedPuzzle;
    }


    private boolean puzzleSolution(int rows, int cols) {

        boolean hasSolution = false;
        List<PuzzlePiece> listTL = PuzzleValidation.getSpecificPieces(puzzle, leftStraight, topStraight);
        for (PuzzlePiece first : listTL) {
            initPuzzle(rows, cols);
            solvedPuzzle[0][0] = first;
            first.setUsed(true);
            hasSolution = solvePuzzleRecursion(first, 0, 0, rows, cols);
        }
        return hasSolution;

    }


    public boolean validateSolution() throws IOException {
        return verifyThatSolutionContainsAllPieces() && checkSum(solvedPuzzle);
    }


    public boolean checkSum(PuzzlePiece[][] solvedPuzzle) {
        boolean isAllPiecesMatch = false;
        if (solvedPuzzle != null) {
            int rowsSum = 0;
            for (int row = 0; row < solvedPuzzle.length; row++) {
                rowsSum += getRowSum(solvedPuzzle, row);
            }
            int colsSum = 0;
            for (int col = 0; col < solvedPuzzle[0].length; col++) {
                colsSum += getColsSum(solvedPuzzle, col);
            }

            isAllPiecesMatch = ((rowsSum + colsSum) == 0);
        }
        return isAllPiecesMatch;

    }

    //    =============================================================================
//                     Private methods
//    =============================================================================
    private boolean solvePuzzleRecursion(PuzzlePiece current, int row, int col, int rows, int cols) {
        boolean changeDirection = false;

        if (col == cols - 1 && row == rows - 1) {
            if (checkSum(solvedPuzzle)) {
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

            List<PuzzlePiece> list = PuzzleValidation.getSpecificPieces(puzzle, toSearch);

            if (list.size() > 0) {
                for (PuzzlePiece p : list) {
                    p.setUsed(true);
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

    private void initPuzzle(int rows, int cols) {
        solvedPuzzle = new PuzzlePiece[rows][cols];
        for (PuzzlePiece p : puzzle) {
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

    private boolean verifyThatSolutionContainsAllPieces() {
        boolean isValid = solvedPuzzle.length * solvedPuzzle[0].length == puzzle.size();
        for (int i = 0; i < solvedPuzzle.length; i++) {
            for (int j = 0; j < solvedPuzzle[0].length; j++) {
                if (!puzzle.contains(solvedPuzzle[i][j])) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    private int getRowSum(PuzzlePiece[][] solvedPuzzle, int row) {
        int sum = solvedPuzzle[row][0].getLeftValue();

        for (int i = 0; i < solvedPuzzle[0].length - 1; i++) {
            sum += solvedPuzzle[row][i].getRightValue() + solvedPuzzle[row][i + 1].getLeftValue();
        }
        return sum;
    }

    private int getColsSum(PuzzlePiece[][] solvedPuzzle, int col) {
        int sum = solvedPuzzle[0][col].getTopValue();

        for (int i = 0; i < solvedPuzzle.length - 1; i++) {
            sum += solvedPuzzle[i][col].getBottomValue() + solvedPuzzle[i + 1][col].getTopValue();

        }
        return sum;
    }


}
