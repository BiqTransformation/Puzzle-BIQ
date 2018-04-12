package course.puzzle.puzzleManager;

import java.util.ArrayList;
import java.util.List;

import course.puzzle.file.FileOutput;
import course.puzzle.puzzle.PuzzlePiece;

public class MainPuzzle {

	public static void main(String[] args) throws Exception {
		String fromPath = "src//test//resources//files//test3.in";
		String toPath = "src//test//resources//files//test3.out";
		PuzzleManager pm = new PuzzleManager(fromPath,toPath);
		pm.handlePuzzle();
		String message = FileOutput.loadFromTextFile();	
		System.out.println(message);
		
	

	}

}
