package course.puzzle.puzzle;

import course.puzzle.file.FileOutput;

import java.util.ArrayList;
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
                if (solvePuzzileOneColumn(rows)) {
                    return solvedPuzzle;
                }
                ;

            } else if (rows > 1 && cols > 1) {
                if (PuzzleValidation.validateNumberOfStraightEdges(puzzle, rows, cols)) {
                    puzzleSolution(rows, cols);
                    if (verifySolution(solvedPuzzle)) {
                        return solvedPuzzle;
                    }
                }

            }
        }
        solvedPuzzle = null;
        return solvedPuzzle;
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

    private boolean solvePuzzileOneColumn(int rows) {
        solvedPuzzle = new PuzzlePiece[rows][1];

        List<PuzzlePiece> list1 = PuzzleValidation.getSpecificPieces(puzzle, leftStraight, topStraight, rightStraight);
        PuzzlePiece first = list1.get(0);
        puzzle.remove(first);
        solvedPuzzle[0][0] = first;

        List<PuzzlePiece> list2 = PuzzleValidation.getSpecificPieces(puzzle, rightStraight, bottomStraight, leftStraight);
        PuzzlePiece last = list2.get(0);
        puzzle.remove(last);
        solvedPuzzle[rows - 1][0] = last;


        boolean hasSolution = solvePuzzleColumnRecursion(first, 0, 0, rows);
        if (hasSolution) {
            return getColsSum(solvedPuzzle, 0) == 0;
        }
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
//            List<PuzzlePiece> list2 = PuzzleValidation.getSpecificPieces(puzzle, first.getRight().getMatch());
//
//            if (list2.size() > 0) {
//                for (PuzzlePiece p2 : list2) {
//                    p2.setUsed(true);
//                    solvedPuzzle[0][1] = p2;
//                    //Get list of the third elements
//                    List<PuzzlePiece> list3 = PuzzleValidation.getSpecificPieces(puzzle, p2.getRight().getMatch());
//
//                    if (list3.size() > 0) {
//                        for (PuzzlePiece p3 : list3) {
//                            p3.setUsed(true);
//                            solvedPuzzle[0][2] = p3;
//                            List<PuzzlePiece> list4 = PuzzleValidation.getSpecificPieces(puzzle, p3.getRight().getMatch());
//
//                            if (list4.size() > 0) {
//                                for (PuzzlePiece p4 : list4) {
//                                    p4.setUsed(true);
//                                    solvedPuzzle[0][3] = p4;
//                                    List<PuzzlePiece> list5 = PuzzleValidation.getSpecificPieces(puzzle, p4.getRight().getMatch());
//                                    if (list5.size() > 0) {
//                                        solvedPuzzle[0][4] = list5.get(0);
//                                        if (getRowSum(solvedPuzzle, 0) == 0) {
//                                            System.out.println(first.getId() + " " + p2.getId() + " " + p3.getId() + " " + p4.getId() + " " + list5.get(0).getId());
//
//                                            return true;
//                                        }
//
//                                    }
//                                    p4.setUsed(false);
//                                }//for 4th
//                            }
//                            p3.setUsed(false);
//                        }//for third
//                    }//if third exists
//                    p2.setUsed(false);
//                } //for second
//            }//if second exists
//            else {
            first.setUsed(false);
//            }


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
        if (col == cols - 1) {
//         if (getRowSum(solvedPuzzle, 0) == 0) {
//               return true;
//            } else {
                return false;
//            }
        } else {
            List<PuzzlePiece> list = PuzzleValidation.getSpecificPieces(puzzle, current.getRight().getMatch());

            if (list.size() > 0) {
                for (PuzzlePiece p : list) {
                    p.setUsed(true);
                    solvedPuzzle[row][++col] = p;
                    current = p;

                    //Get list of the next elements
                    solvePuzzleRowRecursion(current, row, col, cols);

                    System.out.print(p.getId() + " ");

//                    p.setUsed(false);
                }
                current.setUsed(false);
            }

        }

        return false;
    }

    private boolean solvePuzzleColumnRecursion(PuzzlePiece current, int row, int col, int rows) {

        if (row == rows - 2) {
            return true;
        } else {
            List<PuzzlePiece> list = PuzzleValidation.getSpecificPieces(puzzle, current.getBottom().getMatch());
            if (list.size() == 0) {
                System.out.println("no solution");
                return false;
            } else {
                current = list.get(0);
                solvedPuzzle[++row][col] = current;
                puzzle.remove(current);
            }
        }
        return solvePuzzleColumnRecursion(current, row, col, rows);
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
