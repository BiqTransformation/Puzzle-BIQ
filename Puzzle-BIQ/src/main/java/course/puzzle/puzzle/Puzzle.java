package course.puzzle.puzzle;

import course.puzzle.file.FileOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Puzzle {
//TODO implement rotate -Lior

    private List<PuzzlePiece> puzzle;
    private List<String> errors = new ArrayList<>();

    public Puzzle(List<PuzzlePiece> puzzle) {
        this.puzzle = puzzle;
        validatePuzzle();

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


}
