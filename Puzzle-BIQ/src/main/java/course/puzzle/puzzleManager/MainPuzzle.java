package course.puzzle.puzzleManager;

import java.util.ArrayList;
import java.util.List;

import course.puzzle.file.FileOutput;
import course.puzzle.puzzle.PuzzlePiece;

public class MainPuzzle {

	public static void main(String[] args) throws Exception {
		
		
		String fromPath = args[0];//"src//test//resources//files//test3.in";
		String toPath = args[1];//"src//test//resources//files//test3.out";
		String rotate = args[2];
		boolean rotBoolean = Boolean.parseBoolean(rotate);
		String treads = args[3];
		int numOfTreads = Integer.parseInt(treads);
		
		
		PuzzleManager pm = new PuzzleManager(fromPath,toPath,rotBoolean,numOfTreads);
		pm.handlePuzzle();
		FileOutput fo = new FileOutput(toPath);
		String message = fo.loadFromTextFile();	
		System.out.println(message);
		
	

	}

}
