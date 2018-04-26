package course.puzzle.e2e;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import course.puzzle.puzzle.Puzzle;
import course.puzzle.puzzle.PuzzlePiece;

public class TestPuzzleRotate {
	
	@Test
	public void testZeroShapeIsUniqu(){
		PuzzlePiece p1 = new PuzzlePiece(1,0,0,0,0);		
		PuzzlePiece p2 = new PuzzlePiece(2,0,0,0,0);
		PuzzlePiece p3 = new PuzzlePiece(3,0,0,0,0);
		PuzzlePiece p4 = new PuzzlePiece(4,0,0,0,0);
		List<PuzzlePiece> puzzleTest = new ArrayList<>();
		puzzleTest.add(p1);
		puzzleTest.add(p2);
		puzzleTest.add(p3);
		puzzleTest.add(p4);
		Puzzle puzzle = new Puzzle(puzzleTest,true);
		puzzle.setRotate(true);
		List<PuzzlePiece> retValue=puzzle.rotateAll();		
		assertEquals(retValue,puzzleTest);		
		}
	
	
	@Test
	public void testOpositeEqualsShape(){
		PuzzlePiece p1 = new PuzzlePiece(1,-1,0,-1,0);		
		PuzzlePiece p2 = new PuzzlePiece(2,0,0,1,-1);
		PuzzlePiece p3 = new PuzzlePiece(3,0,0,0,0);	
		List<PuzzlePiece> puzzleTest = new ArrayList<>();
		puzzleTest.add(p1);
		puzzleTest.add(p2);
		puzzleTest.add(p3);	
		Puzzle puzzle = new Puzzle(puzzleTest,true);
		puzzle.setRotate(true);
		List<PuzzlePiece> retValue=puzzle.rotateAll();	
		for(PuzzlePiece p : retValue){
			System.out.println(p.getId() + " " + p.getRotateEdge());
		}
		assertEquals(retValue.size(),7);
		
		
		}
	

}


