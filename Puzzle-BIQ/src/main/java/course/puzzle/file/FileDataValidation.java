package course.puzzle.file;

import java.util.ArrayList;

import course.puzzle.puzzle.PuzzlePiece;

public class FileDataValidation {

    private int numOfPieces;
	private ArrayList<String> inPutlist = new ArrayList<>();
	ArrayList<PuzzlePiece> listOfPuzzlePieces = new ArrayList<>();
	ArrayList<PuzzlePiece> listOfPuzzlePiecesAfterAfterAllValidation = new ArrayList<>();
	/** The method return list of puzzle pieces after all data from file was validated .
	 * @param inPutlist
	 * @return listOfPuzzlePieces
	 */
	public ArrayList<PuzzlePiece> fileDataValidator(ArrayList<String> inPutlist) {
		ArrayList<Integer> validSetOfIntegers = null;
		
		try {

			int numOfPieces = firstLineValidator(inPutlist.get(0));

			if (numOfPieces != -1) {
                
				for (int i = 1; i < inPutlist.size(); i++) {

					validSetOfIntegers = integersListValidation(inPutlist.get(i));

					if (!validSetOfIntegers.isEmpty()) {
						listOfPuzzlePiecesAfterAfterAllValidation = PuzzlePieceBuilder(validSetOfIntegers);
					} else {
						
						// TO DO write to Error File .
					}
				}
			} else {
				// TO DO write to Error File .
			}
		} catch (Exception e) {

			// TO DO write to Error File .
		}
		
		if (listOfPuzzlePiecesAfterAfterAllValidation.size() == numOfPieces) {
			return listOfPuzzlePiecesAfterAfterAllValidation;
		}
		else {
			listOfPuzzlePiecesAfterAfterAllValidation.clear();
			return listOfPuzzlePiecesAfterAfterAllValidation;
		}
			
	}

	/** firstLineValidator designed to verify the format of first line only .
	 * @param firstLine
	 * @return integer with number of pieces in case of good scenario and -1 in case of bag scenario .
	 */
	protected int firstLineValidator(String firstLine) {
		int numOfPieces = 0;

		String[] arrStr = firstLine.trim().split("=");

		if (arrStr[0].equals("NumElements")) {
			if (Integer.parseInt(arrStr[1]) > 1) {
				numOfPieces = Integer.parseInt(arrStr[1]);
			} else {
				numOfPieces = -1;
			}
		} else {
			numOfPieces = -1;
			// TO DO write error to file .
		}
		
		setNumOfPieces(numOfPieces);
		
		return numOfPieces;
	}
	
	protected int idNumberValidation(int idNum) {
		int returnIdNum = 0 ;
		if (idNum <=  numOfPieces && idNum > 0) {
			returnIdNum = idNum ;
		}
		else {
			returnIdNum = -1;
			//  // TO DO write error to file .
		}
		return returnIdNum;	
	}

	/** Every line include the 5 integers 1 number it's piece Id ,others  4 are sides of puzzle.
	 * 
	 * @param str
	 * @return
	 */
	protected ArrayList<Integer> integersListValidation(String str) {
		int numOfPiece = 0;
		ArrayList<Integer> listOfIntegers = new ArrayList<>();
		
		String[] afterSplit = str.trim().split(" ");
		int idNum;
		try {
			int validId = idNumberValidation(Integer.parseInt(afterSplit[0]));
			    if (validId != -1) {
			    	listOfIntegers.add(validId);
			    }
			    
		} catch (NumberFormatException e1) {
			// TO DO write error to file .
			e1.printStackTrace();
		}
		
		for (int i = 1; i < afterSplit.length; i++) {
			if (afterSplit[i].equals("-1") || afterSplit[i].equals("0") || afterSplit[i].equals("1")) {
				int currNum;
				try {
					currNum = Integer.parseInt(afterSplit[i]);
				} catch (Exception e) {
					throw new NumberFormatException();
					// TO DO write error to file .
				}
				listOfIntegers.add(currNum);
			} else {
				listOfIntegers.clear();
				// TO DO write error to file .
				break;
				
			}
		}
		return listOfIntegers;
	}

	/**
	 * @param listOfvalidintegers
	 * @return
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

	public void setNumOfPieces(int numOfPieces) {
		this.numOfPieces = numOfPieces;
	}
	
	
}
