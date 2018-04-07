package course.puzzle.puzzle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PuzzleTest {


    @Test
    public void goodTestValidateSolution() throws Exception {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 0, -1, 1, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 1, 0, 0);


        PuzzlePiece[][] good = new PuzzlePiece[][]{{p1, p2}, {p3, p4}};

        assertTrue(SolvePuzzle.verifySolution(good));
    }

    @Test
    public void badTestValidateSolution() throws Exception {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, 1, 1, 0);

        PuzzlePiece[][] bad = new PuzzlePiece[][]{{p1, p2}, {p3, p4}};

        assertFalse(SolvePuzzle.verifySolution(bad));
    }

    @Test
    public void goodTestOneRowSolution() throws Exception {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 0);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, -1, 0, 1, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 0, 1, 0);
        List<PuzzlePiece> puzzle = new ArrayList<>();
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);



        assertTrue(SolvePuzzle.verifySolution(new SolvePuzzle(puzzle).findSolution()));
    }

    @Test
    public void goodTestOneRowSolution1() throws Exception {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 0);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, -1, 0, 1, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 0, 1, 0);
        PuzzlePiece p5 = new PuzzlePiece(4, 0, 0, 0, 0);
        List<PuzzlePiece> puzzle = new ArrayList<>();
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);
        puzzle.add(p5);


        assertTrue(SolvePuzzle.verifySolution(new SolvePuzzle(puzzle).findSolution()));
    }

    @Test
    public void goodTestOneColumnSolution() throws Exception {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 0, -1);
        PuzzlePiece p2 = new PuzzlePiece(2, 0, 1, 0, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, 0, 1, 0, 1);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, 0, -1);
        List<PuzzlePiece> puzzle = new ArrayList<>();
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);

        assertTrue(SolvePuzzle.verifySolution(new SolvePuzzle(puzzle).findSolution()));
    }

    @Test
    public void goodTestMatrix2x2Solution() throws Exception {

        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, -1, 0);
        puzzleList.add(p1);
        puzzleList.add(p2);
        puzzleList.add(p3);
        puzzleList.add(p4);

        assertTrue(SolvePuzzle.verifySolution(new SolvePuzzle(puzzleList).findSolution()));
    }

    @Test
    public void goodTestMatrix2x3Solution() throws Exception {

        List<PuzzlePiece> puzzleList = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, -1, 0);
        PuzzlePiece p5 = new PuzzlePiece(5, -1, 0, 1, 0);
        PuzzlePiece p6 = new PuzzlePiece(6, 1, 0, -1, 0);
        puzzleList.add(p1);
        puzzleList.add(p2);
        puzzleList.add(p3);
        puzzleList.add(p4);
        puzzleList.add(p5);
        puzzleList.add(p6);

        assertTrue(SolvePuzzle.verifySolution(new SolvePuzzle(puzzleList).findSolution()));
    }

    @Test
    public void goodTestMatrix3x2Solution() throws Exception {

        List<PuzzlePiece> puzzleList = new ArrayList<>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, -1, 0);
        PuzzlePiece p5 = new PuzzlePiece(5, 0, -1, 1, 1);
        PuzzlePiece p6 = new PuzzlePiece(6, -1, 1, 0, -1);
        puzzleList.add(p1);
        puzzleList.add(p2);
        puzzleList.add(p3);
        puzzleList.add(p4);
        puzzleList.add(p5);
        puzzleList.add(p6);

        assertTrue(SolvePuzzle.verifySolution(new SolvePuzzle(puzzleList).findSolution()));
    }

}
