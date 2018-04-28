package course.puzzle.puzzleManager;

import java.util.ArrayList;
import java.util.List;

import course.puzzle.file.FileOutput;
import course.puzzle.puzzle.PuzzlePiece;

public class MainPuzzle {

	public static void main(String[] args) throws Exception {
		
		int numOfThreads =0;
		String fromPath = args[0];//"src//test//resources//files//test3.in";
		String toPath = args[1];//"src//test//resources//files//test3.out";
		String rotate = args[2];		
		boolean rotBoolean = Boolean.parseBoolean(rotate);
		String threads = args[3] != null ? args[3] : "4";	
		
		try{
			numOfThreads  = Integer.parseInt(threads);
		}
		catch(NumberFormatException e){};
		
		
		
		PuzzleManager pm = new PuzzleManager(fromPath,toPath,rotBoolean,numOfThreads);
		pm.handlePuzzle();
		FileOutput fo = new FileOutput(toPath);
		String message = fo.loadFromTextFile();	
		System.out.println(message);
		
	

	}

}
