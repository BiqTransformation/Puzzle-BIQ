package course.puzzle.puzzleManager;

import java.util.ArrayList;
import java.util.List;

import course.puzzle.file.FileOutput;
import course.puzzle.puzzle.PuzzlePiece;

public class MainPuzzle {

	public static void main(String[] args) throws Exception {
//		String fromPath = "src//test//resources//files//test2.in";
//		String toPath = "src//test//resources//files//test2.out";
//		PuzzleManager pm = new PuzzleManager(fromPath,toPath);
//		pm.handlePuzzle();
//		String message = FileOutput.loadFromTextFile();	
//		System.out.println(message);
		
		
		PuzzlePiece[][] board = new PuzzlePiece[2][2];
		PuzzlePiece p1 = new PuzzlePiece(1,0,0,-1,1);
		PuzzlePiece p2 = new PuzzlePiece(2,0,-1,-1,1);
		PuzzlePiece p3 = new PuzzlePiece(3,1,0,0,-1);
		PuzzlePiece p4 = new PuzzlePiece(4,0,1,0,0);
		List<PuzzlePiece> pack = new ArrayList<>();
		pack.add(p1);
		pack.add(p2);
		pack.add(p3);
		pack.add(p4);

	}

}
