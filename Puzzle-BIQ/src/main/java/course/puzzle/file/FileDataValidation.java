package course.puzzle.file;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import course.puzzle.puzzle.PuzzlePiece;

public class FileDataValidation {
    
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private int numOfPieces;
	private ArrayList<String> inPutlist = new ArrayList<>();
	ArrayList<PuzzlePiece> listOfPuzzlePieces = new ArrayList<>();
	ArrayList<PuzzlePiece> listOfPuzzlePiecesAfterAllValidation = new ArrayList<>();

	/**
	 * The method return list of puzzle pieces after all data from file was
	 * validated .
	 * 
	 * @param inPutlist
	 * @return listOfPuzzlePieces
	 * @throws IOException 
	 */
	public ArrayList<PuzzlePiece> fileDataValidator(ArrayList<String> inPutlist) throws IOException {
		ArrayList<Integer> validSetOfIntegers = null;
		if (basicFileValidator(inPutlist)) {
			try {
				numOfPieces = firstLineValidator(inPutlist.get(0));
				if (numOfPieces != -1) {

					for (int i = 1; i < inPutlist.size(); i++) {

						validSetOfIntegers = integersListValidation(inPutlist.get(i));

						if (validSetOfIntegers.size() == 5) {  ///// Should be prepared !!!!!!!!!!!!!!!!!!!1
							listOfPuzzlePiecesAfterAllValidation = PuzzlePieceBuilder(validSetOfIntegers);
						} else {
							FileOutput.printToOutputFile(timestamp+" : "+"Puzzle will not be complited , one or more parametrs are incorrect. ");
						}
					}
				} 
			} catch (Exception e) {

				// TO DO write to Error File .
			}
		} else {
			FileOutput.printToOutputFile(timestamp+ " : " + "Input list not valid !!!  ");
		}

		if (listOfPuzzlePiecesAfterAllValidation.size() == numOfPieces) {
			return listOfPuzzlePiecesAfterAllValidation;
		} else {
			FileOutput.printToOutputFile(timestamp+ " : " + "number of puzzle pieces not equal to requared : "+numOfPieces +" actual : "+ listOfPuzzlePiecesAfterAllValidation.size() ); 
			listOfPuzzlePiecesAfterAllValidation.clear();
			return listOfPuzzlePiecesAfterAllValidation;
		}
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
				if (Integer.parseInt(arrStr[1]) > 1) {
					numOfPieces = Integer.parseInt(arrStr[1]);
					setNumOfPieces(numOfPieces);
					return numOfPieces;

				} else {	
						FileOutput.printToOutputFile(timestamp+" : "+"The NumElements less then 2 current number is "+ arrStr[1]);
					return numOfPieces;
				
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();		
					FileOutput.printToOutputFile(timestamp+" : "+"ParseInt failed , parameter is :"+ arrStr[1]);
				return numOfPieces;
			}
		} else {
			FileOutput.printToOutputFile(timestamp+" : "+"Format error , expected parameter is NumElements ,but actual is :"+ arrStr[0]);
			return numOfPieces;
			
		}
	}

	/**
	 * @param idNum
	 * @return returnIdNum
	 */
	protected int idNumberValidation(int idNum) {
		int returnIdNum = -1;
		if (idNum <= numOfPieces && idNum > 0) {
			returnIdNum = idNum;
			return returnIdNum;
		} else {
			return returnIdNum;
			// // TO DO write error to file .
		}

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
		int currNum = 0;
		ArrayList<Integer> listOfIntegers = new ArrayList<>();

		String[] afterSplit = str.trim().split(" ");

		try {
			int validId = idNumberValidation(Integer.parseInt(afterSplit[0]));
			if (validId != -1) {
				listOfIntegers.add(validId);
				for (int i = 1; i < afterSplit.length; i++) {
					if (afterSplit[i].equals("-1") || afterSplit[i].equals("0") || afterSplit[i].equals("1")) {

						try {
							listOfIntegers.add(Integer.parseInt(afterSplit[i]));

						} catch (NumberFormatException e) {
							e.printStackTrace();
							FileOutput.printToOutputFile(timestamp+" : "+"ParseInt failed , parameter is :"+ afterSplit[i]);
						}
					} else {
						FileOutput.printToOutputFile(timestamp+"For Id : "+validId+" , parameter number :"+ i +" is incorrect : "+ afterSplit[i]);
						//break;
					}
				}
			} else {
				FileOutput.printToOutputFile(timestamp+" Id number incorrect !!!");
				listOfIntegers.remove(0);	
			}

		} catch (NumberFormatException e) {
			
			//FileOutput.saveAsTextFile(timestamp+" : "+"ParseInt failed , parameter is :"+ afterSplit[i]);
			//listOfIntegers.clear();
			// TO DO write error to file .
			e.printStackTrace();
		}

		return listOfIntegers;
	}

	/**
	 * Method build the single puzzle piece and add him to list.
	 * 
	 * @param listOfvalidintegers
	 * @return listOfPuzzlePieces
	 */
	protected ArrayList<PuzzlePiece> PuzzlePieceBuilder(ArrayList<Integer> listOfvalidintegers) {

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
	 */
	protected boolean basicFileValidator(ArrayList<String> inputlist) {

		if (inputlist.size() <= 2) {
			return false;
		} else {
			return true;
		}
	}

	public void setNumOfPieces(int numOfPieces) {
		this.numOfPieces = numOfPieces;
	}

}
