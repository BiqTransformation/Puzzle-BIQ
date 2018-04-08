package course.puzzle.puzzle;

import course.puzzle.file.FileOutput;

import java.io.IOException;
import java.util.List;

public class Puzzle {
    protected List<PuzzlePiece> puzzle;


    public Puzzle(List<PuzzlePiece> puzzle) {
        this.puzzle = puzzle;
        validatePuzzle();

    }

    public void validatePuzzle() {
        boolean isValid = true;
        if (!PuzzleValidation.validateNumberOfStraightEdges(puzzle)) {
            FileOutput.printToOutputFile(Parameters.WRONG_NUMBER_OF_STRAIGHT_EDGES);
            isValid = false;

        }
        if (!PuzzleValidation.validateTopLeftCorner(puzzle)) {
            FileOutput.printToOutputFile(Parameters.MISSING_CORNER_TL);
            isValid = false;
        }
        if (!PuzzleValidation.validateTopRightCorner(puzzle)) {
            FileOutput.printToOutputFile(Parameters.MISSING_CORNER_TR);
            isValid = false;
        }
        if (!PuzzleValidation.validateBottomRightCorner(puzzle)) {
            FileOutput.printToOutputFile(Parameters.MISSING_CORNER_BR);
            isValid = false;
        }
        if (!PuzzleValidation.validateBottomLeftCorner(puzzle)) {
            FileOutput.printToOutputFile(Parameters.MISSING_CORNER_BL);
            isValid = false;
        }
        if (!PuzzleValidation.validateSumOfEdges(puzzle)) {
            FileOutput.printToOutputFile(Parameters.SUM_OF_EDGES_IS_NOT_ZERO);
            isValid = false;
        }
        if(!isValid){
            return;
        }
    }


}
