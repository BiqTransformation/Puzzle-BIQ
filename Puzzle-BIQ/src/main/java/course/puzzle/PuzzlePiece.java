package course.puzzle;

public class PuzzlePiece {
    private int left;
    private int top;
    private int right;
    private int bottom;
    private int id;



    public PuzzlePiece(int id,int left, int top, int right, int bottom) {
        this.id = id;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;

    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }

    public int getId() {
        return id;
    }
}
