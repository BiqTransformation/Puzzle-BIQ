package course.puzzle.puzzle;

import course.puzzle.file.FileOutput;
import course.puzzle.puzzleManager.PuzzleManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PuzzleValidationTest {
	
	
	
    @Test
    public void badTestNumberOfLeftRightStraightEdges() {
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 1, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 1, 1, 1);
        puzzleList.add(p1);
        puzzleList.add(p2);


        assertFalse(PuzzleValidation.validateNumberOfStraightEdges(puzzleList));
    }

    @Test
    public void badTestNumberOfTopBottomStraightEdges() {
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, -1, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 1, 1, -1);
        puzzleList.add(p1);
        puzzleList.add(p2);

        assertFalse(PuzzleValidation.validateNumberOfStraightEdges(puzzleList));
    }

    @Test
    public void goodTestNumberOfLeftRightStraightEdges() {
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 1, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 1, 0, 1);
        puzzleList.add(p1);
        puzzleList.add(p2);

        assertTrue(PuzzleValidation.validateNumberOfStraightEdges(puzzleList));
    }

    @Test
    public void goodTestNumberOfTopBottomStraightEdges() {
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 1, 0, 0);
        puzzleList.add(p1);
        puzzleList.add(p2);

        assertTrue(PuzzleValidation.validateNumberOfStraightEdges(puzzleList));
    }

    @Test
    public void goodTestCorners() {
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, 1, 1, 0);
        puzzleList.add(p1);
        puzzleList.add(p2);
        puzzleList.add(p3);
        puzzleList.add(p4);

        assertTrue(PuzzleValidation.validateTopLeftCorner(puzzleList,false));
        assertTrue(PuzzleValidation.validateTopRightCorner(puzzleList,false));
        assertTrue(PuzzleValidation.validateBottomLeftCorner(puzzleList,false));
        assertTrue(PuzzleValidation.validateBottomRightCorner(puzzleList,false));
    }
    @Test
    public void goodTestCornersWithRotation() {
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 1, 0, 0, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 0, 0, 1);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, 1, 1, 0);
        puzzleList.add(p1);
        puzzleList.add(p2);
        puzzleList.add(p3);
        puzzleList.add(p4);

        assertTrue(PuzzleValidation.validateTopLeftCorner(puzzleList,true));
        assertTrue(PuzzleValidation.validateTopRightCorner(puzzleList,true));
        assertTrue(PuzzleValidation.validateBottomLeftCorner(puzzleList,true));
        assertTrue(PuzzleValidation.validateBottomRightCorner(puzzleList,true));
    }
    @Test
    public void badTestCorners() {
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 1, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 1, 1, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, -1);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 1, 1, 0);
        puzzleList.add(p1);
        puzzleList.add(p2);
        puzzleList.add(p3);
        puzzleList.add(p4);

        assertFalse(PuzzleValidation.validateTopLeftCorner(puzzleList,false));
        assertFalse(PuzzleValidation.validateTopRightCorner(puzzleList,false));
        assertFalse(PuzzleValidation.validateTopLeftCorner(puzzleList,false));
        assertFalse(PuzzleValidation.validateBottomRightCorner(puzzleList,false));

    }

    @Test
    public void goodTestValidateSumOfEdges() {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 0);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, -1, 0, 1, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 0, 1, 0);
        List<PuzzlePiece> puzzle = new ArrayList<>();
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);

        assertTrue(PuzzleValidation.validateSumOfEdges(puzzle));
    }
    @Test
    public void badTestValidateSumOfEdges() {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 0);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 0, 1, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 0, 1, 0);
        List<PuzzlePiece> puzzle = new ArrayList<>();
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);

        assertFalse(PuzzleValidation.validateSumOfEdges(puzzle));
    }

}
