package course.puzzle.client;

import course.puzzle.model.ClientInput;
import course.puzzle.model.ServerOutput;
import course.puzzle.puzzle.Puzzle;
import course.puzzle.puzzle.PuzzlePiece;

import java.util.List;

public interface IClient {
    public List<PuzzlePiece> getPuzzlePieces(String inputFile);
    public Puzzle createNewPuzzle(Boolean rotate, List<PuzzlePiece> list);
    public ClientInput createClientInput(Puzzle puzzle);
    public String createJson(ClientInput input);
    public ServerOutput getServerOutput(String solutionJson);
    public void printToOutput(ServerOutput solution);
}
