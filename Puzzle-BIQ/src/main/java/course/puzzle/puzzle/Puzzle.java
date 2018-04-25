package course.puzzle.puzzle;

import course.puzzle.file.FileOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Puzzle {
//TODO implement rotate -Lior

    private List<PuzzlePiece> puzzle;
    private List<String> errors = new ArrayList<>();
    private boolean isRotate;
    
    public Puzzle(List<PuzzlePiece> puzzle,boolean rotate) {
        this.isRotate=rotate;
    	this.puzzle = puzzle;
        this.isRotate=isRotate;
        validatePuzzle();
    }
    
    
    
    public boolean isRotate() {
		return isRotate;
	}



	public void setRotate(boolean isRotate) {
		this.isRotate = isRotate;
	}



	public List<PuzzlePiece> getPuzzle() {
        return puzzle;
    }
    public List<String> getErrors() {
        return errors;
    }
    public PuzzlePiece getPieceById(int id){

        for(PuzzlePiece p : puzzle){
            if(p.getId() == id){
                return p;
            }

        }
        return null;
    }
    public void addError(String error) {
        errors.add(error);
    }

    public void validatePuzzle() {
   
        if (!PuzzleValidation.validateNumberOfStraightEdges(puzzle)) {
            errors.add(PuzzleErrors.WRONG_NUMBER_OF_STRAIGHT_EDGES);
           }
        if (!PuzzleValidation.validateTopLeftCorner(puzzle)) {
            errors.add(PuzzleErrors.MISSING_CORNER_TL);
         }
        if (!PuzzleValidation.validateTopRightCorner(puzzle)) {
            errors.add(PuzzleErrors.MISSING_CORNER_TR);

        }
        if (!PuzzleValidation.validateBottomRightCorner(puzzle)) {
            errors.add(PuzzleErrors.MISSING_CORNER_BR);

        }
        if (!PuzzleValidation.validateBottomLeftCorner(puzzle)) {
            errors.add(PuzzleErrors.MISSING_CORNER_BL);
         }
        if (!PuzzleValidation.validateSumOfEdges(puzzle)) {
            errors.add(PuzzleErrors.SUM_OF_EDGES_IS_NOT_ZERO);
        }
    }
        
	public List<PuzzlePiece> rotateAll() {
		List<PuzzlePiece> allPieces = new ArrayList<>();
		if (isRotate) {
			for (PuzzlePiece p : puzzle) {
				allPieces.add(p);
				if (!p.isAllEdgesEquals(p)) {
					PuzzlePiece temp1 = firstRotate(p);
					allPieces.add(temp1);				
					if (!p.isOposEdgesEquals(p)) {
						PuzzlePiece temp2 = secondRotate(p);
						allPieces.add(temp2);
					}
					PuzzlePiece temp3 = thirdRotate(p);
					allPieces.add(temp3);
				}
			}
		}
		return allPieces;
	}
        		

	private PuzzlePiece firstRotate(PuzzlePiece p) {
		PuzzlePiece p1 = new PuzzlePiece(p.getId(),p.getBottomValue(),p.getLeftValue(),p.getTopValue(),p.getRightValue());
		p1.setRotateEdge(90);		
		return p1;
	}
	
	private PuzzlePiece secondRotate(PuzzlePiece p) {
		PuzzlePiece p1 = new PuzzlePiece(p.getId(),p.getRightValue(),p.getBottomValue(),p.getLeftValue(),p.getTopValue());
		p1.setRotateEdge(180);		
		return p1;
	}
	
	private PuzzlePiece thirdRotate(PuzzlePiece p) {
		PuzzlePiece p1 = new PuzzlePiece(p.getId(),p.getBottomValue(),p.getLeftValue(),p.getTopValue(),p.getRightValue());
		p1.setRotateEdge(270);		
		return p1;
	}
	
	


}
