package course.puzzle.e2e;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import course.puzzle.file.FileOutput;
import course.puzzle.puzzleManager.PuzzleManager;
public class E2E_validation {


	@Test
	public void test() throws Exception{
		String in = "C:\\Puzzle-BIQ\\test4_nR_t3.in";
		String out = "C:\\Puzzle-BIQ\\test.out";

		boolean rotate = false;
		int numOfTreads = 2;
		PuzzleManager pm = new PuzzleManager(in ,out,rotate,numOfTreads);
		pm.handlePuzzle();
		FileOutput fo = new FileOutput(out);
		String message = fo.loadFromTextFile();
		System.out.println(message);
		assertTrue(pm.validateSolutionViaOutputFile(in,out));
	}
	
	
	@Test
	public void testSimpleNegativeE2EWithLessIDsThenNumElements() throws Exception{
		String fromPath = "src//test//resources//files//test3.in";
		String toPath = "src//test//resources//files//test3.out";

		boolean rotate = true;
	    int numOfTreads = 2; 
		PuzzleManager pm = new PuzzleManager(fromPath ,toPath,rotate,numOfTreads);
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
		boolean rotate = true;
	    int numOfTreads = 2;
		 PuzzleManager pm = new PuzzleManager(fromPath ,toPath,rotate,numOfTreads);
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
		boolean rotate = true;
	    int numOfTreads = 2;
	    System.out.println("Puzzle from file: " + in+ "; rotate: " + rotate + "; number of threads: " + numOfTreads);
		PuzzleManager pm = new PuzzleManager(in, out,rotate,numOfTreads);
		pm.handlePuzzle();
		assertTrue(pm.validateSolutionViaOutputFile(in,out));
	}
	
	@Test
	public void goodValidateSolutionViaOutputFile40piecesWithoutRotate() throws Exception {
		String in = "src//test//resources//files//puzzle40Pieces.in";
		String out = "src//test//resources//files//puzzle40Pieces.out";
		boolean rotate = false;
	    int numOfTreads = 3;	    
	   System.out.println("Puzzle from file: " + in+ "; rotate: " + rotate + "; number of threads: " + numOfTreads);

		PuzzleManager pm = new PuzzleManager(in, out,rotate,numOfTreads);
		pm.handlePuzzle();		
		assertTrue(pm.validateSolutionViaOutputFile(in,out));
	}
	
	
	@Test
	public void goodValidateSolutionViaOutputFile40piecesWithRotate() throws Exception {
		String in = "src//test//resources//files//puzzle40Pieces.in";
		String out = "src//test//resources//files//puzzle40Pieces.out";
		boolean rotate = true;
	    int numOfTreads = 3;	    
	   System.out.println("Puzzle from file: " + in+ "; rotate: " + rotate + "; number of threads: " + numOfTreads);

		PuzzleManager pm = new PuzzleManager(in, out,rotate,numOfTreads);
		pm.handlePuzzle();		
		assertTrue(pm.validateSolutionViaOutputFile(in,out));
	}
	
	@Test
	public void goodValidateSolutionViaOutputFile64piecesWithoutRotate() throws Exception {
		String in = "src//test//resources//files//puzzle64Pieces.in";
		String out = "src//test//resources//files//puzzle64Pieces.out";
		boolean rotate = false;
	    int numOfTreads = 3;	    
	   System.out.println("Puzzle from file: " + in+ "; rotate: " + rotate + "; number of threads: " + numOfTreads);

		PuzzleManager pm = new PuzzleManager(in, out,rotate,numOfTreads);
		pm.handlePuzzle();		
		assertTrue(pm.validateSolutionViaOutputFile(in,out));
	}
	
	@Test
	public void goodValidateSolutionViaOutputFile64piecesWithRotate() throws Exception {
		String in = "src//test//resources//files//puzzle64Pieces.in";
		String out = "src//test//resources//files//puzzle64Pieces.out";
		boolean rotate = true;
	    int numOfTreads = 3;	    
	   System.out.println("Puzzle from file: " + in+ "; rotate: " + rotate + "; number of threads: " + numOfTreads);

		PuzzleManager pm = new PuzzleManager(in, out,rotate,numOfTreads);
		pm.handlePuzzle();		
		assertTrue(pm.validateSolutionViaOutputFile(in,out));
	}
	
	
	@Test
	public void goodValidateSolutionViaOutputFile150piecesWithoutRotate() throws Exception {
		String in = "src//test//resources//files//puzzle150Pieces.in";
		String out = "src//test//resources//files//puzzle150Pieces.out";
		boolean rotate = false;
	    int numOfTreads = 3;	    
	   System.out.println("Puzzle from file: " + in+ "; rotate: " + rotate + "; number of threads: " + numOfTreads);

		PuzzleManager pm = new PuzzleManager(in, out,rotate,numOfTreads);
		pm.handlePuzzle();		
		assertTrue(pm.validateSolutionViaOutputFile(in,out));
	}
	
	
	@Test
	public void goodValidateSolutionViaOutputFile150piecesWithRotate() throws Exception {
		String in = "src//test//resources//files//puzzle150Pieces.in";
		String out = "src//test//resources//files//puzzle150Pieces.out";
		boolean rotate = true;
	    int numOfTreads = 3;	    
	   System.out.println("Puzzle from file: " + in+ "; rotate: " + rotate + "; number of threads: " + numOfTreads);

		PuzzleManager pm = new PuzzleManager(in, out,rotate,numOfTreads);
		pm.handlePuzzle();		
		assertTrue(pm.validateSolutionViaOutputFile(in,out));
	}
	

	@Test
	public void badValidateSolutionViaOutputFile() throws Exception {
		String in = "src//test//resources//files//good12Pieces.in";
		String out = "src//test//resources//files//bad12Pieces.out";
        boolean rotate = false;
	    int numOfTreads = 1;
		PuzzleManager pm = new PuzzleManager(in, out,rotate,numOfTreads);

		assertFalse(pm.validateSolutionViaOutputFile(in,out));
	}
	


}
