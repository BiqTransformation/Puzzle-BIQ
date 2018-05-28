package course.puzzle.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import course.puzzle.puzzle.PuzzlePiece;

public class PuzzleDataValidationTests {

	private final static String InputFileNamePath ="src/main/resources/files/inPutForDataValidationTests.txt";
	private final static String WrongInputFileNamePath ="src/main/resources/files/input111.txt";
	@Test
	public void goodValidatorIntegrationFlow() throws Exception  {
		FileReader reader = new FileReader();
		List<String> rawData = reader.readFromFile(InputFileNamePath);		
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		List<PuzzlePiece> actualPiecesList = validator.fileDataValidator(rawData);
		ArrayList<PuzzlePiece> expectedPiecesList = new ArrayList<>();
		expectedPiecesList.add(new PuzzlePiece(1, -1, 0, 0, 0));
		expectedPiecesList.add(new PuzzlePiece(2, 0, 0, 0, 0));
		expectedPiecesList.add(new PuzzlePiece(3, 0, 0, 0, 0));
		assertEquals(expectedPiecesList,actualPiecesList);
	}
	
	@Test
	public void goodValidatorIntegrationFlow1() throws Exception  {
		FileReader reader = new FileReader();
		List<String> rawData = reader.readFromFile(InputFileNamePath);		
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		List<PuzzlePiece> actualPiecesList = validator.fileDataValidator(rawData);
		ArrayList<PuzzlePiece> expectedPiecesList = new ArrayList<>();
		expectedPiecesList.add(new PuzzlePiece(1, -1, 0, 0, 0));
		expectedPiecesList.add(new PuzzlePiece(2, 0, 0, 0, 0));
		expectedPiecesList.add(new PuzzlePiece(3, 0, 0, 0, 0));
		assertEquals(expectedPiecesList,actualPiecesList);
	}
	
	@Test (expected=RuntimeException.class)
	public void fileReaderException() throws  Exception {
		FileReader reader = new FileReader();
		List<String> rawData = reader.readFromFile(WrongInputFileNamePath);	
	}
	
	@Test 
	public void good_fileDataValidator() throws Exception {
			
		ArrayList<String> input = new ArrayList<>();
		input.add("NumElements=2");
		input.add("1 0 0 1 0");
		input.add("2 -1 0 0 0");
		
		PuzzlePiece piece1 = new PuzzlePiece(1, 0, 0, 1, 0);
		PuzzlePiece piece2 = new PuzzlePiece(2, -1, 0, 0, 0);
		ArrayList<PuzzlePiece> expected = new ArrayList<>();
		expected.add(piece1);
		expected.add(piece2);
		
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		List<PuzzlePiece> actual = validator.fileDataValidator(input);
		assertEquals(expected.get(0).getId(), actual.get(0).getId());
		
	}
	
	@Test 
	public void good_PuzzlePieceIdUnique() throws Exception {
		PuzzlePiece piece1 = new PuzzlePiece(1, 0, 0, 1, 0);
		PuzzlePiece piece2 = new PuzzlePiece(2, -1, 0, 0, 0);
		PuzzlePiece piece3 = new PuzzlePiece(3, -1, 0, 0, 0);
		ArrayList<PuzzlePiece> expected = new ArrayList<>();
		expected.add(piece1);
		expected.add(piece2);
		expected.add(piece3);
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		boolean actual = validator.checkIdUniqueness(expected);
		assertTrue(actual);
		
	}
	
	@Test 
	public void bad_PuzzlePieceIdNotUnique() throws Exception {
		PuzzlePiece piece1 = new PuzzlePiece(2, 0, 0, 1, 0);
		PuzzlePiece piece2 = new PuzzlePiece(1, -1, 0, 0, 0);
		PuzzlePiece piece3 = new PuzzlePiece(2, -1, 0, 0, 0);
		
		ArrayList<PuzzlePiece> expected = new ArrayList<>();
		expected.add(piece1);
		expected.add(piece2);
		expected.add(piece3);
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		boolean actual = validator.checkIdUniqueness(expected);
		assertFalse(actual);
		
	}
	
	@Test 
	public void bad_PuzzlePieceIdIncorrectMoreThenTotalNumberOfElements() throws Exception {	
		ArrayList<String> input = new ArrayList<>();
		input.add("NumElements=2");
		input.add("1 -1 0 0 0");
		// the id more then total elements.
		input.add("3 0 0 0 0");
		ArrayList<PuzzlePiece> expected = new ArrayList<>();	
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		List<PuzzlePiece> actual = validator.fileDataValidator(input);
		// Empty list was received .
		assertEquals(expected, actual);
		
	}
	
	@Test 
	public void bad_ExpectedNumberOfElementsMoreThenPuzzlePieces() throws Exception {	
		ArrayList<String> input = new ArrayList<>();
		// NumElements more then actual received.
		input.add("NumElements=3");
		input.add("1 -1 0 0 0");
		input.add("2 0 0 0 0");
		ArrayList<PuzzlePiece> expected = new ArrayList<>();
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		List<PuzzlePiece> actual = validator.fileDataValidator(input);
		// Empty list was received .
		assertEquals(expected, actual);
		
	}
	@Test
	public void goodTestOFfirstLineValidator() throws IOException {
		int expected = 2;
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		String str = "NumElements=2";
		int actual = validator.getNumberOfElements(str);
		assertEquals(expected, actual);
	}
	
	@Test
	public void badTestOFfirstLineValidatorIncludedSpaces() throws IOException {
		int expected = 2;
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		String str = "NumElem ents=    2";
		int actual = validator.getNumberOfElements(str);
		assertEquals(expected, actual);
	}
	
	@Test
	public void badTestOFgetNumberOfElementsInvalidChar_$() throws IOException {
		int expected = -1;
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		String str = "NumElements=$";
		int actual = validator.getNumberOfElements(str);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void bad_NumElementsIlligal_0() throws IOException {
		int expected = -1;
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		String str = "NumElements=0";
		int actual = validator.getNumberOfElements(str);
		assertEquals(expected, actual);
	}

	
	@Test
	public void bad_NumElementsIlligal_1() throws IOException {
		int expected = 1;
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		String str = "NumElements=1";
		int actual = validator.getNumberOfElements(str);
		assertEquals(expected, actual);
	}

	
	@Test
	public void bad_TestOFgetNumberOfElementsWrongStringFormat() throws IOException {
		int expected = -1;
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		String str = "Num$$Elements=0";
		int actual = validator.getNumberOfElements(str);
		assertEquals(expected, actual);
	}
		
	@Test
	public void goodTestIntegersListValidation() throws IOException {
		ArrayList<Integer> expectedList = new ArrayList<>();
		expectedList.add(1);
		expectedList.add(-1);
		expectedList.add(0);
		expectedList.add(0);
		expectedList.add(0);
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		String secondLine = "1 -1 0 0 0";
		validator.setNumOfPieces(2);
		ArrayList<Integer> actualList = validator.integersListValidation(secondLine);
		assertEquals(expectedList, actualList);

	}

	
	@Test
	public void badTestIntegersListValidation() throws IOException {
		ArrayList<Integer> expectedList = new ArrayList<>();
		expectedList.clear();
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		validator.setNumOfPieces(1);
		String str = "1 -1 3 0 0";
		ArrayList<Integer> actualList = validator.integersListValidation(str);
		assertEquals(expectedList, actualList);

	}
	
	@Test
	public void badTestIntegersListValidationMoreThen5Elements() throws IOException {
		ArrayList<Integer> expectedList = new ArrayList<>();
		expectedList.clear();
		
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		validator.setNumOfPieces(2);
		String str = "1 -1 0 0 0 0";
		ArrayList<Integer> actualList = validator.integersListValidation(str);
		assertEquals(expectedList, actualList);
	}
	
	@Test
	public void badTestIntegersListValidationInvaldChar() throws IOException {
		ArrayList<Integer> expectedList = new ArrayList<>();
		expectedList.clear();
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		validator.setNumOfPieces(2);
		String str = "1 -1 1 0 b ";
		ArrayList<Integer> actualList = validator.integersListValidation(str);
		assertEquals(expectedList, actualList);
	}
	
	@Test
	public void badTestIntegersListValidationInvaldChar_ID() throws IOException {
		ArrayList<Integer> expectedList = new ArrayList<>();
		expectedList.clear();
		
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		String str = "b -1 3 0 0 ";
		ArrayList<Integer> actualList = validator.integersListValidation(str);
		assertEquals(expectedList, actualList);
	}
	
	@Test
	public void goodPuzzlePieceBuilder() {
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		ArrayList<PuzzlePiece> expected = new ArrayList<>();
		expected.add(new PuzzlePiece(1, -1, 0, 0, 0));
		ArrayList<Integer> listOfIntegers = new ArrayList<>();
		listOfIntegers.add(1);
		listOfIntegers.add(-1);
		listOfIntegers.add(0);
		listOfIntegers.add(0);
		listOfIntegers.add(0);
		ArrayList<PuzzlePiece> actual = validator.PuzzlePieceBuilder(listOfIntegers);
		assertEquals(expected.get(0).getId(), actual.get(0).getId());
	}
	
	@Test
	public void goodBasicFileValidator() throws IOException {
		PuzzleInputDataValidation validator = new PuzzleInputDataValidation();
		ArrayList<String> inputList = new ArrayList<>();
		inputList.add("1");
		inputList.add("2");
		boolean actual = validator.basicFileValidator(inputList);
		assertTrue(actual);
	}
	

}
