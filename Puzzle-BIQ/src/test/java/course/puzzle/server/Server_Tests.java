package course.puzzle.server;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;

import course.puzzle.puzzle.Puzzle;
import course.puzzle.puzzle.PuzzlePiece;
public class Server_Tests {
	
	@Test
	public void serverGetJsonAndBuildPuzzle() throws IOException{
		String puzzleTest = "{\"puzzlePieces\":[{\"id\":1,\"leftValue\":1,\"topValue\":0,\"rightValue\":0,\"bottomValue\":0,\"shape\":{\"edges\":[1,0,0,0]}},{\"id\":2,\"leftValue\":0,\"topValue\":0,\"rightValue\":0,\"bottomValue\":-1,\"shape\":{\"edges\":[0,0,0,-1]}}],\"isRotate\":false}";

		Server server = new Server(3,7000);
		Puzzle puzzle = server.readJson(puzzleTest);
		assertFalse(puzzle.getRotate());
		List<PuzzlePiece> sut =puzzle.getPuzzlePieces();
		assertTrue(sut.size()==2);	

}
	
	
	@Test
	public void serverSetJson() throws IOException{
		PuzzlePiece pp1 = new PuzzlePiece(1,1,0,0,0);
		PuzzlePiece pp2 = new PuzzlePiece(2,0,0,0,-1);
		List<PuzzlePiece> puzzlePack = new ArrayList<>();
		puzzlePack.add(pp1);
		puzzlePack.add(pp2);
		Puzzle puzzle = new Puzzle(false,puzzlePack);
		Gson gson = new Gson();
		String json = gson.toJson(puzzle);
		System.out.println(json);
		assertTrue(json.contains("\"id\":1"));
		assertTrue(json.contains("\"edges\":[1,0,0,0]"));
		

}
	
}
