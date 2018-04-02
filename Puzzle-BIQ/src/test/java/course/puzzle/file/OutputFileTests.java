package course.puzzle.file;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import course.puzzle.puzzle.Parameters;
import course.puzzle.puzzle.PuzzlePiece;
import course.puzzle.puzzle.SolvePuzzle;

public class OutputFileTests {

	@Test
	public void WriteReadtoOutputFile() throws IOException{
		//clean file
		FileOutput.path="src/main/resources/files/output.txt";
	    FileOutput.cleanOutputFile();
		
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
	
	@Test
	public void testPuzzleSolutionPrint() throws IOException{
		FileOutput.path="src/main/resources/files/output.txt";
	    FileOutput.cleanOutputFile();
	    PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 0);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, -1, 0, 1, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 0, 1, 0);
        List<PuzzlePiece> puzzle = new ArrayList<>();
        puzzle.add(p1);
        puzzle.add(p2);
        puzzle.add(p3);
        puzzle.add(p4);
        
        PuzzlePiece[][] good = new SolvePuzzle(puzzle).prepareSolvedPuzzle();
        FileOutput.printSolution(good);
        String str =  FileOutput.loadFromTextFile();
        System.out.println(str);
        assertTrue(str.contains("1 3 4 2"));
	}
	
	

}
