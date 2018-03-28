package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle {
    private List<PuzzlePiece> puzzle;

    public Puzzle(List<PuzzlePiece> puzzle) {
        this.puzzle = puzzle;
    }

    public Map<Integer, Integer> getPossibleSolutions() {
        Map<Integer, Integer> solutions = new HashMap<Integer, Integer>();
        int puzzleSize = puzzle.size();
        for(int i = 1; i <= puzzleSize;i++ ){

        }

        return solutions;
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
        Edge leftStraight = new Edge("left", 0);
         Edge rightStraight = new Edge("right", 0);

        if (getListOfPiecesWithSpecificEdge(leftStraight).size() != getListOfPiecesWithSpecificEdge(rightStraight).size()) {
            return false;
        }
        Edge topStraight = new Edge("top", 0);
        Edge bottomStraight = new Edge("bottom", 0);

        if (getListOfPiecesWithSpecificEdge(topStraight).size() != getListOfPiecesWithSpecificEdge(bottomStraight).size()) {
            System.out.println(Parameters.MISSING_CORNER_TL);
            return false;

        }
        return true;
    }
    public boolean validateNumberOfStraightEdges(int width, int height) {
        Edge leftStraight = new Edge("left", 0);
        Edge rightStraight = new Edge("right", 0);
        Edge topStraight = new Edge("top", 0);
        Edge bottomStraight = new Edge("bottom", 0);

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

}
