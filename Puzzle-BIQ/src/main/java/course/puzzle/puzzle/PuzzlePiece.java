package course.puzzle.puzzle;

import java.util.Objects;

public class PuzzlePiece {
    public static int JOKER = Integer.MIN_VALUE;
    private int id;
    private int leftValue;
    private int topValue;
    private int rightValue;
    private int bottomValue;

    private PuzzleShape shape;
    private int rotateEdge = 0;

    public PuzzlePiece(int id, int left, int top, int right, int bottom) {
        this.id = id;

        this.shape = new PuzzleShape(new int[]{left, top, right, bottom});
        this.leftValue = left;
        this.topValue = top;
        this.rightValue = right;
        this.bottomValue = bottom;
    }

    public PuzzlePiece(int id, int[] edges) {
        this.id = id;
        this.shape = new PuzzleShape(edges);
        this.leftValue = edges[0];
        this.topValue = edges[1];
        this.rightValue = edges[2];
        this.bottomValue = edges[3];

    }

    public int getRotateEdge() {
        return rotateEdge;
    }

    public PuzzleShape getShape() {
        return shape;
    }

    public void setShape() {
        this.shape = new PuzzleShape(new int[]{leftValue,topValue,rightValue,bottomValue});
    }

    public void setRotateEdge(int rotateEdge) {
        this.rotateEdge = rotateEdge;
    }

    public int getLeftValue() {
        return leftValue;
    }

    public int getTopValue() {
        return topValue;
    }

    public int getRightValue() {
        return rightValue;
    }

    public int getBottomValue() {
        return bottomValue;
    }

    public void setLeftValue(int leftValue) {
        this.leftValue = leftValue;
    }

    public void setTopValue(int topValue) {
        this.topValue = topValue;
    }

    public void setRightValue(int rightValue) {
        this.rightValue = rightValue;
    }

    public void setBottomValue(int bottomValue) {
        this.bottomValue = bottomValue;
    }


    public int getId() {
        return id;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PuzzlePiece)) return false;
        PuzzlePiece PuzzlePiece = (PuzzlePiece) o;
        return Objects.equals(getId(), PuzzlePiece.getId());
    }

    public boolean isAllEdgesEquals() {
        return this.getLeftValue() == this.getTopValue() && this.getLeftValue() == this.getRightValue() && this.getLeftValue() == this.getBottomValue();
    }

    public boolean isOposEdgesEquals(PuzzlePiece p) {
        return p.getLeftValue() == p.getRightValue() && p.getTopValue() == p.getBottomValue();
    }

    public PuzzlePiece rotatePiece(PuzzlePiece p, int angle) {
        switch (angle) {
            case 90:
                return firstRotate(p);
            case 180:
                return secondRotate(p);
            case 270:
                return thirdRotate(p);
            default:
                return p;

        }
    }

    public PuzzlePiece firstRotate(PuzzlePiece p) {
        PuzzlePiece p1 = new PuzzlePiece(p.getId(), p.getBottomValue(), p.getLeftValue(), p.getTopValue(), p.getRightValue());
        p1.setRotateEdge(90);
        return p1;
    }

    public PuzzlePiece secondRotate(PuzzlePiece p) {
        PuzzlePiece p1 = new PuzzlePiece(p.getId(), p.getRightValue(), p.getBottomValue(), p.getLeftValue(), p.getTopValue());
        p1.setRotateEdge(180);
        return p1;
    }

    public PuzzlePiece thirdRotate(PuzzlePiece p) {
        PuzzlePiece p1 = new PuzzlePiece(p.getId(), p.getTopValue(), p.getRightValue(), p.getBottomValue(), p.getLeftValue());
        p1.setRotateEdge(270);
        return p1;
    }

	@Override
	public String toString() {
		String toPrint = "\n" + id + " " + " [" + rotateEdge + "] "
				+ getLeftValue() + " "
				+ getTopValue() + " "
				+ getRightValue() + " " + getBottomValue();
		return toPrint;
	}
}
