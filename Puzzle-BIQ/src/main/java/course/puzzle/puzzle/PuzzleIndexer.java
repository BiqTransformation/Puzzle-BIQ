package course.puzzle.puzzle;
/*
 * This class represent an indexer , it should map a shape to a list of puzzle 
 * pieces with the same shape and different id and rotate
 * Author:Lior Siton 
 *  
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PuzzleIndexer {
	
	 private Map<PuzzleShape, Set<PuzzlePiece>> pieces
     = new HashMap<>();
	 private Puzzle puzzle;
	 
//	 public PuzzleIndexer(Puzzle puzzle){
//		 this.puzzle=puzzle;
//	 }
	 
	public void add(List<PuzzlePiece> puz) {
		// List<PuzzlePiece> currPuzzle = puzzle.getPuzzle();
		if (puz.isEmpty()) {
			System.out.println("The puzzle package is empty");
		}

		for (PuzzlePiece p : puz) {	
			PuzzleShape shape =p.getShape();
			if (pieces.get(shape) == null) {
				Set<PuzzlePiece> puzzlePieces = new HashSet();
				puzzlePieces.add(p);
				pieces.put(shape, puzzlePieces);
			} else {
				Set<PuzzlePiece> curr = pieces.get(shape);
				for (PuzzlePiece piece : curr) {
					if(!piece.getShape().equals(shape)){
						break;
						}
				}				
				curr.add(p);
				pieces.put(shape, curr);

			}

		}

	}
	
	public Set<PuzzlePiece> getNeededShapes(PuzzleShape shape){
		Set<PuzzlePiece> retValue = new HashSet<>();		
		for(Map.Entry<PuzzleShape, Set<PuzzlePiece>> entry : pieces.entrySet()){
			if(entry.getKey().equals(shape)){
				retValue =entry.getValue();
			}
		}
		return retValue;
	}
		
	

	@Override
	public String toString() {
		return "PuzzleIndexer [pieces=" + pieces + "]";
	}

	public Map<PuzzleShape, Set<PuzzlePiece>> getPieces() {
		return pieces;
	}

	public void setPieces(Map<PuzzleShape, Set<PuzzlePiece>> pieces) {
		this.pieces = pieces;
	}
	
	public void printPieces(){
		for(Map.Entry<PuzzleShape,Set<PuzzlePiece>> entry :pieces.entrySet()){
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
	
	
	
}


