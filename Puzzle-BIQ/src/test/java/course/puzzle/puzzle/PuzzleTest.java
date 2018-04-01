package course.puzzle.puzzle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PuzzleTest {

    @Test
    public void badTestNumberOfLeftRightStraightEdges() {
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 1, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 1, 1, 1);
        puzzleList.add(p1);
        puzzleList.add(p2);
        PuzzleValidation bad = new PuzzleValidation(puzzleList);

        assertFalse(bad.validateNumberOfStraightEdges());
    }

    @Test
    public void badTestNumberOfTopBottomStraightEdges() {
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, -1, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 1, 1, -1);
        puzzleList.add(p1);
        puzzleList.add(p2);
        PuzzleValidation bad = new PuzzleValidation(puzzleList);

//        assertFalse(bad.validateNumberOfStraightEdges());
    }

    @Test
    public void goodTestNumberOfLeftRightStraightEdges() {
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 1, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 1, 0, 1);
        puzzleList.add(p1);
        puzzleList.add(p2);
        PuzzleValidation good = new PuzzleValidation(puzzleList);

//        assertTrue(good.validateNumberOfStraightEdges());
    }

    @Test
    public void goodTestNumberOfTopBottomStraightEdges() {
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 1, 0, 0);
        puzzleList.add(p1);
        puzzleList.add(p2);
        PuzzleValidation good = new PuzzleValidation(puzzleList);

//        assertTrue(good.validateNumberOfStraightEdges());
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
        PuzzleValidation good = new PuzzleValidation(puzzleList);

        assertTrue(good.validateTopLeftCorner());
        assertTrue(good.validateTopRightCorner());
        assertTrue(good.validateBottomLeftCorner());
        assertTrue(good.validateBottomRightCorner());
    }

    @Test
    public void badTestLeftTopCorner() {
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 1, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 1, 1, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, -1);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 1, 1, 0);
        puzzleList.add(p1);
        puzzleList.add(p2);
        puzzleList.add(p3);
        puzzleList.add(p4);
        PuzzleValidation bad = new PuzzleValidation(puzzleList);

        assertFalse(bad.validateTopLeftCorner());
        assertFalse(bad.validateTopRightCorner());
        assertFalse(bad.validateTopLeftCorner());
        assertFalse(bad.validateBottomRightCorner());

    }
    @Test
    public void goodTestValidateSolution() {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 0, -1, 1, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 1, 0, 0);


        PuzzlePiece[][] good = new PuzzlePiece[][]{{p1, p2}, {p3, p4}};

        assertTrue(SolvePuzzle.validateSolution(good));
    }
    @Test
    public void badTestValidateSolution() {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, 1, 1, 0);

        PuzzlePiece[][] bad = new PuzzlePiece[][]{{p1, p2}, {p3, p4}};

        assertFalse(SolvePuzzle.validateSolution(bad));
    }

}
