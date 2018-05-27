package course.puzzle.file;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import course.puzzle.puzzle.LogMessages;
import org.junit.Test;



public class OutputFileTests {
	String path="src/main/resources/files/output.txt";
	FileOutput fo = new FileOutput(path);
	
	
	@Test
	public void WriteReadtoOutputFile() throws IOException{
		//clean file				
		fo.cleanOutputFile();		
		//save
		String message = LogMessages.CANNOT_SOLVE_PUZZLE;
		fo.printToOutputFile(message);
		//save again
		String message2 = LogMessages.MISSING_CORNER_BL;
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
	

	
	

}
