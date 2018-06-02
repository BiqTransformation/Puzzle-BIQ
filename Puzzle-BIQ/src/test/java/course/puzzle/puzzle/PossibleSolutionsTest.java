package course.puzzle.puzzle;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	public void testgetPossiblesolutionWith6PuzzlePiecesNoRotate() throws Exception {
		PuzzlePiece piece1 = new PuzzlePiece(1, 0, 0, 1, 0);
		PuzzlePiece piece2 = new PuzzlePiece(2, -1, 0, 0, 0);
		PuzzlePiece piece3 = new PuzzlePiece(3, 1, 0, 0, 0);
		PuzzlePiece piece4 = new PuzzlePiece(4, 0, 0, -1, 0);
		PuzzlePiece piece5 = new PuzzlePiece(5, 0, 0, 1, 0);
		PuzzlePiece piece6 = new PuzzlePiece(6, -1, 0, 0, 0);
		puzzle.clear();
		puzzle.add(piece1);
		puzzle.add(piece2);
		puzzle.add(piece3);
		puzzle.add(piece4);
		puzzle.add(piece5);
		puzzle.add(piece6);
		PuzzleSolver solve = new PuzzleSolver(new Puzzle(false,puzzle),numOfThreads);
		Map<Integer, Integer> mapUnderTest = solve.getPossibleSolutions();
		Map<Integer,Integer> expected = new HashMap<>();

		expected.put(2,3);
		expected.put(3,2);
		expected.put(1,6);
		System.out.println(mapUnderTest);
		assertEquals(expected,mapUnderTest);
	}
	
	
	@Test
	public void testgetPossiblesolutionWith6PuzzlePiecesRotate() throws Exception {
		PuzzlePiece piece1 = new PuzzlePiece(1, 0, 0, 1, 0);
		PuzzlePiece piece2 = new PuzzlePiece(2, -1, 0, 0, 0);
		PuzzlePiece piece3 = new PuzzlePiece(3, 1, 0, 0, 0);
		PuzzlePiece piece4 = new PuzzlePiece(4, 0, 0, -1, 0);
		PuzzlePiece piece5 = new PuzzlePiece(5, 0, 0, 1, 0);
		PuzzlePiece piece6 = new PuzzlePiece(6, -1, 0, 0, 0);
		puzzle.clear();
		puzzle.add(piece1);
		puzzle.add(piece2);
		puzzle.add(piece3);
		puzzle.add(piece4);
		puzzle.add(piece5);
		puzzle.add(piece6);
		PuzzleSolver solve = new PuzzleSolver(new Puzzle(true,puzzle),numOfThreads);
		Map<Integer, Integer> mapUnderTest = solve.getPossibleSolutions();
		Map<Integer,Integer> expected = new HashMap<>();
		expected.put(2,3);
		expected.put(1,6);
		System.out.println(mapUnderTest);
		assertEquals(expected,mapUnderTest);
	}





}
