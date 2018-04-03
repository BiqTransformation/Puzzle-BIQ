package course.puzzle.puzzle;

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

            solutions.put(puzzleSize, 1);
            solutions.put(1, puzzleSize);
            for (int i = 2; i < puzzleSize; i++) {
                if (puzzleSize % i == 0) {
                    int num = puzzleSize / i;
                    solutions.put(i, num);
                }
            }
        }
        return solutions;
    }

    public PuzzlePiece [][] findSolution() {
        getPossibleSolutions();

        for (Map.Entry<Integer, Integer> s : solutions.entrySet()) {

                int rows = s.getKey();
                int cols = s.getValue();

                if (rows == cols && rows == 1) {

                    return new PuzzlePiece[][]{{puzzle.get(0)}, {}};

                } else if (rows == 1 && PuzzleValidation.isPossibleOneRow(puzzle)) {
                    solvePuzzleOneRow(cols);
                    if(verifySolution(solvedPuzzle)){
                        return solvedPuzzle;
                    }

                } else if (cols == 1 && PuzzleValidation.isPossibleOneColumn(puzzle)) {
                    solvePuzzileOneColumn(rows);
                    if(verifySolution(solvedPuzzle)){
                        return solvedPuzzle;
                    }
                } else if (rows > 1 && cols > 1) {
                    if(PuzzleValidation.validateNumberOfStraightEdges(puzzle,rows,cols)){
                        puzzleSolution(rows, cols);
                        if(verifySolution(solvedPuzzle)){
                            return solvedPuzzle;
                        }
                    }

                }



        }
        return null;
    }

    public void puzzleSolution(int rows, int cols) {
        solvedPuzzle = new PuzzlePiece[rows][cols];
        //Prepare frame
        //Get all corners and put it on the board
        List<PuzzlePiece> listLT = PuzzleValidation.getSpecificPieces(puzzle, leftStraight, topStraight);
        PuzzlePiece cornerLT = listLT.get(0);
        puzzle.remove(cornerLT);

        List<PuzzlePiece> listRT = PuzzleValidation.getSpecificPieces(puzzle, rightStraight, topStraight);
        PuzzlePiece cornerRT = listRT.get(0);
        puzzle.remove(cornerRT);

        List<PuzzlePiece> listLB = PuzzleValidation.getSpecificPieces(puzzle, leftStraight, bottomStraight);
        PuzzlePiece cornerLB = listLB.get(0);
        puzzle.remove(cornerLB);

        List<PuzzlePiece> listRB = PuzzleValidation.getSpecificPieces(puzzle, rightStraight, bottomStraight);
        PuzzlePiece cornerRB = listRB.get(0);
        puzzle.remove(cornerRB);

        solvedPuzzle[0][0] = cornerLT;
        solvedPuzzle[0][cols - 1] = cornerRT;
        solvedPuzzle[rows - 1][0] = cornerLB;
        solvedPuzzle[rows - 1][cols - 1] = cornerRB;


        //Fill frame
        //Top side
        solvePuzzleRowRecursion(cornerLT, 0, 0, cols);
        //Left side
        solvePuzzleColumnRecursion(cornerLT, 0, 0, rows);
        //Bottom side
        solvePuzzleRowRecursion(cornerLB, rows - 1, 0, cols);
        //Right side
        solvePuzzleColumnRecursion(cornerRT, 0, cols - 1, rows);
    }

    private void solvePuzzileOneColumn(int rows) {
        solvedPuzzle = new PuzzlePiece[rows][1];
        List<PuzzlePiece> list1 = PuzzleValidation.getSpecificPieces(puzzle, leftStraight, topStraight, rightStraight);
        PuzzlePiece first = list1.get(0);
        puzzle.remove(first);
        solvedPuzzle[0][0] = first;

        List<PuzzlePiece> list2 = PuzzleValidation.getSpecificPieces(puzzle, rightStraight, bottomStraight, leftStraight);
        PuzzlePiece last = list2.get(0);
        puzzle.remove(last);
        solvedPuzzle[rows - 1][0] = last;

        solvePuzzleColumnRecursion(first, 0, 0, rows);

    }

    private void solvePuzzleOneRow(int cols) {
        solvedPuzzle = new PuzzlePiece[1][cols];
        List<PuzzlePiece> list1 = PuzzleValidation.getSpecificPieces(puzzle, leftStraight, topStraight, bottomStraight);
        PuzzlePiece first = list1.get(0);
        puzzle.remove(first);
        solvedPuzzle[0][0] = first;

        List<PuzzlePiece> list2 = PuzzleValidation.getSpecificPieces(puzzle, rightStraight, topStraight, bottomStraight);
        PuzzlePiece last = list2.get(0);
        puzzle.remove(last);


        PuzzlePiece current = first;
        int ind = 0;
        solvePuzzleRowRecursion(current, 0, 0, cols);

        solvedPuzzle[0][cols - 1] = last;
    }

    private void solvePuzzleRowRecursion(PuzzlePiece current, int row, int col, int cols) {

        if (col == cols - 2) {
            return;
        } else {
            List<PuzzlePiece> list = PuzzleValidation.getSpecificPieces(puzzle, current.getRight().getMatch());
            if (list.size() == 0) {
                System.out.println("no solution");
                return;
            }
            else{
                current = list.get(0);
                solvedPuzzle[row][++col] = current;
                puzzle.remove(current);
            }

        }
        solvePuzzleRowRecursion(current, row, col, cols);
    }

    private void solvePuzzleColumnRecursion(PuzzlePiece current, int row, int col, int rows) {

        if (row == rows - 2) {
            return;
        } else {
            List<PuzzlePiece> list = PuzzleValidation.getSpecificPieces(puzzle, current.getBottom().getMatch());
            if (list.size() == 0) {
                System.out.println("no solution");
                return;
            }
            else {
                current = list.get(0);
                solvedPuzzle[++row][col] = current;
                puzzle.remove(current);
            }
        }
        solvePuzzleColumnRecursion(current, row, col, rows);
    }


    //check the case that piece1 exists on board and piece2 want
//to connect to piece1 left side with piece2 right side
    public boolean checkRightToLeft(PuzzlePiece puzzlePiece1, PuzzlePiece puzzlePiece2) {
        return (puzzlePiece1.getLeftValue()) + (puzzlePiece2.getRightValue()) == 0;
    }

    //check the case that piece1 exists on board and piece2 want
//to connect to piece1 right side with piece2 left side
    public boolean checkLeftToRight(PuzzlePiece puzzlePiece1, PuzzlePiece puzzlePiece2) {
        return puzzlePiece1.getRightValue() + puzzlePiece2.getLeftValue() == 0;
    }

    //check the case that piece1 exists on board and piece2 want
//to connect to piece1 top side with piece2 bottom side
    public boolean checkTopToBottom(PuzzlePiece puzzlePiece1, PuzzlePiece puzzlePiece2) {
        return puzzlePiece1.getTopValue() + puzzlePiece2.getBottomValue() == 0;
    }

    //check the case that piece1 exists on board and piece2 want
//to connect to piece1 bottom side with piece2 top side
    public boolean checkBottomToTop(PuzzlePiece puzzlePiece1, PuzzlePiece puzzlePiece2) {
        return puzzlePiece1.getBottomValue() + puzzlePiece2.getTopValue() == 0;
    }

    public static boolean verifySolution(PuzzlePiece[][] solvedPuzzle) {
        int rowsSum = 0;
        for (int row = 0; row < solvedPuzzle.length; row++) {
            rowsSum += getRowSum(solvedPuzzle, row);
        }
        int colsSum = 0;
        for (int col = 0; col < solvedPuzzle[0].length; col++) {
            colsSum += getColsSum(solvedPuzzle, col);
        }

        return (rowsSum + colsSum) == 0;
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
