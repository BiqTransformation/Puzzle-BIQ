package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PuzzlePiece {
	private int id;
	private Edge left;
	private Edge top;
	private Edge right;
	private Edge bottom;
	List<Edge> listOfEdges = new ArrayList<Edge>();
	private int rotateEdge=0;	

	public PuzzlePiece(int id, int left, int top, int right, int bottom) {
		this.id = id;
		this.left = new Edge("left", left);
		this.top = new Edge("top", top);
		this.right = new Edge("right", right);
		this.bottom = new Edge("bottom", bottom);
		listOfEdges.add(this.left);
		listOfEdges.add(this.top);
		listOfEdges.add(this.right);
		listOfEdges.add(this.bottom);
	}

	public int getRotateEdge() {
		return rotateEdge;
	}

	public void setRotateEdge(int rotateEdge) {
		this.rotateEdge = rotateEdge;
	}

	public int getLeftValue() {
		return left.getValue();
	}

	public Edge getLeft() {
		return left;
	}

	public int getTopValue() {
		return top.getValue();
	}

	public Edge getTop() {
		return top;
	}

	public int getRightValue() {
		return right.getValue();
	}

	public Edge getRight() {
		return right;
	}

	public int getBottomValue() {
		return bottom.getValue();
	}

	public Edge getBottom() {
		return bottom;
	}

	public  int getId() {
		return id;
	}


	@Override
	public int hashCode() {
		return id;
	}


    @Override
	 public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof PuzzlePiece)) return false;
	        PuzzlePiece PuzzlePiece = (PuzzlePiece) o;
	        return Objects.equals(getId(), PuzzlePiece.getId());
	    }
    
    public boolean listOfEdgesEquals(PuzzlePiece p){
    	return p.listOfEdges.equals(listOfEdges);
    }
    
    public boolean isAllEdgesEquals(){
    	return this.getLeftValue()==this.getTopValue() && this.getLeftValue()==this.getRightValue() && this.getLeftValue()==this.getBottomValue();
    }
    
    public boolean isOposEdgesEquals(PuzzlePiece p){
    	return p.getLeftValue()==p.getRightValue() && p.getTopValue()==p.getBottomValue();
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
		PuzzlePiece p1 = new PuzzlePiece(p.getId(), p.getBottomValue(), p.getLeftValue(), p.getTopValue(), p.getRightValue());
		p1.setRotateEdge(270);
		return p1;
	}

	@Override
	public String toString() {
		String toPrint = "\n" + id + " " + " [" + rotateEdge + "] " + getLeftValue() + " " + getTopValue() + " " + getRightValue() + " " + getBottomValue();
		return toPrint;
	}
}
