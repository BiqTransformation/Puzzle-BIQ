package course.puzzle.e2e;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import course.puzzle.file.FileDataValidation;
import course.puzzle.file.FileOutput;
import course.puzzle.file.FileReader;
import course.puzzle.puzzle.PuzzlePiece;
import course.puzzle.puzzle.SolvePuzzle;
import course.puzzle.puzzleManager.PuzzleManager;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class E2ETests {


private String filesPath = "src//test//resources//files//";
	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
                {"good3Pieces"},{"good4Pieces"},{"good9Pieces"},{"good12Pieces"},{"good12PiecesA"},{"good20Pieces"},{"good12PiecesB"},{"good16Pieces"},{"good24Pieces"}
		});
	}
	@Parameterized.Parameter // first data value (0) is default
	public String fInput;

	@Test
	public void testE2E() throws Exception{
		String fromPath = filesPath + fInput +".in";
		String toPath = filesPath + fInput +"Actual.txt";
		String expected = filesPath + fInput +".out";
        System.out.println("Puzzle from file: " + fromPath);
		PuzzleManager pm = new PuzzleManager(fromPath,toPath);
		pm.handlePuzzle();

		assertTrue(FileOutput.fileCompare(expected,toPath));

	}

//	@Test
//	public void testSimpleE2EWith1PuzzlePiecePuzzleManager() throws Exception{
//		String fromPath = "src//test//resources//files//test1.in";
//		String toPath = "src//test//resources//files//test1.out";
//		PuzzleManager pm = new PuzzleManager(fromPath,toPath);
//		pm.handlePuzzle();
//		String message = FileOutput.loadFromTextFile();
//		System.out.println(message);
//		assertEquals(message.trim(),"1");
//
//	}
//
//
//	@Test
//	public void test4PiecesPuzzle() throws Exception{
//		String input = filesPath + "good4Pieces.in";
//		String output = filesPath + "good4Pieces.out";
////		//clean output
//		FileOutput.path=output;
//		FileOutput.cleanOutputFile();
//		List<String> inputList =FileReader.readFromFile(input);
//		FileDataValidation fav = new FileDataValidation();
//		List<PuzzlePiece> puzzleList= fav.fileDataValidator(inputList);
//		SolvePuzzle sp = new SolvePuzzle(puzzleList);
//		PuzzlePiece[][] puz =sp.findSolution();
//
//		FileOutput.printSolution(puz);
//		String message = FileOutput.loadFromTextFile();
//		System.out.println(message);
//		assertTrue(message.contains("1 3"));
//		assertTrue(message.contains("2 4"));
//
//	}
//	@Test
//	public void test9PiecesPuzzle() throws Exception{
//		String input = filesPath + "good9Pieces.in";
//		String output = filesPath + "good9Pieces.out";
////		//clean output
//		FileOutput.path=output;
//		FileOutput.cleanOutputFile();
//		List<String> inputList =FileReader.readFromFile(input);
//		FileDataValidation fav = new FileDataValidation();
//		List<PuzzlePiece> puzzleList= fav.fileDataValidator(inputList);
//		SolvePuzzle sp = new SolvePuzzle(puzzleList);
//		PuzzlePiece[][] puz =sp.findSolution();
//
//		FileOutput.printSolution(puz);
//		String message = FileOutput.loadFromTextFile();
//		System.out.println(message);
//		assertTrue(message.contains("8 6 3"));
//		assertTrue(message.contains("9 5 2"));
//		assertTrue(message.contains("7 1 4"));
//	}
//
//
//	@Test
//	public void testSimpleNegativeE2EWithLessIDsThenNumElements() throws Exception{
//		String fromPath = "src//test//resources//files//test3.in";
//		String toPath = "src//test//resources//files//test3.out";
//		//clean output
//		FileOutput.path=toPath;
//	    FileOutput.cleanOutputFile();
//		List<String> inputList =FileReader.readFromFile(fromPath);
//		FileDataValidation fav = new FileDataValidation();
//		List<PuzzlePiece> puzzleList= fav.fileDataValidator(inputList);
//		String message = FileOutput.loadFromTextFile();
//		System.out.println(message);
//		assertTrue(message.contains("missing puzzle elements with the following IDs:3,4"));
//	}
//
//	@Test
//	public void testSimpleNegativeE2EWithLessIDsNotInSequance() throws Exception{
//		String fromPath = "src//test//resources//files//test4.in";
//		String toPath = "src//test//resources//files//test4.out";
//		//clean output
//		FileOutput.path=toPath;
//	    FileOutput.cleanOutputFile();
//		List<String> inputList =FileReader.readFromFile(fromPath);
//		FileDataValidation fav = new FileDataValidation();
//		List<PuzzlePiece> puzzleList= fav.fileDataValidator(inputList);
//		String message = FileOutput.loadFromTextFile();
//		System.out.println(message);
//		//assertTrue(message.contains("missing puzzle elements with the following IDs:3,4"));
//	}
//
	
	
}
