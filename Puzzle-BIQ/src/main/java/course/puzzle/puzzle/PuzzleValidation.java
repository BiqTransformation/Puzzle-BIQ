package course.puzzle.puzzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import course.puzzle.file.FileOutput;

public class PuzzleValidation {
    private static Edge leftStraight = new Edge("left", 0);
    private static Edge rightStraight = new Edge("right", 0);
    private static Edge topStraight = new Edge("top", 0);
    private static Edge bottomStraight = new Edge("bottom", 0);
   //TODO - move rotate to this class
    
    
	public static List<PuzzlePiece> getUniquShapes(List<PuzzlePiece> inputList) {
		List<PuzzlePiece> uniquePieces = inputList;
		List<PuzzlePiece> checkDup = new ArrayList<>();
		for (PuzzlePiece p : uniquePieces) {
			if (p.getRotateEdge() != 0) {
				checkDup.add(p);
			}
		}
		if (checkDup.size() > 0) {
			for (int i = 0; i < checkDup.size(); i++) {
				PuzzlePiece p1 = checkDup.get(i);
				checkDup.remove(p1);
				if (checkDupPiece(p1, checkDup)) {
					uniquePieces.remove(p1);
				}
			}
		}
		return uniquePieces;
	}


	private static boolean checkDupPiece(PuzzlePiece p, List<PuzzlePiece> uniquePieces) {
		for (PuzzlePiece piece : uniquePieces) {
			if (p.listOfEdgesEquals(piece)) {
				return true;
			}
		}
		return false;
	}
	







    public static List<PuzzlePiece> getSpecificPieces(List<PuzzlePiece> puzzle, Edge edge) {
        List<PuzzlePiece> specificEdges = new ArrayList<PuzzlePiece>();
        for (PuzzlePiece p : puzzle) {
            if (p.listOfEdges.contains(edge) && !p.isUsed()) {
                specificEdges.add(p);
            }
        }

        return specificEdges;
    }
    
    
    public static List<PuzzlePiece> getSpecificPieces(List<PuzzlePiece> puzzle, Edge edge1, Edge edge2) {
        List<PuzzlePiece> specificEdges = new ArrayList<>();
        for (PuzzlePiece p : puzzle) {
            if (p.listOfEdges.contains(edge1) && p.listOfEdges.contains(edge2)  && !p.isUsed()) {
                specificEdges.add(p);
            }
        }

        return specificEdges;
    }

    public static List<PuzzlePiece> getSpecificPieces(List<PuzzlePiece> puzzle, Edge edge1, Edge edge2, Edge edge3) {
        List<PuzzlePiece> specificEdges = new ArrayList<>();
        for (PuzzlePiece p : puzzle) {
            if (p.listOfEdges.contains(edge1) && p.listOfEdges.contains(edge2) && p.listOfEdges.contains(edge3)  && !p.isUsed()) {
                specificEdges.add(p);
            }
        }

        return specificEdges;
    }

    public static boolean validateNumberOfStraightEdges(List<PuzzlePiece> puzzle) {

        if (getSpecificPieces(puzzle,leftStraight).size() != getSpecificPieces(puzzle,rightStraight).size()) {
            return false;
        }

        if (getSpecificPieces(puzzle,topStraight).size() != getSpecificPieces(puzzle,bottomStraight).size()) {

            return false;

        }
        return true;
    }
    public static boolean validateNumberOfStraightEdges(List<PuzzlePiece> puzzle,int rows, int cols) {

        if (getSpecificPieces(puzzle,leftStraight).size() < rows) {
            return false;
        }
        if (getSpecificPieces(puzzle,rightStraight).size() < rows) {
            return false;
        }
        if (getSpecificPieces(puzzle,topStraight).size() < cols) {
            return false;
        }
        if (getSpecificPieces(puzzle,bottomStraight).size() < cols) {
            return false;
        }

        return true;
    }


    public static boolean validateTopLeftCorner(List<PuzzlePiece> puzzle) {

        if(getSpecificPieces(puzzle,leftStraight,topStraight).size() == 0){

            return false;
        }

        return true;
    }

    public static boolean validateBottomLeftCorner(List<PuzzlePiece> puzzle) {

        if(getSpecificPieces(puzzle,leftStraight,bottomStraight).size() == 0){

            return false;
        }
        return true;
    }

    public static boolean validateTopRightCorner(List<PuzzlePiece> puzzle)  {
        if(getSpecificPieces(puzzle,rightStraight,topStraight).size() == 0){

            return false;
        }

        return true;
    }
    public static boolean validateBottomRightCorner(List<PuzzlePiece> puzzle) {
        if(getSpecificPieces(puzzle,rightStraight,bottomStraight).size() == 0){

            return false;
        }
        return true;
    }

    public static boolean validateSumOfEdges(List<PuzzlePiece> puzzle){
        int sum = 0;
        for(PuzzlePiece p : puzzle){
            for(Edge e : p.listOfEdges){
                sum += e.getValue();
            }
        }
        return sum == 0;
    }
    public static boolean isPossibleOneRow(List<PuzzlePiece> puzzle) {
        if(getSpecificPieces(puzzle,leftStraight,topStraight,bottomStraight).size() != 0
                && getSpecificPieces(puzzle,rightStraight,topStraight,bottomStraight).size() != 0
                && validateNumberOfStraightEdges(puzzle,1,puzzle.size())) {

            return true;
        }
        return false;
    }


    public static boolean isPossibleOneColumn(List<PuzzlePiece> puzzle) {
        if(getSpecificPieces(puzzle,leftStraight,topStraight,rightStraight).size() != 0
                && getSpecificPieces(puzzle,leftStraight,bottomStraight,rightStraight).size() != 0
                && validateNumberOfStraightEdges(puzzle,puzzle.size(),1)) {

            return true;
        }
        return false;
    }

    public static boolean checkSum(PuzzlePiece[][] solvedPuzzle) {
        boolean isAllPiecesMatch = false;
        if (solvedPuzzle != null) {
            int rowsSum = 0;
            for (int row = 0; row < solvedPuzzle.length; row++) {
                rowsSum += getRowSum(solvedPuzzle,row);
            }
            int colsSum = 0;
            for (int col = 0; col < solvedPuzzle[0].length; col++) {
                colsSum += getColsSum(solvedPuzzle,col);
            }

            isAllPiecesMatch = ((rowsSum + colsSum) == 0);
        }
        return isAllPiecesMatch;

    }

    private static int getRowSum(PuzzlePiece[][] solvedPuzzle, int row) {
        int sum = solvedPuzzle[row][0].getLeftValue();

        for (int i = 0; i < solvedPuzzle[0].length - 1; i++) {
            sum += solvedPuzzle[row][i].getRightValue() + solvedPuzzle[row][i + 1].getLeftValue();
        }
        return sum;
    }

    private static int getColsSum(PuzzlePiece[][] solvedPuzzle,int col) {
        int sum = solvedPuzzle[0][col].getTopValue();

        for (int i = 0; i < solvedPuzzle.length - 1; i++) {
            sum += solvedPuzzle[i][col].getBottomValue() + solvedPuzzle[i + 1][col].getTopValue();

        }
        return sum;
    }
}
