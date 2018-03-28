package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.List;

public class PuzzlePiece {
    private int id;
    private Edge left;
    private Edge top;
    private Edge right;
    private Edge bottom;
    List<Edge> listOfEdges = new ArrayList<Edge>();




    public PuzzlePiece(int id,int left, int top, int right, int bottom) {
        this.id = id;
        this.left = new Edge("left",left);
        this.top = new Edge("top",top);
        this.right = new Edge("right",right);
        this.bottom = new Edge("bottom",bottom);
        listOfEdges.add(this.left);
        listOfEdges.add(this.top);
        listOfEdges.add(this.right);
        listOfEdges.add(this.bottom);
    }

    public Edge getLeft() {
        return left;
    }

    public Edge getTop() {
        return top;
    }

    public Edge getRight() {
        return right;
    }

    public Edge getBottom() {
        return bottom;
    }


    public int getId() {
        return id;
    }
}
