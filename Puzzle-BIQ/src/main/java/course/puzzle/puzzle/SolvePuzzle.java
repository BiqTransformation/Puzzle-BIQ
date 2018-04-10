package course.puzzle.puzzle;

import course.puzzle.file.FileOutput;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SolvePuzzle extends Puzzle {


    //members
    private PuzzlePiece[][] solvedPuzzle;
    private Map<Integer, Integer> solutions = new LinkedHashMap<>();

    private static Edge leftStraight = new Edge("left", 0);
    private static Edge rightStraight = new Edge("right", 0);
    private static Edge topStraight = new Edge("top", 0);
    private static Edge bottomStraight = new Edge("bottom", 0);


    public SolvePuzzle(List<PuzzlePiece> puzzle) {
        super(puzzle);
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

            } else if (rows == 1 && PuzzleValidation.isPossibleOneRow(puzzle)) {
                if (solvePuzzleOneRow(cols)) {
                    return solvedPuzzle;
                }

            } else if (cols == 1 && PuzzleValidation.isPossibleOneColumn(puzzle)) {
                if (solvePuzzleOneColumn(rows)) {
                    return solvedPuzzle;
                }
                ;

            } else if (rows > 1 && cols > 1) {
                if (PuzzleValidation.validateNumberOfStraightEdges(puzzle, rows, cols)) {

                    if (puzzleSolution(rows, cols)) {
                        return solvedPuzzle;
                    }
                }

            }
        }
        solvedPuzzle = null;
        return solvedPuzzle;
    }

    public boolean  puzzleSolution(int rows, int cols) {

        boolean hasSolution = false;
        List<PuzzlePiece> listTL = PuzzleValidation.getSpecificPieces(puzzle, leftStraight, topStraight, bottomStraight);
        for (PuzzlePiece first : listTL) {
            initPuzzle(rows, cols);
        }
        return hasSolution;

    }

    private boolean solvePuzzleOneColumn(int rows) {
         boolean hasSolution = false;

        List<PuzzlePiece> listTop = PuzzleValidation.getSpecificPieces(puzzle, leftStraight, topStraight, rightStraight);
        for (PuzzlePiece first : listTop) {
            initPuzzle(rows, 1);

            solvedPuzzle[0][0] = first;
            first.setUsed(true);

            hasSolution = solvePuzzleColumnRecursion(first, 0, 0, rows);
            if(hasSolution){
                return hasSolution;
            }

            first.setUsed(false);
        }//for all firsts
        return hasSolution;
    }



    private boolean solvePuzzleOneRow(int cols) {

        boolean hasSolution = false;

        List<PuzzlePiece> listTL = PuzzleValidation.getSpecificPieces(puzzle, leftStraight, topStraight, bottomStraight);
        for (PuzzlePiece first : listTL) {
            initPuzzle(1, cols);

            solvedPuzzle[0][0] = first;
            first.setUsed(true);

            hasSolution = solvePuzzleRowRecursion(first, 0, 0, cols);
            if(hasSolution){
                return hasSolution;
            }

            first.setUsed(false);
        }//for all firsts
        return hasSolution;
    }

    private void initPuzzle(int rows, int cols) {
        solvedPuzzle = new PuzzlePiece[rows][cols];
        for (PuzzlePiece p : puzzle) {
            p.setUsed(false);
        }
    }

    private boolean solvePuzzleRowRecursion(PuzzlePiece current, int row, int col, int cols) {
        Edge edge1 = null;
        Edge edge2 = null;

        if (col == cols - 1) {
            if (getRowSum(solvedPuzzle, 0) == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            edge1 = current.getRight().getMatch();
            edge2 = new Edge("top",0);

            List<PuzzlePiece> list = PuzzleValidation.getSpecificPieces(puzzle, edge1,edge2);

            if (leftToRight(row, col, cols, list)) return true;
            }

        return false;
    }


    private boolean solvePuzzleColumnRecursion(PuzzlePiece current, int row, int col, int rows) {
        if (row == rows - 1) {
            if (getColsSum(solvedPuzzle, 0) == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            Edge edge1 = current.getBottom().getMatch();
            Edge edge2 = new Edge("right",0);
            List<PuzzlePiece> list = PuzzleValidation.getSpecificPieces(puzzle, edge1,edge2);

            if (list.size() > 0) {
                for (PuzzlePiece p : list) {
                    p.setUsed(true);
                    solvedPuzzle[++row][col] = p;
                    current = p;

                    boolean solved = solvePuzzleColumnRecursion(current, row, col, rows);
                    System.out.print(p.getId() + " ");
                    if (solved) {
                        return true;
                    } else {
                        p.setUsed(false);
                        --row;
                    }
                }
            }
        }

        return false;
    }
    private boolean solvePuzzleRecursion(PuzzlePiece current, int row, int col, int rows, int cols) {
        Edge edge1 = null;
        Edge edge2 = null;
        Edge edge3 = null;
        List<PuzzlePiece> list = null;

        if (col == cols - 1 && row == rows - 1) {
            if (verifySolution(solvedPuzzle)) {
                return true;
            } else {
                return false;
            }
        } else {
            if(row == 0){
               list = PuzzleValidation.getSpecificPieces(puzzle, current.getRight().getMatch(),new Edge("top",0));
            }
            else if(row < rows - 1){
               list = PuzzleValidation.getSpecificPieces(puzzle, current.getRight().getMatch(),solvedPuzzle[row-1][col+1].getBottom().getMatch());
             }
            else if(row == rows - 1){
                    list = PuzzleValidation.getSpecificPieces(puzzle, current.getRight().getMatch(),new Edge("bottom",0));
            }
            else if(col == cols - 2){
                list = PuzzleValidation.getSpecificPieces(puzzle, current.getRight().getMatch(),new Edge("bottom",0),new Edge("top",0));
            }

            if (leftToRight(row, col, cols, list)) return true;
        }

        return false;
    }

    private boolean leftToRight(int row, int col, int cols, List<PuzzlePiece> list) {
        PuzzlePiece current;
        if (list.size() > 0) {
            for (PuzzlePiece p : list) {
                p.setUsed(true);
                solvedPuzzle[row][++col] = p;
                current = p;

                boolean solved = solvePuzzleRowRecursion(current, row, col, cols);

                if (solved) {
                    return true;
                } else {
                    p.setUsed(false);
                    --col;
                }
            }
        }
        return false;
    }
    private boolean rightToLeft(int row, int col, int cols, List<PuzzlePiece> list) {
        PuzzlePiece current;
        if (list.size() > 0) {
            for (PuzzlePiece p : list) {
                p.setUsed(true);
                solvedPuzzle[row][--col] = p;
                current = p;

                boolean solved = solvePuzzleRowRecursion(current, row, col, cols);

                if (solved) {
                    return true;
                } else {
                    p.setUsed(false);
                    ++col;
                }
            }
        }
        return false;
    }


    public static boolean verifySolution(PuzzlePiece[][] solvedPuzzle) {
        if (solvedPuzzle != null) {
            int rowsSum = 0;
            for (int row = 0; row < solvedPuzzle.length; row++) {
                rowsSum += getRowSum(solvedPuzzle, row);
            }
            int colsSum = 0;
            for (int col = 0; col < solvedPuzzle[0].length; col++) {
                colsSum += getColsSum(solvedPuzzle, col);
            }

            return (rowsSum + colsSum) == 0;
        } else {
            FileOutput.printToOutputFile(Parameters.CANNOT_SOLVE_PUZZLE);
            return false;
        }

    }

    private static int getRowSum(PuzzlePiece[][] solvedPuzzle, int row) {
        int sum = solvedPuzzle[row][0].getLeftValue();

        for (int i = 0; i < solvedPuzzle[0].length - 1; i++) {
            sum += solvedPuzzle[row][i].getRightValue() + solvedPuzzle[row][i + 1].getLeftValue();
        }
        return sum;
    }

    private static int getColsSum(PuzzlePiece[][] solvedPuzzle, int col) {
        int sum = solvedPuzzle[0][col].getTopValue();

        for (int i = 0; i < solvedPuzzle.length - 1; i++) {
            sum += solvedPuzzle[i][col].getBottomValue() + solvedPuzzle[i + 1][col].getTopValue();

        }
        return sum;
    }


}
