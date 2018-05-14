package course.puzzle.puzzle;


import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PossibleSolutionsTest {
    public boolean rotate= false;
    public int numOfThreads=2;
	List<PuzzlePiece> puzzle = new ArrayList<>();

	@Test
	public void testgetPossiblesolutionWith6PuzzlePieces() throws Exception {
		PuzzlePiece piece1 = new PuzzlePiece(1, 0, 0, 1, 0);
		PuzzlePiece piece2 = new PuzzlePiece(2, -1, 0, -1, 0);
		PuzzlePiece piece3 = new PuzzlePiece(3, 1, 0, 0, 0);
		PuzzlePiece piece4 = new PuzzlePiece(4, 0, 0, -1, 0);
		PuzzlePiece piece5 = new PuzzlePiece(5, 1, 0, 1, 0);
		PuzzlePiece piece6 = new PuzzlePiece(6, -1, 0, 0, 0);
		puzzle.clear();
		puzzle.add(piece1);
		puzzle.add(piece2);
		puzzle.add(piece3);
		puzzle.add(piece4);
		puzzle.add(piece5);
		puzzle.add(piece6);
		PuzzleSolver solve = new PuzzleSolver(new Puzzle(puzzle,false),numOfThreads);
		Map<Integer, Integer> mapUnderTest = solve.getPossibleSolutions();
		Map<Integer, Integer> testMap = new HashMap<>();
		testMap.put(1, 6);
		testMap.put(6, 1);
		testMap.put(2, 3);
		testMap.put(3, 2);
		assertEquals(testMap,mapUnderTest );
	}


	@Test
	public void testgetPossiblesolutionWith8PuzzlePieces() throws Exception {
		PuzzlePiece piece1 = new PuzzlePiece(1, 0, 0, 1, 0);
		PuzzlePiece piece2 = new PuzzlePiece(2, -1, 0, -1, 0);
		PuzzlePiece piece3 = new PuzzlePiece(3, 1, 0, 0, 0);
		PuzzlePiece piece4 = new PuzzlePiece(4, 0, 0, -1, 0);
		PuzzlePiece piece5 = new PuzzlePiece(5, 1, 0, 1, 0);
		PuzzlePiece piece6 = new PuzzlePiece(6, -1, 0, 1, 0);
		PuzzlePiece piece7 = new PuzzlePiece(7, -1, 0, -1, 0);
		PuzzlePiece piece8 = new PuzzlePiece(8, 1, 0, 0, 0);
		puzzle.clear();
		puzzle.add(piece1);
		puzzle.add(piece2);
		puzzle.add(piece3);
		puzzle.add(piece4);
		puzzle.add(piece5);
		puzzle.add(piece6);
		puzzle.add(piece7);
		puzzle.add(piece8);
		PuzzleSolver solve = new PuzzleSolver(new Puzzle(puzzle,rotate),numOfThreads);
		Map<Integer, Integer> mapUnderTest = solve.getPossibleSolutions();
		Map<Integer, Integer> testMap = new HashMap<>();
		testMap.put(1, 8);
		testMap.put(8, 1);
		testMap.put(2, 4);
		testMap.put(4, 2);
		assertEquals(mapUnderTest, testMap);
	}

	@Test
	public void testgetPossiblesolutionWith3PuzzlePieces() throws Exception {
		PuzzlePiece piece1 = new PuzzlePiece(1, 0, 0, 1, 0);
		PuzzlePiece piece2 = new PuzzlePiece(2, -1, 0, -1, 0);
		PuzzlePiece piece3 = new PuzzlePiece(3, 1, 0, 0, 0);
		puzzle.clear();
		puzzle.add(piece1);
		puzzle.add(piece2);
		puzzle.add(piece3);
		PuzzleSolver solve = new PuzzleSolver(new Puzzle(puzzle,rotate),numOfThreads);
		Map<Integer, Integer> mapUnderTest = solve.getPossibleSolutions();
		Map<Integer, Integer> testMap = new HashMap<>();
		testMap.put(1, 3);
		testMap.put(3, 1);
		assertEquals(mapUnderTest, testMap);
	}

	@Test
	public void testgetPossiblesolutionWith1PuzzlePieces() throws Exception {
		PuzzlePiece piece1 = new PuzzlePiece(1, 0, 0, 0, 0);
		puzzle.clear();
		puzzle.add(piece1);
		PuzzleSolver solve = new PuzzleSolver(new Puzzle(puzzle,rotate),numOfThreads);
		Map<Integer, Integer> mapUnderTest = solve.getPossibleSolutions();
		Map<Integer, Integer> testMap = new HashMap<>();
		testMap.put(1, 1);
		assertEquals(mapUnderTest, testMap);
	}

	@Test
	public void testgetPossiblesolutionWith2PuzzlePieces() throws Exception {
		PuzzlePiece piece1 = new PuzzlePiece(1, 0, 0, -1, 0);
		PuzzlePiece piece2 = new PuzzlePiece(2, 1, 0, 0, 0);
		puzzle.clear();
		puzzle.add(piece1);
		puzzle.add(piece2);
		PuzzleSolver solve = new PuzzleSolver(new Puzzle(puzzle,rotate),numOfThreads);
		Map<Integer, Integer> mapUnderTest = solve.getPossibleSolutions();
		Map<Integer, Integer> testMap = new HashMap<>();
		testMap.put(1, 2);
		testMap.put(2, 1);
		assertEquals(mapUnderTest, testMap);
	}


}
