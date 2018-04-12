package course.puzzle.puzzle;

import course.puzzle.file.FileOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    protected List<PuzzlePiece> puzzle;


    public Puzzle(List<PuzzlePiece> puzzle) {
        this.puzzle = puzzle;
        validatePuzzle();

    }

    public void validatePuzzle() {
        boolean isValid = true;
        
        StringBuilder stringBuilder = new StringBuilder();
        if (!PuzzleValidation.validateNumberOfStraightEdges(puzzle)) {
            stringBuilder.append(Parameters.WRONG_NUMBER_OF_STRAIGHT_EDGES);
            isValid = false;

        }
        if (!PuzzleValidation.validateTopLeftCorner(puzzle)) {
            stringBuilder.append(Parameters.MISSING_CORNER_TL);
            isValid = false;
        }
        if (!PuzzleValidation.validateTopRightCorner(puzzle)) {
            stringBuilder.append(Parameters.MISSING_CORNER_TR);
            isValid = false;
        }
        if (!PuzzleValidation.validateBottomRightCorner(puzzle)) {
            stringBuilder.append(Parameters.MISSING_CORNER_BR);
            isValid = false;
        }
        if (!PuzzleValidation.validateBottomLeftCorner(puzzle)) {
            stringBuilder.append(Parameters.MISSING_CORNER_BL);
            isValid = false;
        }
        if (!PuzzleValidation.validateSumOfEdges(puzzle)) {
            stringBuilder.append(Parameters.SUM_OF_EDGES_IS_NOT_ZERO);
            isValid = false;
        }
        if(!isValid){
           FileOutput.printToOutputFile(stringBuilder.toString());
            return;
        }
    }


}
