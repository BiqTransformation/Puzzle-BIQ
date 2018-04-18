package course.puzzle.file;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import course.puzzle.puzzle.Parameters;
import course.puzzle.puzzle.PuzzlePiece;

public class PuzzleDataValidation {

	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private int numOfPieces;
	private ArrayList<String> inPutlist = new ArrayList<>();
	private ArrayList<PuzzlePiece> listOfPuzzlePieces = new ArrayList<>();
	private ArrayList<PuzzlePiece> listOfPuzzlePiecesAfterAllValidation = new ArrayList<>();
	private ArrayList<String> errorList = new ArrayList<>();

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

			numOfPieces = getNumberOfElements(inPutlist.get(0));
			if (numOfPieces != -1) {
				for (int i = 1; i < inPutlist.size(); i++) {
					validSetOfIntegers = integersListValidation(inPutlist.get(i));
					if (!validSetOfIntegers.isEmpty()) {
						listOfPuzzlePiecesAfterAllValidation = PuzzlePieceBuilder(validSetOfIntegers);
					}
					else{
						listOfPuzzlePiecesAfterAllValidation.clear();
						return listOfPuzzlePiecesAfterAllValidation;
					}
				}
			}
		}
		if (listOfPuzzlePiecesAfterAllValidation.size() == numOfPieces) {
			int num = validateIdSequance();
			if (num == 0) {
				if (checkIdUniqueness(listOfPuzzlePiecesAfterAllValidation)) {
					return listOfPuzzlePiecesAfterAllValidation;
				} else {
					listOfPuzzlePiecesAfterAllValidation.clear();
					return listOfPuzzlePiecesAfterAllValidation;
				}

			} else {
				String message = Parameters.PUZZLE_SIZE + listOfPuzzlePiecesAfterAllValidation.size();
				message += Parameters.MISSING_PUZZLE_ELEMENTS + num;
				errorList.add(message);
			}

		} else if (listOfPuzzlePiecesAfterAllValidation.size() < numOfPieces) {
			int diff = numOfPieces - listOfPuzzlePiecesAfterAllValidation.size();
			String message = Parameters.MISSING_PUZZLE_ELEMENTS;
			for (int i = diff + 1; i <= numOfPieces; i++) {
				message += i + ",";
				
			}
			errorList.add(message);
			listOfPuzzlePiecesAfterAllValidation.clear();
		}
		return listOfPuzzlePiecesAfterAllValidation;
	}

	/*
	 * public void startPuzzle() throws Exception{ PuzzleSolver solvePuzzle = new
	 * PuzzleSolver(listOfPuzzlePiecesAfterAllValidation); PuzzlePiece[][] puz
	 * =solvePuzzle.findSolution(); FileOutput.printSolution(puz); }
	 */

	/**
	 * firstLineValidator designed to verify the format of first line only !!! .
	 * 
	 * @param firstLine
	 * @return integer with number of pieces in case of good scenario and -1 in case
	 *         of bad scenario .
	 * @throws IOException
	 */

	protected int getNumberOfElements (String firstLine) throws IOException {
		int numOfPieces = -1;

		String[] arrStr = firstLine.trim().replaceAll(" ", "").split("=");

		if (arrStr[0].equals("NumElements")) {
			try {
				if (Integer.parseInt(arrStr[1]) >= 1) {
					numOfPieces = Integer.parseInt(arrStr[1]);
					setNumOfPieces(numOfPieces);
					return numOfPieces;
				} else {
					errorList.add(timestamp + " : " + "The NumElements less then 2 current number is " + arrStr[1]);
					return numOfPieces;
				}
			} catch (NumberFormatException e) {
				errorList.add(timestamp + " : " + "ParseInt failed , parameter is :" + arrStr[1]);
				return numOfPieces;
			}
		} else {
			errorList.add(timestamp + " : " + "Format error , expected parameter is NumElements ,but actual is :"
					+ arrStr[0]);
			return numOfPieces;

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
		// int currNum = 0;
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
						errorList.add(timestamp + " : " + "For Id : " + validId + " , parameter number :" + i
								+ " is incorrect : " + afterSplit[i]);
					}
				}
			}
			else{
				listOfIntegers.clear();
				return listOfIntegers;
			}
		} catch (NumberFormatException e) {
			errorList.add("Unepected error");
		}
		if (listOfIntegers.size() != 5) {
			errorList.add(timestamp + " : " + " List of integers different from 5 .");
			listOfIntegers.clear();
			return listOfIntegers;
		} else {
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
			errorList.add(timestamp + " : " + "Id number not valid should be more then 0 and less then NumElements.");
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
	 * @return true/false
	 * @throws IOException
	 */
	protected boolean basicFileValidator(List<String> inputlist) throws IOException {
		if (inputlist.size() < 2) {
			errorList.add(timestamp + " : " + "Input file does not contain enough information to create puzzle ");
			return false;
		}

		else {
			return true;
		}
	}

	private int validateIdSequance() {
		int num = 0;
		for (PuzzlePiece p : listOfPuzzlePiecesAfterAllValidation) {
			if (p.getId() > numOfPieces) {
				num = p.getId();
			}
		}
		return num;
	}

	protected boolean checkIdUniqueness(ArrayList<PuzzlePiece> puzzelPieces) throws IOException {
		boolean flag = true;
		ArrayList<Integer> listOfIds = new ArrayList<>();
		for (PuzzlePiece puzzlePiece : puzzelPieces) {
			listOfIds.add(puzzlePiece.getId());
		}
		for (int i = 0; i < listOfIds.size() - 1; i++) {
			for (int j = i + 1; j < (listOfIds.size()); j++) {
				if ((listOfIds.get(i)) == (listOfIds.get(j))) {
					flag = false;
					errorList.add(timestamp + " : " + "id is not unique " + listOfIds.get(j));
				}
			}
		}
		return flag;
	}

	public ArrayList<String> getErrorList() {
		return errorList;
	}

	public void setNumOfPieces(int numOfPieces) {
		this.numOfPieces = numOfPieces;
	}

}
