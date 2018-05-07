package course.puzzle.puzzle;

import static course.puzzle.puzzle.PuzzlePiece.JOKER;

public class PuzzleShape {
    private int edges[];


    public PuzzleShape(int[] edges) {
        this.edges = edges;

    }

    public int getLeft() {
        return edges[0];
    }

    public void setLeft(int left) {
        this.edges[0] = left;
    }

    public int getTop() {
        return edges[1];
    }

    public void setTop(int top) {
        this.edges[1] = top;
    }

    public int getRight() {
        return edges[2];
    }

    public void setRight(int right) {
        this.edges[2] = right;
    }

    public int getBottom() {
        return edges[3];
    }

    public void setBottom(int bottom) {
        this.edges[3] = bottom;
    }
    public boolean isMatch(PuzzlePiece p) {
        boolean match =
                edgeMatch(getLeft(), p.getLeftValue()) &&
                        edgeMatch(getTop(), p.getTopValue()) &&
                        edgeMatch(getRight(), p.getRightValue()) &&
                        edgeMatch(getBottom(), p.getBottomValue());
        return match;
    }

    private boolean edgeMatch(int reqEdge, int shapeEdge) {
        boolean equal = reqEdge == JOKER || reqEdge == shapeEdge;
        return equal;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuzzleShape other = (PuzzleShape) o;
        return edges[0] == other.edges[0] && edges[1] == other.edges[1] && edges[2] == other.edges[2] && edges[3] == other.edges[3];
    }

    @Override
    public int hashCode() {
        return edges[0] * 54 + edges[1] * 21 + edges[2] * 3 + edges[3];
    }

    @Override
    public String toString() {
        return edges[0] + " " + edges[1]  + " " + edges[2]  + " " + edges[3];
    }
}
