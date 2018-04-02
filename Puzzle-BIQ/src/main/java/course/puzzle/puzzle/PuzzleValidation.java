package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.List;

public class PuzzleValidation {
    private static Edge leftStraight = new Edge("left", 0);
    private static Edge rightStraight = new Edge("right", 0);
    private static Edge topStraight = new Edge("top", 0);
    private static Edge bottomStraight = new Edge("bottom", 0);



    public static List<PuzzlePiece> getListOfPiecesWithSpecificEdge(List<PuzzlePiece> puzzle,Edge edge) {
        List<PuzzlePiece> specificEdges = new ArrayList<PuzzlePiece>();
        for (PuzzlePiece p : puzzle) {
            if (p.listOfEdges.contains(edge)) {
                specificEdges.add(p);
            }
        }

        return specificEdges;
    }
    public static List<PuzzlePiece> getListOfPiecesWithSpecificEdge(List<PuzzlePiece> puzzle,Edge edge1,Edge edge2) {
        List<PuzzlePiece> specificEdges = new ArrayList<PuzzlePiece>();
        for (PuzzlePiece p : puzzle) {
            if (p.listOfEdges.contains(edge1) && p.listOfEdges.contains(edge2)) {
                specificEdges.add(p);
            }
        }

        return specificEdges;
    }

    public static List<PuzzlePiece> getListOfPiecesWithSpecificEdge(List<PuzzlePiece> puzzle,Edge edge1,Edge edge2, Edge edge3) {
        List<PuzzlePiece> specificEdges = new ArrayList<PuzzlePiece>();
        for (PuzzlePiece p : puzzle) {
            if (p.listOfEdges.contains(edge1) && p.listOfEdges.contains(edge2) && p.listOfEdges.contains(edge3)) {
                specificEdges.add(p);
            }
        }

        return specificEdges;
    }

    public static boolean validateNumberOfStraightEdges(List<PuzzlePiece> puzzle) {

        if (getListOfPiecesWithSpecificEdge(puzzle,leftStraight).size() != getListOfPiecesWithSpecificEdge(puzzle,rightStraight).size()) {
            return false;
        }

        if (getListOfPiecesWithSpecificEdge(puzzle,topStraight).size() != getListOfPiecesWithSpecificEdge(puzzle,bottomStraight).size()) {
            System.out.println(Parameters.WRONG_NUMBER_OF_STRAIGHT_EDGES);
            return false;

        }
        return true;
    }
    public static boolean validateNumberOfStraightEdges(List<PuzzlePiece> puzzle,int width, int height) {

        if (getListOfPiecesWithSpecificEdge(puzzle,leftStraight).size() < height - 2) {
            return false;
        }
        if (getListOfPiecesWithSpecificEdge(puzzle,rightStraight).size() < height - 2) {
            return false;
        }
        if (getListOfPiecesWithSpecificEdge(puzzle,topStraight).size() < width - 2) {
            return false;
        }
        if (getListOfPiecesWithSpecificEdge(puzzle,bottomStraight).size() < width - 2) {
            return false;
        }

        return true;
    }


    public static boolean validateTopLeftCorner(List<PuzzlePiece> puzzle) {

        if(getListOfPiecesWithSpecificEdge(puzzle,leftStraight,topStraight).size() == 0){
            System.out.println(Parameters.MISSING_CORNER_TL);
            return false;
        }

        return true;
    }

    public static boolean validateBottomLeftCorner(List<PuzzlePiece> puzzle) {

        if(getListOfPiecesWithSpecificEdge(puzzle,leftStraight,bottomStraight).size() == 0){
            System.out.println(Parameters.MISSING_CORNER_BL);
            return false;
        }
        return true;
    }

    public static boolean validateTopRightCorner(List<PuzzlePiece> puzzle) {
        if(getListOfPiecesWithSpecificEdge(puzzle,rightStraight,topStraight).size() == 0){
            System.out.println(Parameters.MISSING_CORNER_TR);
            return false;
        }

        return true;
    }
    public static boolean validateBottomRightCorner(List<PuzzlePiece> puzzle) {
        if(getListOfPiecesWithSpecificEdge(puzzle,rightStraight,bottomStraight).size() == 0){
            System.out.println(Parameters.MISSING_CORNER_BR);
            return false;
        }
        return true;
    }
    public static boolean isPossibleOneRow(List<PuzzlePiece> puzzle) {
        if(getListOfPiecesWithSpecificEdge(puzzle,leftStraight,topStraight,bottomStraight).size() != 0
                && getListOfPiecesWithSpecificEdge(puzzle,rightStraight,topStraight,bottomStraight).size() != 0) {

            return true;
        }
        return false;
    }


    public static boolean isPossibleOneColumn(List<PuzzlePiece> puzzle) {
        if(getListOfPiecesWithSpecificEdge(puzzle,leftStraight,topStraight,rightStraight).size() != 0
                && getListOfPiecesWithSpecificEdge(puzzle,leftStraight,bottomStraight,rightStraight).size() != 0) {

            return true;
        }
        return false;
    }
}
