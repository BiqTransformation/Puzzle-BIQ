package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Puzzle {


    private List<PuzzlePiece> puzzlePieces;
    private List<String> errors = new ArrayList<>();
    private boolean isRotate;
    private AtomicBoolean solved = new AtomicBoolean();
    
    public Puzzle(List<PuzzlePiece> puzzle,boolean rotate) {
        this.isRotate=rotate;
    	this.puzzlePieces = puzzle;
        this.isRotate=isRotate;
        validatePuzzle();
    }
    
    
    
    public boolean isRotate() {
		return isRotate;
	}

	public void setRotate(boolean isRotate) {
		this.isRotate = isRotate;
	}

    public AtomicBoolean getSolved() {
        return solved;
    }

    public void setSolved(AtomicBoolean solved) {
        this.solved = solved;
    }

	public List<PuzzlePiece> getPuzzle() {
        return puzzlePieces;
    }
    public List<String> getErrors() {
        return errors;
    }
    public PuzzlePiece getPieceById(int id){

        for(PuzzlePiece p : puzzlePieces){
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
   
        if (!PuzzleValidation.validateNumberOfStraightEdges(puzzlePieces)) {
            errors.add(PuzzleErrors.WRONG_NUMBER_OF_STRAIGHT_EDGES);
           }
        if (!PuzzleValidation.validateTopLeftCorner(puzzlePieces)) {
            errors.add(PuzzleErrors.MISSING_CORNER_TL);
         }
        if (!PuzzleValidation.validateTopRightCorner(puzzlePieces)) {
            errors.add(PuzzleErrors.MISSING_CORNER_TR);

        }
        if (!PuzzleValidation.validateBottomRightCorner(puzzlePieces)) {
            errors.add(PuzzleErrors.MISSING_CORNER_BR);

        }
        if (!PuzzleValidation.validateBottomLeftCorner(puzzlePieces)) {
            errors.add(PuzzleErrors.MISSING_CORNER_BL);
         }
        if (!PuzzleValidation.validateSumOfEdges(puzzlePieces)) {
            errors.add(PuzzleErrors.SUM_OF_EDGES_IS_NOT_ZERO);
        }
    }
        
	public List<PuzzlePiece> rotateAll() {
		List<PuzzlePiece> allPieces = new ArrayList<>();
		
			for (PuzzlePiece p : puzzlePieces) {
				allPieces.add(p);
				if (!p.isAllEdgesEquals()) {
					if (!p.isOposEdgesEquals(p)) {
						PuzzlePiece temp1 = firstRotate(p);
						allPieces.add(temp1);					
						PuzzlePiece temp2 = secondRotate(p);
						allPieces.add(temp2);
						PuzzlePiece temp3 = thirdRotate(p);
						allPieces.add(temp3);
						}
					else{
						PuzzlePiece temp1 = firstRotate(p);
						allPieces.add(temp1);
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
