package course.puzzle.puzzle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PuzzleTest {


    @Test
    public void goodTestValidateSolution() {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 0, -1, 1, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 1, 0, 0);


        PuzzlePiece[][] good = new PuzzlePiece[][]{{p1, p2}, {p3, p4}};

        assertTrue(PuzzleSolver.checkSum(good));
    }

    @Test
    public void badTestValidateSolution() {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, 1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, 1, 1, 0);

        PuzzlePiece[][] bad = new PuzzlePiece[][]{{p1, p2}, {p3, p4}};

        assertFalse(PuzzleSolver.checkSum(bad));
    }

    @Test
    public void goodTestOneRowSolution() {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 0);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, -1, 0, 1, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 0, 1, 0);
        List<PuzzlePiece> puzzle = new ArrayList<>();
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);

        assertTrue(PuzzleSolver.checkSum(new PuzzleSolver(new Puzzle(puzzle)).findSolution()));
    }

    @Test
    public void goodTestOneRowSolution1() {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 0);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, -1, 0, 1, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 0, 1, 0);
        PuzzlePiece p5 = new PuzzlePiece(5, 0, 0, 0, 0);
        List<PuzzlePiece> puzzle = new ArrayList<>();
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);
        puzzle.add(p5);


        assertTrue(PuzzleSolver.checkSum(new PuzzleSolver(new Puzzle(puzzle)).findSolution()));
    }

    @Test
    public void goodTestTwoRowsSolution2() {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 0);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, -1, 0, 1, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 0, 1, 0);
        PuzzlePiece p5 = new PuzzlePiece(5, 0, 0, 0, 0);
        PuzzlePiece p6 = new PuzzlePiece(1, 0, 0, 1, 0);
        PuzzlePiece p7 = new PuzzlePiece(2, -1, 0, 0, 0);
        PuzzlePiece p8 = new PuzzlePiece(3, -1, 0, 1, 0);
        PuzzlePiece p9 = new PuzzlePiece(4, -1, 0, 1, 0);
        PuzzlePiece p10 = new PuzzlePiece(5, 0, 0, 0, 0);
        List<PuzzlePiece> puzzle = new ArrayList<>();
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);
        puzzle.add(p5);
        puzzle.add(p6);
        puzzle.add(p7);
        puzzle.add(p8);
        puzzle.add(p9);
        puzzle.add(p10);

        assertTrue(PuzzleSolver.checkSum(new PuzzleSolver(new Puzzle(puzzle)).findSolution()));
    }

    @Test
    public void goodTestOneColumnSolution() {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 0, -1);
        PuzzlePiece p2 = new PuzzlePiece(2, 0, 1, 0, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, 0, 1, 0, 1);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, 0, -1);
        List<PuzzlePiece> puzzle = new ArrayList<>();
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);

        assertTrue(PuzzleSolver.checkSum(new PuzzleSolver(new Puzzle(puzzle)).findSolution()));
    }

    @Test
    public void goodTestOneColumnSolution1() {

        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 0, -1);
        PuzzlePiece p2 = new PuzzlePiece(2, 0, 1, 0, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, 0, 1, 0, 1);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, 0, -1);
        PuzzlePiece p5 = new PuzzlePiece(5, 0, 0, 0, 0);
        List<PuzzlePiece> puzzle = new ArrayList<>();
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);
        puzzle.add(p5);

        assertTrue(PuzzleSolver.checkSum(new PuzzleSolver(new Puzzle(puzzle)).findSolution()));
    }

    @Test
    public void goodTestMatrix2x2Solution() {

        List<PuzzlePiece> puzzle = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, -1, 0);
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);

        assertTrue(PuzzleSolver.checkSum(new PuzzleSolver(new Puzzle(puzzle)).findSolution()));
    }

    @Test
    public void goodTestMatrix2x3Solution() {

        List<PuzzlePiece> puzzle = new ArrayList<PuzzlePiece>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, -1, 0);
        PuzzlePiece p5 = new PuzzlePiece(5, -1, 0, 1, 0);
        PuzzlePiece p6 = new PuzzlePiece(6, 1, 0, -1, 0);
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);
        puzzle.add(p5);
        puzzle.add(p6);

        assertTrue(PuzzleSolver.checkSum(new PuzzleSolver(new Puzzle(puzzle)).findSolution()));
    }

    @Test
    public void goodTestMatrix3x2Solution() {

        List<PuzzlePiece> puzzle = new ArrayList<>();
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, -1);
        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, -1, 0);
        PuzzlePiece p5 = new PuzzlePiece(5, 0, -1, 1, 1);
        PuzzlePiece p6 = new PuzzlePiece(6, -1, 1, 0, -1);
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);
        puzzle.add(p5);
        puzzle.add(p6);

        assertTrue(PuzzleSolver.checkSum(new PuzzleSolver(new Puzzle(puzzle)).findSolution()));
    }

}
