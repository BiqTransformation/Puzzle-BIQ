package course.puzzle.puzzle;


import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Test
    public void testgetPossiblesolutionWith6PuzzlePieces(){
    PuzzlePiece piece1 = new PuzzlePiece(1,1,0,0,1);
    PuzzlePiece piece2 = new PuzzlePiece(2,1,0,-1,0);
    PuzzlePiece piece3 = new PuzzlePiece(1,1,0,0,1);
    PuzzlePiece piece4 = new PuzzlePiece(2,1,0,-1,0);
    PuzzlePiece piece5 = new PuzzlePiece(1,1,0,0,1);
    PuzzlePiece piece6 = new PuzzlePiece(2,1,0,-1,0);
    puzzlePackage.clear();
    puzzlePackage.add(piece1);
    puzzlePackage.add(piece2);
    puzzlePackage.add(piece3);
    puzzlePackage.add(piece4);
    puzzlePackage.add(piece5);
    puzzlePackage.add(piece6);
    SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
    Map<Integer,Integer> mapUnderTest =solve.getPossibleSolutions();
    Map<Integer,Integer> testMap = new HashMap<>();
    testMap.put(1,6);
    testMap.put(6,1);
    testMap.put(2,3);
    testMap.put(3,2);
    assertEquals(mapUnderTest,testMap);
    }
    
    
    @Test
    public void testgetPossiblesolutionWith8PuzzlePieces(){
    PuzzlePiece piece1 = new PuzzlePiece(1,1,0,0,1);
    PuzzlePiece piece2 = new PuzzlePiece(2,1,0,-1,0);
    PuzzlePiece piece3 = new PuzzlePiece(1,1,0,0,1);
    PuzzlePiece piece4 = new PuzzlePiece(2,1,0,-1,0);
    PuzzlePiece piece5 = new PuzzlePiece(1,1,0,0,1);
    PuzzlePiece piece6 = new PuzzlePiece(2,1,0,-1,0);
    PuzzlePiece piece7 = new PuzzlePiece(2,1,0,-1,0);
    PuzzlePiece piece8 = new PuzzlePiece(2,1,0,-1,0);
    puzzlePackage.clear();
    puzzlePackage.add(piece1);
    puzzlePackage.add(piece2);
    puzzlePackage.add(piece3);
    puzzlePackage.add(piece4);
    puzzlePackage.add(piece5);
    puzzlePackage.add(piece6);
    puzzlePackage.add(piece7);
    puzzlePackage.add(piece8);
    SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
    Map<Integer,Integer> mapUnderTest =solve.getPossibleSolutions();
    Map<Integer,Integer> testMap = new HashMap<>();
    testMap.put(1,8);
    testMap.put(8,1);
    testMap.put(2,4);
    testMap.put(4,2);
    assertEquals(mapUnderTest,testMap);
    }
    
    @Test
    public void testgetPossiblesolutionWith3PuzzlePieces(){
    PuzzlePiece piece1 = new PuzzlePiece(1,1,0,0,1);
    PuzzlePiece piece2 = new PuzzlePiece(2,1,0,-1,0);
    PuzzlePiece piece3 = new PuzzlePiece(1,1,0,0,1);
    puzzlePackage.clear();
    puzzlePackage.add(piece1);
    puzzlePackage.add(piece2);
    puzzlePackage.add(piece3);   
    SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
    Map<Integer,Integer> mapUnderTest =solve.getPossibleSolutions();
    Map<Integer,Integer> testMap = new HashMap<>();
    testMap.put(1,3);
    testMap.put(3,1);    
    assertEquals(mapUnderTest,testMap);
    }
    
    @Test
    public void testgetPossiblesolutionWith0PuzzlePieces(){
    puzzlePackage.clear();      
    SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
    Map<Integer,Integer> mapUnderTest =solve.getPossibleSolutions();    
    assertTrue(mapUnderTest.isEmpty());
    }
    
    @Test
    public void testgetPossiblesolutionWith1PuzzlePieces(){
    PuzzlePiece piece1 = new PuzzlePiece(1,1,0,0,1);   
    puzzlePackage.clear();
    puzzlePackage.add(piece1);      
    SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
    Map<Integer,Integer> mapUnderTest =solve.getPossibleSolutions();
    Map<Integer,Integer> testMap = new HashMap<>();
    testMap.put(1,1);        
    assertEquals(mapUnderTest,testMap);
    }
    
    @Test
    public void testgetPossiblesolutionWith2PuzzlePieces(){
    PuzzlePiece piece1 = new PuzzlePiece(1,1,0,0,1);
    PuzzlePiece piece2 = new PuzzlePiece(2,1,0,-1,0);    
    puzzlePackage.clear();
    puzzlePackage.add(piece1);
    puzzlePackage.add(piece2);     
    SolvePuzzle solve = new SolvePuzzle(puzzlePackage);
    Map<Integer,Integer> mapUnderTest =solve.getPossibleSolutions();
    Map<Integer,Integer> testMap = new HashMap<>();
    testMap.put(1,2);
    testMap.put(2,1);    
    assertEquals(mapUnderTest,testMap);
    }
  

}
