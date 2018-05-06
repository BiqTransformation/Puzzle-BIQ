package course.puzzle.puzzle;
/*
 * This class represents a puzzle shape 
 * that included in PuzzlePiece - the Shape will be 
 * build of 4 arguments of left , top,right , bottom
 *Author: Lior Siton
 */

public class PuzzleShape {
	private int left;
	private int top;
	private int right;
	private int bottom;
	public PuzzleShape(int left, int top, int right, int bottom) {
		
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public int getBottom() {
		return bottom;
	}
	public void setBottom(int bottom) {
		this.bottom = bottom;
	}
	@Override
	public String toString() {
		return "PuzzleShape [left=" + left + ", top=" + top + ", right=" + right + ", bottom=" + bottom + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bottom;
		result = prime * result + left;
		result = prime * result + right;
		result = prime * result + top;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuzzleShape other = (PuzzleShape) obj;
		if (bottom != other.bottom)
			return false;
		if (left != other.left)
			return false;
		if (right != other.right)
			return false;
		if (top != other.top)
			return false;
		return true;
	}
	
	
	
	
	

}
