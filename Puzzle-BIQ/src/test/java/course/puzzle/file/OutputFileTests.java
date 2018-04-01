package course.puzzle.file;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import org.junit.Test;

import course.puzzle.puzzle.Parameters;

public class OutputFileTests {

	@Test
	public void WriteReadtoOutputFile() throws IOException{
		//clean file
		try{
			PrintWriter writer = new PrintWriter("src/main/resources/files/output.txt");
			writer.print("");
			writer.close();
		}
		catch(IOException e){
			
		}
		
		//save
		String message = Parameters.CANNOT_SOLVE_PUZZLE;
		FileOutput.saveAsTextFile(message);		
		//save again
		String message2 = Parameters.MISSING_CORNER_BL;
		FileOutput.saveAsTextFile(message2);
		//load
		//message2 =message +"\n" + message2;
		//System.out.println("message2 " + message2);
		//System.out.println();
		String message3 =  FileOutput.loadFromTextFile();
		//System.out.println("message3 " + message3);
		assertTrue(message3.contains("Cannot solve puzzle:"));
		assertTrue(message3.contains("missing corner element: BL"));
	}
	
	

}
