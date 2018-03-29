package course.puzzle.puzzle;

public class SolvePuzzle {
	
	
	
	
	
	
	
	
	//check the case that piece1 exists on board and piece2 want 
	//to connect to piece1 left side with piece2 right side 
	public boolean checkRightToLeft(PuzzlePiece puzzlePiece1, PuzzlePiece puzzlePiece2) {
		return (puzzlePiece1.getLeft().getValue()) + (puzzlePiece2.getRight().getValue()) == 0;
	}
	
	//check the case that piece1 exists on board and piece2 want 
	//to connect to piece1 right side with piece2 left side 
	public boolean checkLeftToRight(PuzzlePiece puzzlePiece1, PuzzlePiece puzzlePiece2) {
		return puzzlePiece1.getRight().getValue() + puzzlePiece2.getLeft().getValue() == 0;
	}
	
	//check the case that piece1 exists on board and piece2 want 
	//to connect to piece1 top side with piece2 bottom side 
		public boolean checkTopToBottom(PuzzlePiece puzzlePiece1, PuzzlePiece puzzlePiece2) {
			return puzzlePiece1.getTop().getValue() + puzzlePiece2.getBottom().getValue() == 0;
		}
		
	//check the case that piece1 exists on board and piece2 want 
	//to connect to piece1 bottom side with piece2 top side 
		public boolean checkBottomToTop(PuzzlePiece puzzlePiece1, PuzzlePiece puzzlePiece2) {
			return puzzlePiece1.getBottom().getValue() + puzzlePiece2.getTop().getValue() == 0;
			}

}
