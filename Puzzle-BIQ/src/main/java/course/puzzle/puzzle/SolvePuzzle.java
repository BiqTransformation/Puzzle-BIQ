package course.puzzle.puzzle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolvePuzzle extends Puzzle{

	//members
	private PuzzlePiece[][] solvedPuzzle;
	public SolvePuzzle(List<PuzzlePiece> puzzle) {
		super(puzzle);
	}

    public Map<Integer, Integer> getPossibleSolutions() {
        Map<Integer, Integer> solutions = new HashMap<Integer, Integer>();
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

    //check the case that piece1 exists on board and piece2 want
    //to connect to piece1 left side with piece2 right side
    public boolean checkRightToLeft(PuzzlePiece puzzlePiece1, PuzzlePiece puzzlePiece2) {
        return (puzzlePiece1.getLeft()) + (puzzlePiece2.getRight()) == 0;
    }

    //check the case that piece1 exists on board and piece2 want
    //to connect to piece1 right side with piece2 left side
    public boolean checkLeftToRight(PuzzlePiece puzzlePiece1, PuzzlePiece puzzlePiece2) {
        return puzzlePiece1.getRight() + puzzlePiece2.getLeft() == 0;
    }

    //check the case that piece1 exists on board and piece2 want
    //to connect to piece1 top side with piece2 bottom side
    public boolean checkTopToBottom(PuzzlePiece puzzlePiece1, PuzzlePiece puzzlePiece2) {
        return puzzlePiece1.getTop() + puzzlePiece2.getBottom() == 0;
    }

    //check the case that piece1 exists on board and piece2 want
    //to connect to piece1 bottom side with piece2 top side
    public boolean checkBottomToTop(PuzzlePiece puzzlePiece1, PuzzlePiece puzzlePiece2) {
        return puzzlePiece1.getBottom() + puzzlePiece2.getTop() == 0;
    }

    public static boolean validateSolution(PuzzlePiece[][] solvedPuzzle) {
        int rowsSum = 0;
        for(int row = 0; row < solvedPuzzle[row].length-1; row++){
            rowsSum += getRowSum(solvedPuzzle, row);
        }
        int colsSum = 0;
        for(int col = 0; col < solvedPuzzle.length-1; col++){
            colsSum += getColsSum(solvedPuzzle, col);
        }

        return (rowsSum + colsSum)==0 ;
    }

    private static int getRowSum(PuzzlePiece[][] solvedPuzzle, int row) {
        int sum = solvedPuzzle[row][0].getLeft();

        for(int i = 0; i < solvedPuzzle[row].length-1; i++){
               sum += solvedPuzzle[row][i].getRight() + solvedPuzzle[row][i + 1].getLeft();
        }
        return sum;
    }

    private static int getColsSum(PuzzlePiece[][] solvedPuzzle, int col) {
       int sum = solvedPuzzle[0][col].getTop();

        for (int i = 0; i < solvedPuzzle.length-1; i++) {
                sum += solvedPuzzle[i][col].getBottom() + solvedPuzzle[i + 1][col].getTop();

        }
        return sum;
    }
}
