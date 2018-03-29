package course.puzzle.puzzle;


import course.puzzle.puzzle.Puzzle;
import course.puzzle.puzzle.PuzzlePiece;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class PuzzleTest {

    @Test
    public void badTestNumberOfLeftRightStraightEdges(){
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1,0,1,1,1);
        PuzzlePiece p2 = new PuzzlePiece(2,1,1,1,1);
        puzzleList.add(p1);
        puzzleList.add(p2);
         Puzzle bad = new PuzzleValidation(puzzleList);

//        assertFalse(bad.validateNumberOfStraightEdges());
    }

    @Test
    public void badTestNumberOfTopBottomStraightEdges(){
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1,-1,0,1,1);
        PuzzlePiece p2 = new PuzzlePiece(2,1,1,1,-1);
        puzzleList.add(p1);
        puzzleList.add(p2);
        Puzzle bad = new Puzzle(puzzleList);

//        assertFalse(bad.validateNumberOfStraightEdges());
    }

    @Test
    public void goodTestNumberOfLeftRightStraightEdges(){
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1,0,1,1,1);
        PuzzlePiece p2 = new PuzzlePiece(2,1,1,0,1);
        puzzleList.add(p1);
        puzzleList.add(p2);
        Puzzle good = new Puzzle(puzzleList);

//        assertTrue(good.validateNumberOfStraightEdges());
    }
    @Test
    public void goodTestNumberOfTopBottomStraightEdges(){
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1,0,0,1,1);
        PuzzlePiece p2 = new PuzzlePiece(2,1,1,0,0);
        puzzleList.add(p1);
        puzzleList.add(p2);
        Puzzle good = new Puzzle(puzzleList);

//        assertTrue(good.validateNumberOfStraightEdges());
    }

    @Test
    public void goodTestLeftTopCorner(){
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1,0,0,1,1);
        PuzzlePiece p2 = new PuzzlePiece(2,1,1,0,0);
        puzzleList.add(p1);
        puzzleList.add(p2);
        Puzzle good = new Puzzle(puzzleList);

//        assertTrue(good.validateLeftTopCorner());
    }

    @Test
    public void badTestLeftTopCorner(){
        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1,0,0,1,1);
        PuzzlePiece p2 = new PuzzlePiece(2,1,1,0,0);
        puzzleList.add(p1);
        puzzleList.add(p2);
        Puzzle bad = new Puzzle(puzzleList);

//        assertFalse(bad.validateLeftTopCorner());
    }
}
