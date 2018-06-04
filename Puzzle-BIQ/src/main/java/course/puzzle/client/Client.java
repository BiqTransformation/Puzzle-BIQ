package course.puzzle.client;

import java.io.DataInputStream;

import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

import course.puzzle.file.FileReader;

import course.puzzle.file.PuzzleInputDataValidation;
import course.puzzle.model.ClientInput;
import course.puzzle.model.ServerOutput;
import course.puzzle.puzzle.Puzzle;
import course.puzzle.puzzle.PuzzlePiece;

public class Client implements IClient{

	public static void main(String[] args) {

		Socket socket = null;
		DataInputStream inputStream;
		DataInputStream userInput;
		PrintStream outputStream;
		FileReader reader = new FileReader();
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();

		String line = "";
		try {
			socket = new Socket("localhost", 7000);
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new PrintStream(socket.getOutputStream());
			
			while (!line.equals("!")) {
//				reader.readFromFile("");
//				line = userInput.readLine();
//				outputStream.println(line);
//				System.out.println(inputStream.readLine());
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
				// log and ignore
			}

		}

	}

	@Override
	public List<PuzzlePiece> getPuzzlePieces(String inputFile) {
		return null;
	}

	@Override
	public Puzzle createNewPuzzle(Boolean rotate, List<PuzzlePiece> list) {
		return null;
	}

	@Override
	public ClientInput createClientInput(Puzzle puzzle) {
		return null;
	}

	@Override
	public String createJson(ClientInput input) {
		return null;
	}

	@Override
	public ServerOutput getServerOutput(String solutionJson) {
		return null;
	}

	@Override
	public void printToOutput(ServerOutput solution) {

	}
}
