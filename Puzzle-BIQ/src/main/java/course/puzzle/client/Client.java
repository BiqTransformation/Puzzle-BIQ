package course.puzzle.client;

import java.io.DataInputStream;

import java.io.PrintStream;
import java.net.Socket;

import course.puzzle.file.FileReader;

import course.puzzle.file.PuzzleInputDataValidation;

public class Client {

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

}
