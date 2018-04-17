package course.puzzle.file;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import course.puzzle.puzzle.Puzzle;
import course.puzzle.puzzle.PuzzleSolver;
import org.junit.Test;

import course.puzzle.puzzle.PuzzleErrors;
import course.puzzle.puzzle.PuzzlePiece;


public class OutputFileTests {
	String path="src/main/resources/files/output.txt";
	FileOutput fo = new FileOutput(path);
	
	
	@Test
	public void WriteReadtoOutputFile() throws IOException{
		//clean file				
		fo.cleanOutputFile();		
		//save
		String message = PuzzleErrors.CANNOT_SOLVE_PUZZLE;
		fo.printToOutputFile(message);
		//save again
		String message2 = PuzzleErrors.MISSING_CORNER_BL;
		fo.printToOutputFile(message2);
		//load		
		String message3 =  fo.loadFromTextFile();
		//System.out.println("message3 " + message3);
		assertTrue(message3.contains("Cannot solve puzzle:"));
		assertTrue(message3.contains("missing corner element: BL"));
	}
	
	
	@Test
	public void testOutputFilewriteList() throws IOException{
		fo.cleanOutputFile();
		List<String> list = new ArrayList<>();
		String str1 = "test1";
		String str2 = "test2";
		list.add(str1);
		list.add(str2);	
		fo.printListToOutputFile(list);
		String message = fo.loadFromTextFile();
		//System.out.println(message);
		assertTrue(message.contains("test1"));
		assertTrue(message.contains("test2"));
	}
	
	@Test
	public void testPuzzleSolutionPrint() throws Exception {
		
	    fo.cleanOutputFile();
	    PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 0);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, -1, 0, 1, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 0, 1, 0);
        List<PuzzlePiece> puzzle = new ArrayList<>();
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);        
        PuzzlePiece[][] good = new PuzzleSolver(new Puzzle(puzzle)).findSolution();
        fo.printSolution(good);
        String str =  fo.loadFromTextFile();
        System.out.println(str);
        assertTrue(str.contains("1 3 4 2"));
	}
	
	

}
