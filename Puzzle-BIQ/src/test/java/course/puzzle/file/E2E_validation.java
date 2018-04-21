package course.puzzle.file;

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
		 FileOutput fo = new FileOutput(toPath);
		 String message = fo.loadFromTextFile();
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
		 FileOutput fo = new FileOutput(toPath);
		 String message = fo.loadFromTextFile();
		 //System.out.println(message);
	     assertTrue(message.contains("Id number not valid should be more then 0 and less then NumElements"));
	}
	
	@Test
	public void goodValidateSolutionViaOutputFile() throws Exception {
		String in = "src//test//resources//files//good12Pieces.in";
		String out = "src//test//resources//files//good12Pieces.out";


		PuzzleManager pm = new PuzzleManager(in, out);
		pm.handlePuzzle();

		assertTrue(pm.validateSolutionViaOutputFile());
	}

	@Test
	public void badValidateSolutionViaOutputFile() throws Exception {
		String in = "src//test//resources//files//good12Pieces.in";
		String out = "src//test//resources//files//bad12Pieces.out";


		PuzzleManager pm = new PuzzleManager(in, out);
		pm.handlePuzzle();

		assertFalse(pm.validateSolutionViaOutputFile());
	}
	


}
