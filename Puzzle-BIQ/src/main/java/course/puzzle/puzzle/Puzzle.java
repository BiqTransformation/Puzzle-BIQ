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
    
    
    
    public boolean getRotate() {
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

    public void validatePuzzle() {
   if(!isRotate){
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

    }
        

}
