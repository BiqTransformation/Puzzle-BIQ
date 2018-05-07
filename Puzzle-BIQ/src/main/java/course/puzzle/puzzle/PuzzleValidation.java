package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.List;

import static course.puzzle.puzzle.PuzzlePiece.JOKER;

/**
 * @author Svetlana
 * Verify that puzzle is valid - has enough corners, straight edges, sum of edges = 0
 */
public class PuzzleValidation {

    private static PuzzleShape leftStraight = new PuzzleShape(new int[]{0,JOKER,JOKER,JOKER});
    private static PuzzleShape rightStraight = new PuzzleShape(new int[]{JOKER,0,JOKER,JOKER});
    private static PuzzleShape topStraight = new PuzzleShape(new int[]{JOKER,JOKER,0,JOKER});
    private static PuzzleShape bottomStraight = new PuzzleShape(new int[]{JOKER,JOKER,JOKER,0});
    private static PuzzleShape leftTopCorner = new PuzzleShape(new int[]{0,0,JOKER,JOKER});
    private static PuzzleShape rightTopCorner = new PuzzleShape(new int[]{JOKER,0,0,JOKER});
    private static PuzzleShape rightBottomCorner = new PuzzleShape(new int[]{JOKER,JOKER,0,0});
    private static PuzzleShape leftBottomCorner = new PuzzleShape(new int[]{0,JOKER,JOKER,0});
    private Puzzle puzzle;

    public PuzzleValidation(Puzzle puzzle) {
        this.puzzle = puzzle;
    }




    public static boolean validateNumberOfStraightEdges(List<PuzzlePiece> puzzle,int rows, int cols) {

        if (getPiecesMatchToShape(puzzle,leftStraight).size() < rows) {
            return false;
        }
        if (getPiecesMatchToShape(puzzle,rightStraight).size() < rows) {
            return false;
        }
        if (getPiecesMatchToShape(puzzle,topStraight).size() < cols) {
            return false;
        }
        if (getPiecesMatchToShape(puzzle,bottomStraight).size() < cols) {
            return false;
        }

        return true;
    }

    public static boolean isPossibleOneRow(List<PuzzlePiece> puzzle) {
        if(getPiecesMatchToShape(puzzle,new PuzzleShape(new int[]{0,0,JOKER,0})).size() != 0
                && getPiecesMatchToShape(puzzle,new PuzzleShape(new int[]{JOKER,0,0,0})).size() != 0
                && validateNumberOfStraightEdges(puzzle,1,puzzle.size())) {

            return true;
        }
        return false;
    }


    public static boolean isPossibleOneColumn(List<PuzzlePiece> puzzle) {
        if(getPiecesMatchToShape(puzzle,new PuzzleShape(new int[]{0,0,0,JOKER})).size() != 0
                && getPiecesMatchToShape(puzzle,new PuzzleShape(new int[]{0,JOKER,0,0})).size() != 0
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

    public static boolean validateNumberOfStraightEdges(List<PuzzlePiece> puzzle) {

        if (getPiecesMatchToShape(puzzle,leftStraight).size() != getPiecesMatchToShape(puzzle,rightStraight).size()) {
            return false;
        }

        if (getPiecesMatchToShape(puzzle,topStraight).size() != getPiecesMatchToShape(puzzle,bottomStraight).size()) {

            return false;

        }
        return true;
    }

    public static boolean validateSumOfEdges(List<PuzzlePiece> puzzle){
        int sum = 0;
        for(PuzzlePiece p : puzzle){

            sum += p.getLeftValue() + p.getRightValue()+ p.getTopValue() + p.getBottomValue();

        }
        return sum == 0;
    }

    public static boolean validateTopLeftCorner(List<PuzzlePiece> puzzle) {

        if(getPiecesMatchToShape(puzzle,leftTopCorner).size() == 0){

            return false;
        }

        return true;
    }

    public static boolean validateBottomLeftCorner(List<PuzzlePiece> puzzle) {

        if(getPiecesMatchToShape(puzzle,leftBottomCorner).size() == 0){

            return false;
        }
        return true;
    }

    public static boolean validateTopRightCorner(List<PuzzlePiece> puzzle)  {
        if(getPiecesMatchToShape(puzzle,rightTopCorner).size() == 0){

            return false;
        }

        return true;
    }
    public static boolean validateBottomRightCorner(List<PuzzlePiece> puzzle) {
        if(getPiecesMatchToShape(puzzle,rightBottomCorner).size() == 0){

            return false;
        }
        return true;
    }
    //==================================================================================
//                    Private methods
//    ================================================================================

    private static List<PuzzlePiece> getPiecesMatchToShape(List<PuzzlePiece> puzzle, PuzzleShape shape) {
        List<PuzzlePiece> specificEdges = new ArrayList<PuzzlePiece>();
        for (PuzzlePiece p : puzzle) {
            if (shape.isMatch(p)) {
                specificEdges.add(p);
            }
        }

        return specificEdges;
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
