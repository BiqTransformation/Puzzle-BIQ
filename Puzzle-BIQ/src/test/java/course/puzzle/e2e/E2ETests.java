package course.puzzle.e2e;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import course.puzzle.file.FileDataValidation;
import course.puzzle.file.FileOutput;
import course.puzzle.file.FileReader;
import course.puzzle.puzzle.PuzzlePiece;
import course.puzzle.puzzle.SolvePuzzle;

public class E2ETests {

	
	@Test
	public void testSimpleE2EWith1ElementPuzzle() throws Exception{
		String fromPath = "src//test//resources//files//test1.in";
		String toPath = "src//test//resources//files//test1.out";
		//clean output
		FileOutput.path=toPath;
	    FileOutput.cleanOutputFile();	    
		List<String> inputList =FileReader.readFromFile(fromPath);	
		FileDataValidation fav = new FileDataValidation();
		List<PuzzlePiece> puzzleList= fav.fileDataValidator(inputList);
		SolvePuzzle sp = new SolvePuzzle(puzzleList);
		PuzzlePiece[][] puz =sp.findSolution();
		//TODO - SolvePuzzle should print
		FileOutput.printSolution(puz);		
		String message = FileOutput.loadFromTextFile();	
		assertEquals(message.trim(),"1");		
	}
	
	@Test
	public void testSimpleE2EWith1E4ementPuzzle() throws Exception{
		String fromPath = "src//test//resources//files//test2.in";
		String toPath = "src//test//resources//files//test2.out";
		//clean output
		FileOutput.path=toPath;
	    FileOutput.cleanOutputFile();	    
		List<String> inputList =FileReader.readFromFile(fromPath);	
		FileDataValidation fav = new FileDataValidation();
		List<PuzzlePiece> puzzleList= fav.fileDataValidator(inputList);
		SolvePuzzle sp = new SolvePuzzle(puzzleList);
		PuzzlePiece[][] puz =sp.findSolution();
		//TODO - SolvePuzzle should print
		FileOutput.printSolution(puz);		
		String message = FileOutput.loadFromTextFile();	
		System.out.println(message);
		assertTrue(message.contains("1 3"));
		assertTrue(message.contains("2 4"));
	}  
	
	@Test
	public void testSimpleE2EWithMissing2Elements() throws Exception{
		String fromPath = "src//test//resources//files//test3.in";
		String toPath = "src//test//resources//files//test3.out";
		//clean output
		FileOutput.path=toPath;
	    FileOutput.cleanOutputFile();
		FileReader.readFromFile(fromPath);
		//List<String> rawData = FileReader.readFromFile(fromPath);
		//assertTrue(rawData.contains("NumElements=1"));
		//assertTrue(rawData.contains("1 0 0 0 0"));	
		String message = FileOutput.loadFromTextFile();
		System.out.println(message);//to fix		
	}
	
	
	@Test
	public void testSimpleE2EWitheNotInRenageElement() throws Exception{
		String fromPath = "src//test//resources//files//test4.in";
		String toPath = "src//test//resources//files//test4.out";
		//clean output
		FileOutput.path=toPath;
	    FileOutput.cleanOutputFile();
		FileReader.readFromFile(fromPath);
		//List<String> rawData = FileReader.readFromFile(fromPath);
		//assertTrue(rawData.contains("NumElements=1"));
		//assertTrue(rawData.contains("1 0 0 0 0"));	
		String message = FileOutput.loadFromTextFile();
		System.out.println(message);//to fix
		
	}
}
