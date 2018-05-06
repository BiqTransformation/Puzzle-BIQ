package course.puzzle.puzzle;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class PuzzleTest {

    public boolean rotate= true;
    public int numOfThreads=2;


//    @Test
//    public void goodTestOneRowSolution() throws IOException {
//
//        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 0);
//        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, 0);
//        PuzzlePiece p3 = new PuzzlePiece(3, -1, 0, 1, 0);
//        PuzzlePiece p4 = new PuzzlePiece(4, -1, 0, 1, 0);
//        List<PuzzlePiece> puzzle = new ArrayList<>();
//        puzzle.add(p1);
//        puzzle.add(p2);
//        puzzle.add(p3);
//        puzzle.add(p4);
//
//        PuzzleSolver ps = new PuzzleSolver(new Puzzle(puzzle,rotate),numOfThreads);
//        ps.findSolution();
//        assertTrue(ps.getSolved());    
    //}
    
  @Test
  	public void goodTestIndexer1() throws IOException {
	  PuzzleShape shape1 = new PuzzleShape(0, 0, 0, 0);
	  PuzzleShape shape2 = new PuzzleShape(-1, 0, 0, 0);
	  PuzzleShape shape3 = new PuzzleShape(1, 0, 0, 0);
	  PuzzleShape shape4 = new PuzzleShape(0, 1, 0, 0);
	  PuzzleShape shape5 = new PuzzleShape(0, 1, 1, 0);
	  PuzzleShape shape6 = new PuzzleShape(1, 0, 1, 0);

      PuzzlePiece p1 = new PuzzlePiece(1, 0, shape1);
      PuzzlePiece p2 = new PuzzlePiece(2, 0, shape2);
      PuzzlePiece p3 = new PuzzlePiece(3, 0, shape3);
      PuzzlePiece p4 = new PuzzlePiece(4, 0, shape4);
      PuzzlePiece p5 = new PuzzlePiece(5, 0, shape5);
      PuzzlePiece p6 = new PuzzlePiece(6, 0, shape6);
      List<PuzzlePiece> puzzlePack = new ArrayList<>();      
      puzzlePack.add(p1);
      puzzlePack.add(p2);
      puzzlePack.add(p3);
      puzzlePack.add(p4);
      puzzlePack.add(p5);
      puzzlePack.add(p6);
      //Puzzle puzzle = new Puzzle(puzzlePack,true);
      PuzzleIndexer pi = new PuzzleIndexer();
      pi.add(puzzlePack);
      //pi.printPieces();
      Integer mapSize=pi.getPieces().size();
      Integer test = 6;
      assertEquals(mapSize,test);      
        
  }
  
  @Test
	public void goodTestIndexer2() throws IOException {
		PuzzleShape shape1 = new PuzzleShape(0, 0, 0, 0);
		PuzzleShape shape2 = new PuzzleShape(-1, 0, 0, 0);
		PuzzleShape shape3 = new PuzzleShape(1, 0, 0, 0);
		PuzzleShape shape4 = new PuzzleShape(0, 1, 0, 0);
		PuzzleShape shape5 = new PuzzleShape(0, 1, 1, 0);
		PuzzleShape shape6 = new PuzzleShape(1, 0, 1, 0);

		PuzzlePiece p1 = new PuzzlePiece(1, 0, shape1);
		PuzzlePiece p2 = new PuzzlePiece(2, 0, shape2);
		PuzzlePiece p3 = new PuzzlePiece(3, 0, shape3);
		PuzzlePiece p4 = new PuzzlePiece(4, 0, shape4);
		PuzzlePiece p5 = new PuzzlePiece(5, 0, shape5);
		PuzzlePiece p6 = new PuzzlePiece(6, 0, shape6);
		List<PuzzlePiece> puzzlePack = new ArrayList<>();
		puzzlePack.add(p1);
		puzzlePack.add(p2);
		puzzlePack.add(p3);
		puzzlePack.add(p4);
		puzzlePack.add(p5);
		puzzlePack.add(p6);
		// Puzzle puzzle = new Puzzle(puzzlePack,true);
		PuzzleIndexer pi = new PuzzleIndexer();
		pi.add(puzzlePack);
		// pi.printPieces();
		Integer mapSize = pi.getPieces().size();
		Integer test = 6;
		assertEquals(mapSize, test);

	}
  
  @Test
	public void goodTestIndexerWithDuplicateShapes() throws IOException {
		PuzzleShape shape1 = new PuzzleShape(1, 0, 0, 0);
		PuzzleShape shape2 = new PuzzleShape(-1, 0, 0, 0);
		PuzzleShape shape3 = new PuzzleShape(1, 0, 0, 0);
		PuzzleShape shape4 = new PuzzleShape(-1, 0, 0, 0);
		PuzzleShape shape5 = new PuzzleShape(0, 1, 1, 0);
		PuzzleShape shape6 = new PuzzleShape(0, 1, 1, 0);

		PuzzlePiece p1 = new PuzzlePiece(1, 0, shape1);
		PuzzlePiece p2 = new PuzzlePiece(2, 0, shape2);
		PuzzlePiece p3 = new PuzzlePiece(3, 0, shape3);
		PuzzlePiece p4 = new PuzzlePiece(4, 0, shape4);
		PuzzlePiece p5 = new PuzzlePiece(5, 0, shape5);
		PuzzlePiece p6 = new PuzzlePiece(6, 0, shape6);
		List<PuzzlePiece> puzzlePack = new ArrayList<>();
		puzzlePack.add(p1);
		puzzlePack.add(p2);
		puzzlePack.add(p3);
		puzzlePack.add(p4);
		puzzlePack.add(p5);
		puzzlePack.add(p6);
		// Puzzle puzzle = new Puzzle(puzzlePack,true);
		PuzzleIndexer pi = new PuzzleIndexer();
		pi.add(puzzlePack);
		// pi.printPieces();
		Integer mapSize = pi.getPieces().size();
		Integer test = 3;
		assertEquals(mapSize, test);
	}
  
  
  @Test
	public void testIndexerWithDuplicateShapesAndGetShape() throws IOException {
		PuzzleShape shape1 = new PuzzleShape(1, 0, 0, 0);
		PuzzleShape shape2 = new PuzzleShape(-1, 0, 0, 0);
		PuzzleShape shape3 = new PuzzleShape(1, 0, 0, 0);
		PuzzleShape shape4 = new PuzzleShape(-1, 0, 0, 0);
		PuzzleShape shape5 = new PuzzleShape(0, 1, 1, 0);
		PuzzleShape shape6 = new PuzzleShape(0, 1, 1, 0);

		PuzzlePiece p1 = new PuzzlePiece(1, 0, shape1);
		PuzzlePiece p2 = new PuzzlePiece(2, 0, shape2);
		PuzzlePiece p3 = new PuzzlePiece(3, 0, shape3);
		PuzzlePiece p4 = new PuzzlePiece(4, 0, shape4);
		PuzzlePiece p5 = new PuzzlePiece(5, 0, shape5);
		PuzzlePiece p6 = new PuzzlePiece(6, 0, shape6);
		List<PuzzlePiece> puzzlePack = new ArrayList<>();
		puzzlePack.add(p1);
		puzzlePack.add(p2);
		puzzlePack.add(p3);
		puzzlePack.add(p4);
		puzzlePack.add(p5);
		puzzlePack.add(p6);
		// Puzzle puzzle = new Puzzle(puzzlePack,true);
		PuzzleIndexer pi = new PuzzleIndexer();
		pi.add(puzzlePack);
		Set<PuzzlePiece> test = pi.getNeededShapes(shape1);
		for(PuzzlePiece p : test){
			System.out.println(p);
		}
		assertTrue(test.contains(p1));
		assertTrue(test.contains(p3));
	}
  
//
//    @Test
//    public void goodTestOneRowSolution1() throws IOException {
//
//        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 0);
//        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, 0);
//        PuzzlePiece p3 = new PuzzlePiece(3, -1, 0, 1, 0);
//        PuzzlePiece p4 = new PuzzlePiece(4, -1, 0, 1, 0);
//        PuzzlePiece p5 = new PuzzlePiece(5, 0, 0, 0, 0);
//        List<PuzzlePiece> puzzle = new ArrayList<>();
//        puzzle.add(p1);
//        puzzle.add(p2);
//        puzzle.add(p3);
//        puzzle.add(p4);
//        puzzle.add(p5);
//
//        PuzzleSolver ps = new PuzzleSolver(new Puzzle(puzzle,rotate),numOfThreads);
//        ps.findSolution();
//        assertTrue(ps.getSolved());    }
//
//
//    @Test
//    public void goodTestOneColumnSolution() throws IOException {
//
//        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 0, -1);
//        PuzzlePiece p2 = new PuzzlePiece(2, 0, 1, 0, 0);
//        PuzzlePiece p3 = new PuzzlePiece(3, 0, 1, 0, 1);
//        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, 0, -1);
//        List<PuzzlePiece> puzzle = new ArrayList<>();
//        puzzle.add(p1);
//        puzzle.add(p2);
//        puzzle.add(p3);
//        puzzle.add(p4);
//
//        PuzzleSolver ps = new PuzzleSolver(new Puzzle(puzzle,rotate),numOfThreads);
//        ps.findSolution();
//        assertTrue(ps.getSolved());
//    }
//
//    @Test
//    public void goodTestOneColumnSolution1() throws IOException {
//
//        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 0, -1);
//        PuzzlePiece p2 = new PuzzlePiece(2, 0, 1, 0, 0);
//        PuzzlePiece p3 = new PuzzlePiece(3, 0, 1, 0, 1);
//        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, 0, -1);
//        PuzzlePiece p5 = new PuzzlePiece(5, 0, 0, 0, 0);
//        List<PuzzlePiece> puzzle = new ArrayList<>();
//        puzzle.add(p1);
//        puzzle.add(p2);
//        puzzle.add(p3);
//        puzzle.add(p4);
//        puzzle.add(p5);
//
//        PuzzleSolver ps = new PuzzleSolver(new Puzzle(puzzle,rotate),numOfThreads);
//        ps.findSolution();
//        assertTrue(ps.getSolved());
//    }
//
//    @Test
//    public void goodTestMatrix2x2Solution() throws IOException {
//
//        List<PuzzlePiece> puzzle = new ArrayList<PuzzlePiece>();
//        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
//        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, -1);
//        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, 0);
//        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, -1, 0);
//        puzzle.add(p1);
//        puzzle.add(p2);
//        puzzle.add(p3);
//        puzzle.add(p4);
//
//        PuzzleSolver ps = new PuzzleSolver(new Puzzle(puzzle,rotate),numOfThreads);
//        ps.findSolution();
//        assertTrue(ps.getSolved());    }
//
//    @Test
//    public void goodTestMatrix2x3Solution() throws IOException {
//
//        List<PuzzlePiece> puzzle = new ArrayList<PuzzlePiece>();
//        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
//        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, -1);
//        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, 0);
//        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, -1, 0);
//        PuzzlePiece p5 = new PuzzlePiece(5, -1, 0, 1, 0);
//        PuzzlePiece p6 = new PuzzlePiece(6, 1, 0, -1, 0);
//        puzzle.add(p1);
//        puzzle.add(p2);
//        puzzle.add(p3);
//        puzzle.add(p4);
//        puzzle.add(p5);
//        puzzle.add(p6);
//
//        PuzzleSolver ps = new PuzzleSolver(new Puzzle(puzzle,rotate),numOfThreads);
//        ps.findSolution();
//        assertTrue(ps.getSolved());    }
//
//    @Test
//    public void goodTestMatrix3x2Solution() throws IOException {
//
//        List<PuzzlePiece> puzzle = new ArrayList<>();
//        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 1);
//        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, -1);
//        PuzzlePiece p3 = new PuzzlePiece(3, 1, 1, 0, 0);
//        PuzzlePiece p4 = new PuzzlePiece(4, 0, -1, -1, 0);
//        PuzzlePiece p5 = new PuzzlePiece(5, 0, -1, 1, 1);
//        PuzzlePiece p6 = new PuzzlePiece(6, -1, 1, 0, -1);
//        puzzle.add(p1);
//        puzzle.add(p2);
//        puzzle.add(p3);
//        puzzle.add(p4);
//        puzzle.add(p5);
//        puzzle.add(p6);
//
//        PuzzleSolver ps = new PuzzleSolver(new Puzzle(puzzle,rotate),numOfThreads);
//        ps.findSolution();
//        assertTrue(ps.getSolved());    }

}
