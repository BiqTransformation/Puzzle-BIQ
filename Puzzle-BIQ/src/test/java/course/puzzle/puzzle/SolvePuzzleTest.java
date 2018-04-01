package course.puzzle.puzzle;


import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import course.puzzle.puzzle.PuzzlePiece;
import course.puzzle.puzzle.SolvePuzzle;

public class SolvePuzzleTest {

    List<PuzzlePiece> puzzlePackage = new ArrayList<>();


    @Test
    public void testcheckRightToLeftPositive(){
        PuzzlePiece piece1 = new PuzzlePiece(1,1,0,0,1);
        PuzzlePiece piece2 = new PuzzlePiece(2,1,0,-1,0);
        puzzlePackage.clear();
        puzzlePackage.add(piece1);
        puzzlePackage.add(piece2);
        SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
        assertTrue(solve.checkRightToLeft(piece1, piece2));
    }

    @Test
    public void testcheckRightToLeftNegative(){
        PuzzlePiece piece1 = new PuzzlePiece(1,1,0,0,1);
        PuzzlePiece piece2 = new PuzzlePiece(2,1,0,1,0);
        puzzlePackage.clear();
        puzzlePackage.add(piece1);
        puzzlePackage.add(piece2);
        SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
        assertFalse(solve.checkRightToLeft(piece1, piece2));
    }

    @Test
    public void testcheckLefttToRightPositive(){
        PuzzlePiece piece1 = new PuzzlePiece(1,0,0,1,1);
        PuzzlePiece piece2 = new PuzzlePiece(2,-1,0,-1,1);
        puzzlePackage.clear();
        puzzlePackage.add(piece1);
        puzzlePackage.add(piece2);
        SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
        assertTrue(solve.checkLeftToRight(piece1, piece2));
    }

    @Test
    public void testcheckLefttToRightNegative(){
        PuzzlePiece piece1 = new PuzzlePiece(1,0,0,1,1);
        PuzzlePiece piece2 = new PuzzlePiece(2,1,0,-1,1);
        puzzlePackage.clear();
        puzzlePackage.add(piece1);
        puzzlePackage.add(piece2);
        SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
        assertFalse(solve.checkLeftToRight(piece1, piece2));
    }

    @Test
    public void testcheckTopToBottomPositive(){
        PuzzlePiece piece1 = new PuzzlePiece(1,0,1,1,1);
        PuzzlePiece piece2 = new PuzzlePiece(2,0,-1,-1,-1);
        puzzlePackage.clear();
        puzzlePackage.add(piece1);
        puzzlePackage.add(piece2);
        SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
        assertTrue(solve.checkTopToBottom(piece1, piece2));
    }

    @Test
    public void testcheckTopToBottomNegative(){
        PuzzlePiece piece1 = new PuzzlePiece(1,0,1,1,1);
        PuzzlePiece piece2 = new PuzzlePiece(2,0,-1,-1,1);
        puzzlePackage.clear();
        puzzlePackage.add(piece1);
        puzzlePackage.add(piece2);
        SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
        assertFalse(solve.checkTopToBottom(piece1, piece2));
    }

    @Test
    public void testcheckBottomToTopPositive(){
        PuzzlePiece piece1 = new PuzzlePiece(1,0,1,1,1);
        PuzzlePiece piece2 = new PuzzlePiece(2,0,-1,-1,-1);
        puzzlePackage.clear();
        puzzlePackage.add(piece1);
        puzzlePackage.add(piece2);
        SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
        assertTrue(solve.checkBottomToTop(piece1, piece2));
    }

    @Test
    public void testcheckBottomToTopNegative(){
        PuzzlePiece piece1 = new PuzzlePiece(1,0,1,1,1);
        PuzzlePiece piece2 = new PuzzlePiece(2,0,0,-1,0);
        puzzlePackage.clear();
        puzzlePackage.add(piece1);
        puzzlePackage.add(piece2);
        SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
        assertFalse(solve.checkBottomToTop(piece1, piece2));
    }

}
