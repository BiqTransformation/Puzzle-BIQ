package course.puzzle.file;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class FileReaderWriterTest {

	@Test
	public void fileReaderVerification() throws IOException {  
		ArrayList<String> expected = new ArrayList<>();
		expected.add("10000");
		expected.add("2-100");
		String fromPath = "c:\\FileForPuzzleTest\\puzzle.txt";
		FileReaderWriter file = new FileReaderWriter();
		ArrayList actual = file.readFromFile(fromPath);
		assertEquals(expected, actual);	
	}
}
