package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Puzzle {


    private List<PuzzlePiece> puzzlePieces;
    private List<String> errors = new ArrayList<>();
    private boolean isRotate;
    private AtomicBoolean solved = new AtomicBoolean();



    Map<PuzzleShape,List<PuzzlePiece>> allPiecesMap = new HashMap<>();
    
    public Puzzle(List<PuzzlePiece> puzzle,boolean rotate) {
        this.isRotate=rotate;
    	this.puzzlePieces = puzzle;
        this.isRotate=isRotate;
        validatePuzzle();
        indexer();
    }

    public Map<PuzzleShape, List<PuzzlePiece>> getAllPiecesMap() {
        return allPiecesMap;
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
    private void indexer(){
        List<PuzzlePiece> allPieces = new ArrayList<>();
        if(isRotate){
            allPieces = rotateAll(puzzlePieces);
        }
        else{
            allPieces = puzzlePieces;
        }

        for(PuzzlePiece p : allPieces){

            List<PuzzlePiece> identicalPieces = allPiecesMap.get(p.getShape());
            if(identicalPieces == null){
                identicalPieces = new ArrayList<>();
                allPiecesMap.put(p.getShape(),identicalPieces);
            }

            identicalPieces.add(p);
        }

    }
    public List<PuzzlePiece> rotateAll(List<PuzzlePiece> list) {
        List<PuzzlePiece> allPieces = new ArrayList<>();


        for (PuzzlePiece p : list) {
            allPieces.add(p);
            PuzzlePiece p90 = p.firstRotate(p);
            PuzzlePiece p180 = p.secondRotate(p);
            PuzzlePiece p270 = p.thirdRotate(p);
            if (!p.isAllEdgesEquals()) {
                if (!p.isOposEdgesEquals(p)) {
                    allPieces.add(p90);
                    allPieces.add(p180);
                    allPieces.add(p270);
                } else {
                    allPieces.add(p90);
                }
            }

        }

        return allPieces;
    }
}
