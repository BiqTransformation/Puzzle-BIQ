package course.puzzle.file;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import course.puzzle.puzzle.Parameters;
import course.puzzle.puzzle.PuzzlePiece;
import course.puzzle.puzzle.SolvePuzzle;

public class FileDataValidation {
    
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private int numOfPieces;
	private ArrayList<String> inPutlist = new ArrayList<>();
	private ArrayList<PuzzlePiece> listOfPuzzlePieces = new ArrayList<>();
	private ArrayList<PuzzlePiece> listOfPuzzlePiecesAfterAllValidation = new ArrayList<>();

	/**
	 * The method return list of puzzle pieces after all data from file was
	 * validated .
	 * 
	 * @param inPutlist
	 * @return listOfPuzzlePieces
	 * @throws Exception 
	 */
	public List<PuzzlePiece> fileDataValidator(List<String> inPutlist) throws Exception {
		List<Integer> validSetOfIntegers = null;
		
		if (basicFileValidator(inPutlist)) {
			
				numOfPieces = firstLineValidator(inPutlist.get(0));
				if (numOfPieces != -1) {
					for (int i = 1; i < inPutlist.size(); i++) {
						validSetOfIntegers = integersListValidation(inPutlist.get(i));
						if (!validSetOfIntegers.isEmpty()) { 
							listOfPuzzlePiecesAfterAllValidation = PuzzlePieceBuilder(validSetOfIntegers);
						} 
					}
				} 
		} 
		if (listOfPuzzlePiecesAfterAllValidation.size() == numOfPieces) {
			startPuzzle();
			return listOfPuzzlePiecesAfterAllValidation;
		} else {
			//TODO need to get the missing ids
			FileOutput.printToOutputFile(timestamp+ " : " +Parameters.MISSING_PUZZLE_ELEMENTS + numOfPieces +" actual : "+ listOfPuzzlePiecesAfterAllValidation.size() ); 
			listOfPuzzlePiecesAfterAllValidation.clear();
			return listOfPuzzlePiecesAfterAllValidation;
		}
	}
	
	public void startPuzzle() throws Exception{
		SolvePuzzle solvePuzzle = new SolvePuzzle(listOfPuzzlePiecesAfterAllValidation);
		PuzzlePiece[][] puz =solvePuzzle.findSolution();
		FileOutput.printSolution(puz);
	}

	/**
	 * firstLineValidator designed to verify the format of first line only !!! .
	 * 
	 * @param firstLine
	 * @return integer with number of pieces in case of good scenario and -1 in case
	 *         of bad scenario .
	 * @throws IOException 
	 */
	
	protected int firstLineValidator(String firstLine) throws IOException {
		int numOfPieces = -1;

		String[] arrStr = firstLine.trim().replaceAll(" ", "").split("=");

		if (arrStr[0].equals("NumElements")) {
			try {
				if (Integer.parseInt(arrStr[1]) >= 1) {
					numOfPieces = Integer.parseInt(arrStr[1]);
					setNumOfPieces(numOfPieces);
					return numOfPieces;
				} else {	
						FileOutput.printToOutputFile(timestamp+" : "+"The NumElements less then 2 current number is "+ arrStr[1]);
					return numOfPieces;
				}
			} catch (NumberFormatException e) {
						
					FileOutput.printToOutputFile(timestamp+" : "+"ParseInt failed , parameter is :"+ arrStr[1]);
				return numOfPieces;
			}
		} else {
			FileOutput.printToOutputFile(timestamp+" : "+"Format error , expected parameter is NumElements ,but actual is :"+ arrStr[0]);
			return numOfPieces;
			
		}
		return numOfPieces;
	}

	/**
	 * Every line include the 5 integers 1 number it's piece Id ,others 4 are sides
	 * of puzzle.
	 * 
	 * @param str
	 * @return listOfIntegers
	 * @throws IOException 
	 */
	
	protected ArrayList<Integer> integersListValidation(String str) throws IOException {
		//int currNum = 0;
		ArrayList<Integer> listOfIntegers = new ArrayList<>();
		String[] afterSplit = str.trim().split(" ");
		try {
			int validId = idNumberValidation(Integer.parseInt(afterSplit[0]));
			if (validId != -1) {
				listOfIntegers.add(validId);
				for (int i = 1; i < afterSplit.length; i++) {
					if (afterSplit[i].equals("-1") || afterSplit[i].equals("0") || afterSplit[i].equals("1")) {		
							listOfIntegers.add(Integer.parseInt(afterSplit[i]));
					} else {
						FileOutput.printToOutputFile(timestamp+" : "+"For Id : "+validId+" , parameter number :"+ i +" is incorrect : "+ afterSplit[i]);
					}
				}
			} 
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (listOfIntegers.size() != 5) {
			FileOutput.printToOutputFile(timestamp+" : "+" List of integers defferent from 5 .");
			listOfIntegers.clear();
			return 	listOfIntegers;
		}
		else {			
			return listOfIntegers;
		}
		
	}
	
	/**
	 * @param idNum
	 * @return returnIdNum
	 * @throws IOException 
	 */
	protected int idNumberValidation(int idNum) throws IOException {
		int returnIdNum = -1;
		if (idNum <= numOfPieces && idNum > 0) {
			returnIdNum = idNum;
			return returnIdNum;
		} else {
			FileOutput.printToOutputFile(timestamp+ " : " + "Id number not valid should be more then 0 and less then NumElements." );
			return returnIdNum;	
		}
	}
	/**
	 * Method build the single puzzle piece and add him to list.
	 * 
	 * @param listOfvalidintegers
	 * @return listOfPuzzlePieces
	 */
	protected ArrayList<PuzzlePiece> PuzzlePieceBuilder(List<Integer> listOfvalidintegers) {

		int id = listOfvalidintegers.get(0);
		int left = listOfvalidintegers.get(1);
		int top = listOfvalidintegers.get(2);
		int right = listOfvalidintegers.get(3);
		int bottom = listOfvalidintegers.get(4);

		PuzzlePiece piece = new PuzzlePiece(id, left, top, right, bottom);

		listOfPuzzlePieces.add(piece);

		return listOfPuzzlePieces;
	}

	/**
	 * @param list
	 * @return  true/false
	 * @throws IOException 
	 */
	protected boolean basicFileValidator(List<String> inputlist) throws IOException {
		if (inputlist.size() <2) {
			FileOutput.printToOutputFile(timestamp+ " : " + "In put file does not contain enough information to create puzzle ");
			return false;					
		} 
		
		else {
			return true;
		}
	}

	public void setNumOfPieces(int numOfPieces) {
		this.numOfPieces = numOfPieces;
	}

}
