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
    private boolean rotate;
    private List<PuzzlePiece> puzzlePieces;

    public Puzzle(boolean rotate, List<PuzzlePiece> puzzlePieceList) {
        this.rotate = rotate;
        puzzlePieces = puzzlePieceList;

    }

    public Puzzle(List<PuzzlePiece> puzzlePieces, boolean isRotate) {
        this.puzzlePieces = puzzlePieces;
        this.rotate = isRotate;
    }

    public boolean getRotate() {
        return rotate;
    }

    public void setRotate(boolean isRotate) {
        this.rotate = isRotate;
    }

    public List<PuzzlePiece> getPuzzlePieces() {
        return puzzlePieces;
    }


    public PuzzlePiece getPieceById(int id) {

        for (PuzzlePiece p : puzzlePieces) {
            if (p.getId() == id) {
                return p;
            }

        }
        return null;
    }


    public List<String> validatePuzzle() {
        List<String> errors = new ArrayList<>();
        if (!PuzzleValidation.validateTopLeftCorner(puzzlePieces, rotate)) {
            errors.add(LogMessages.MISSING_CORNER_TL);

        }
        if (!PuzzleValidation.validateTopRightCorner(puzzlePieces, rotate)) {
            errors.add(LogMessages.MISSING_CORNER_TR);

        }
        if (!PuzzleValidation.validateBottomLeftCorner(puzzlePieces, rotate)) {
            errors.add(LogMessages.MISSING_CORNER_BL);

        }
        if (!PuzzleValidation.validateBottomRightCorner(puzzlePieces, rotate)) {
            errors.add(LogMessages.MISSING_CORNER_BR);

        }
        if (!PuzzleValidation.validateSumOfEdges(puzzlePieces)) {
            errors.add(LogMessages.SUM_OF_EDGES_IS_NOT_ZERO);

        }
        return errors;
    }

    public Map<PuzzleShape, List<PuzzlePiece>> indexer() {
        Map<PuzzleShape, List<PuzzlePiece>> allPiecesMap = new HashMap<>();
        List<PuzzlePiece> allPieces;
        if (rotate) {
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
        return allPiecesMap;
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
