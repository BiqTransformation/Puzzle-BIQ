package course.puzzle.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import course.puzzle.puzzle.PuzzlePiece;

public class FileReaderWriterTest {

	@Test
	public void fileReaderVerification() throws IOException {  
		PuzzlePiece piece1 = new PuzzlePiece(1, -1, 0, 0, 0);
		PuzzlePiece piece2 = new PuzzlePiece(2, 0, 0, 0, 0);
		ArrayList<PuzzlePiece> expected = new ArrayList<>();
		expected.add(piece1);
		expected.add(piece2);
		
		String fromPath = "src/main/resources/files/puzzle1";
		FileReaderWriter file = new FileReaderWriter();
		ArrayList actual = file.readFromFile(fromPath);
		
		FileDataValidation validator = new FileDataValidation(actual);
		ArrayList<PuzzlePiece> pieces =validator.fileDataValidator();
		
		assertTrue(expected.equals(pieces));	
	}
}
