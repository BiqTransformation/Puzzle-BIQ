package course.puzzle.puzzle;

import java.util.Objects;
/*
 * This class represents a puzzle piece
 * it includes the puzzle shape inside
 * @author Svetlana
 */
public class PuzzlePiece {
    public static int JOKER = Integer.MIN_VALUE;
    private int id;
//    private int[] shape.getEdges();

    private PuzzleShape shape;
    private int rotateAngle = 0;

    public PuzzlePiece(int id, int left, int top, int right, int bottom) {
        this.id = id;
                this.shape = new PuzzleShape(new int[]{left, top, right, bottom});

    }

    public PuzzlePiece(int id, int[] edges) {
        this.id = id;
        
        this.shape = new PuzzleShape(edges);

    }
public int[] getEdges(){
        return shape.getEdges();
}
   
    public int getRotateAngle() {
        return rotateAngle;
    }

    public PuzzleShape getShape() {
        return shape;
    }

    public void setShape() {
        this.shape = new PuzzleShape(shape.getEdges());
    }

    public void setRotateAngle(int rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    public int getLeftValue() {
        return shape.getEdges()[0];
    }

    public int getTopValue() {
        return shape.getEdges()[1];
    }

    public int getRightValue() {
        return shape.getEdges()[2];
    }

    public int getBottomValue() {
        return shape.getEdges()[3];
    }

    public void setLeftValue(int leftValue) {
        this.shape.getEdges()[0] = leftValue;
    }

    public void setTopValue(int topValue) {
        this.shape.getEdges()[1] = topValue;
    }

    public void setRightValue(int rightValue) {
        this.shape.getEdges()[2] = rightValue;
    }

    public void setBottomValue(int bottomValue) {
        this.shape.getEdges()[3] = bottomValue;
    }


    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PuzzlePiece)) return false;
        PuzzlePiece PuzzlePiece = (PuzzlePiece) o;
        return Objects.equals(getId(), PuzzlePiece.getId());
    }

    @Override
    public int hashCode() {
        return id;
    }

    public boolean isAllEdgesEquals() {
        return this.shape.getEdges()[0]== this.shape.getEdges()[1] && this.shape.getEdges()[0] == this.shape.getEdges()[2] && this.shape.getEdges()[0] == this.shape.getEdges()[3];
    }

    public boolean isOposEdgesEquals(PuzzlePiece p) {
        return p.shape.getEdges()[0] == p.shape.getEdges()[2] && p.shape.getEdges()[1] == p.shape.getEdges()[3];
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
        PuzzlePiece p1 = new PuzzlePiece(p.getId(), p.getEdges()[3], p.getEdges()[0], p.getEdges()[1], p.getEdges()[2]);
        p1.setRotateAngle(90);
        return p1;
    }

    public PuzzlePiece secondRotate(PuzzlePiece p) {
        PuzzlePiece p1 = new PuzzlePiece(p.getId(), p.getEdges()[2], p.getEdges()[3], p.getEdges()[0], p.getEdges()[1]);
        p1.setRotateAngle(180);
        return p1;
    }

    public PuzzlePiece thirdRotate(PuzzlePiece p) {
        PuzzlePiece p1 = new PuzzlePiece(p.getId(), p.getEdges()[1], p.getEdges()[2], p.getEdges()[3], p.getEdges()[0]);
        p1.setRotateAngle(270);
        return p1;
    }

	@Override
	public String toString() {
		String toPrint = "\n" + id + " " + " [" + rotateAngle + "] "
				+ shape.getEdges()[0] + " "
				+ shape.getEdges()[1] + " "
				+ shape.getEdges()[2] + " " + shape.getEdges()[3];
		return toPrint;
	}
}
