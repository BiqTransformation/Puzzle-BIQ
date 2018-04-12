package course.puzzle.e2e;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import course.puzzle.file.FileOutput;
import course.puzzle.puzzleManager.PuzzleManager;
public class E2E_validation {
	
	
	
	
	@Test
	public void testSimpleNegativeE2EWithLessIDsThenNumElements() throws Exception{
		String fromPath = "src//test//resources//files//test3.in";
		String toPath = "src//test//resources//files//test3.out";
		//clean output
		 PuzzleManager pm = new PuzzleManager(fromPath ,toPath);
		 pm.handlePuzzle();
		 String message = FileOutput.loadFromTextFile();
		 System.out.println(message);
	     assertTrue(message.contains("missing puzzle elements with the following IDs:3,4"));
	}
	
	
	
	@Test
	public void testSimpleNegativeE2EWithLessIDsNotInSequance() throws Exception{
		String fromPath = "src//test//resources//files//test4.in";
		String toPath = "src//test//resources//files//test4.out";
		//clean output
		 PuzzleManager pm = new PuzzleManager(fromPath ,toPath);
		 pm.handlePuzzle();
		 String message = FileOutput.loadFromTextFile();
		 //System.out.println(message);
	     assertTrue(message.contains("Id number not valid should be more then 0 and less then NumElements"));
	}
	
	
	


}
