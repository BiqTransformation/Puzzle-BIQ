package course.puzzle.puzzleManager;

import java.util.ArrayList;
import java.util.List;

import course.puzzle.file.FileOutput;
import course.puzzle.puzzle.PuzzlePiece;

public class MainPuzzle {

	public static void main(String[] args) throws Exception {
		//TODO- add parameter for rotate and number of threads - Alex
		String fromPath = args[0];//"src//test//resources//files//test3.in";
		String toPath = args[1];//"src//test//resources//files//test3.out";
		PuzzleManager pm = new PuzzleManager(fromPath,toPath);
		pm.handlePuzzle();
		FileOutput fo = new FileOutput(toPath);
		String message = fo.loadFromTextFile();	
		System.out.println(message);
		
	

	}

}
