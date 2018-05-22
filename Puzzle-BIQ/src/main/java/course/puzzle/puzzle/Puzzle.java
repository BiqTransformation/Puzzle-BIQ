package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This class represents a puzzle package
 * @author Svetlana
 */
public class Puzzle {

    private List<PuzzlePiece> puzzlePieces;
    private List<String> errors = new ArrayList<>();
    private boolean isRotate;
    private Map<PuzzleShape, List<PuzzlePiece>> allPiecesMap = new HashMap<>();

    public Puzzle(List<PuzzlePiece> puzzlePieceList, boolean rotate) {
        this.isRotate = rotate;

        puzzlePieces = puzzlePieceList;

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

    public List<PuzzlePiece> getPuzzle() {
        return puzzlePieces;
    }

    public List<String> getErrors() {
        return errors;
    }

    public PuzzlePiece getPieceById(int id) {

        for (PuzzlePiece p : puzzlePieces) {
            if (p.getId() == id) {
                return p;
            }

        }
        return null;
    }

    public void addError(String error) {
        errors.add(error);
    }

    public void validatePuzzle() {

        if (!PuzzleValidation.validateTopLeftCorner(puzzlePieces, isRotate)) {
            addError(LogMessages.MISSING_CORNER_TL);

        }
        if (!PuzzleValidation.validateTopRightCorner(puzzlePieces, isRotate)) {
            addError(LogMessages.MISSING_CORNER_TR);

        }
        if (!PuzzleValidation.validateBottomLeftCorner(puzzlePieces, isRotate)) {
            addError(LogMessages.MISSING_CORNER_BL);

        }
        if (!PuzzleValidation.validateBottomRightCorner(puzzlePieces, isRotate)) {
            addError(LogMessages.MISSING_CORNER_BR);

        }
        if (!PuzzleValidation.validateSumOfEdges(puzzlePieces)) {
            addError(LogMessages.SUM_OF_EDGES_IS_NOT_ZERO);

        }
       }

    private void indexer() {
        List<PuzzlePiece> allPieces;
        if (isRotate) {
            allPieces = rotateAll(puzzlePieces);
        } else {
            allPieces = puzzlePieces;
        }

        for (PuzzlePiece p : allPieces) {

            List<PuzzlePiece> identicalPieces = allPiecesMap.get(p.getShape());
            if (identicalPieces == null) {
                identicalPieces = new ArrayList<>();
                allPiecesMap.put(p.getShape(), identicalPieces);
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
