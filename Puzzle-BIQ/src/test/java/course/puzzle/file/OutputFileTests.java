package course.puzzle.file;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import course.puzzle.puzzle.Parameters;

public class OutputFileTests {

	@Test
	public void WriteReadtoOutputFile() throws IOException{
		//save
		FileOutputStream fos = new  FileOutputStream("output.txt");
		String message = Parameters.CANNOT_SOLVE_PUZZLE;
		FileOutput.saveAsTextFile(fos, message);
		//load
		FileInputStream fis = new FileInputStream("output.txt");
		String message2 = FileOutput.loadFromTextFile(fis);
		
		assertEquals(message,message2);
		
	}

}
