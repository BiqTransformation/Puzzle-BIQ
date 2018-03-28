package course.puzzle.file;

import java.util.ArrayList;

import course.puzzle.puzzle.PuzzlePiece;

public class FileDataValidation {

	private int numberOfPieces = 24;
	private ArrayList<String> list = new ArrayList<>();
    private ArrayList<PuzzlePiece> listOfPiecesOutPut = new ArrayList<>();
	
	public FileDataValidation(ArrayList<String> list) {
		this.list = list;
	}

	public ArrayList<PuzzlePiece> fileDataValidator() {

		try {
			int numOfPuzzlePices = Integer.parseInt(list.get(0));
			if (numOfPuzzlePices == numberOfPieces) {
				for (int i = 1; i < list.size(); i++) {
					ArrayList<Integer> validIntegersList = new ArrayList<>(); ;
					ArrayList<Integer> listOfIntegers = convertStringToIntegers(list.get(i).replaceAll(" ", ""));

					if (listOfIntegers.get(0) >= 0 && listOfIntegers.get(0) <= numberOfPieces) {
						validIntegersList.add(listOfIntegers.get(0));
						for (int j = 1; j < listOfIntegers.size(); j++) {
							if (listOfIntegers.get(j) >= -1 && listOfIntegers.get(j) <= 1) {
								validIntegersList.add(listOfIntegers.get(j));
							} else {
								// TO DO write to Error File .
								break;
							}
						}
					} else {
						throw new Exception();
						// TO DO write to Error File .
					}

					if (validIntegersList.size() == 5) {

						PuzzlePieceBuilder(validIntegersList);

					} else {
						throw new Exception();
						// TO DO write to Error File .
					}
				}
			} else {
				throw new Exception();
				// TO DO write to Error File .
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfPiecesOutPut;
	}
	
	private ArrayList<Integer> convertStringToIntegers(String str) {
		ArrayList<Integer> numbers = new ArrayList<>();
		String [] arrOfString =	str.split(",");
		
		try {
			for (int i = 0; i < arrOfString.length; i++) {
				int num = Integer.parseInt(arrOfString[i]);
				numbers.add(num);
			}
		} catch (NumberFormatException e) {
			// TO DO Error message to file .
			e.printStackTrace();
		}
		return numbers;
	}

	private ArrayList<PuzzlePiece> PuzzlePieceBuilder(ArrayList<Integer> listOfvalidintegers) {

		int id = listOfvalidintegers.get(0);
		int left = listOfvalidintegers.get(1);
		int top = listOfvalidintegers.get(2);
		int right = listOfvalidintegers.get(3);
		int bottom = listOfvalidintegers.get(4);
			
		PuzzlePiece piece= new PuzzlePiece(id, left, top, right, bottom);
		
		listOfPiecesOutPut.add(piece);
		
		return listOfPiecesOutPut;
	}
}
