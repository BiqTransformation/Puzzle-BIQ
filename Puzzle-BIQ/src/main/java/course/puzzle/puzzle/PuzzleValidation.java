package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.List;

public class PuzzleValidation extends Puzzle {
    private Edge leftStraight = new Edge("left", 0);
    private Edge rightStraight = new Edge("right", 0);
    private Edge topStraight = new Edge("top", 0);
    private Edge bottomStraight = new Edge("bottom", 0);

    public PuzzleValidation(List<PuzzlePiece> puzzle) {
        super(puzzle);
    }

    public List<PuzzlePiece> getListOfPiecesWithSpecificEdge(Edge edge) {
        List<PuzzlePiece> specificEdges = new ArrayList<PuzzlePiece>();
        for (PuzzlePiece p : puzzle) {
            if (p.listOfEdges.contains(edge)) {
                specificEdges.add(p);
            }
        }

        return specificEdges;
    }
    public List<PuzzlePiece> getListOfPiecesWithSpecificEdge(Edge edge1,Edge edge2) {
        List<PuzzlePiece> specificEdges = new ArrayList<PuzzlePiece>();
        for (PuzzlePiece p : puzzle) {
            if (p.listOfEdges.contains(edge1) && p.listOfEdges.contains(edge2)) {
                specificEdges.add(p);
            }
        }

        return specificEdges;
    }

    public List<PuzzlePiece> getListOfPiecesWithSpecificEdge(Edge edge1,Edge edge2, Edge edge3) {
        List<PuzzlePiece> specificEdges = new ArrayList<PuzzlePiece>();
        for (PuzzlePiece p : puzzle) {
            if (p.listOfEdges.contains(edge1) && p.listOfEdges.contains(edge2) && p.listOfEdges.contains(edge3)) {
                specificEdges.add(p);
            }
        }

        return specificEdges;
    }

    public boolean validateNumberOfStraightEdges() {

        if (getListOfPiecesWithSpecificEdge(leftStraight).size() != getListOfPiecesWithSpecificEdge(rightStraight).size()) {
            return false;
        }

        if (getListOfPiecesWithSpecificEdge(topStraight).size() != getListOfPiecesWithSpecificEdge(bottomStraight).size()) {
            System.out.println(Parameters.WRONG_NUMBER_OF_STRAIGHT_EDGES);
            return false;

        }
        return true;
    }
    public boolean validateNumberOfStraightEdges(int width, int height) {

        if (getListOfPiecesWithSpecificEdge(leftStraight).size() < height - 2) {
            return false;
        }
        if (getListOfPiecesWithSpecificEdge(rightStraight).size() < height - 2) {
            return false;
        }
        if (getListOfPiecesWithSpecificEdge(topStraight).size() < width - 2) {
            return false;
        }
        if (getListOfPiecesWithSpecificEdge(bottomStraight).size() < width - 2) {
            return false;
        }

        return true;
    }

    public boolean validateCorners() {

        if(getListOfPiecesWithSpecificEdge(leftStraight,topStraight).size() == 0){
            System.out.println(Parameters.MISSING_CORNER_TL);
            return false;
        }
        if(getListOfPiecesWithSpecificEdge(leftStraight,bottomStraight).size() == 0){
            System.out.println(Parameters.MISSING_CORNER_BL);
            return false;
        }
        if(getListOfPiecesWithSpecificEdge(rightStraight,topStraight).size() == 0){
            System.out.println(Parameters.MISSING_CORNER_TR);
            return false;
        }
        if(getListOfPiecesWithSpecificEdge(rightStraight,bottomStraight).size() == 0){
            System.out.println(Parameters.MISSING_CORNER_BR);
            return false;
        }
        return true;
    }
}
