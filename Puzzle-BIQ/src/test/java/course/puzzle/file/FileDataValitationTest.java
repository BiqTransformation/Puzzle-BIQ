package course.puzzle.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import course.puzzle.puzzle.PuzzlePiece;

public class FileDataValitationTest {
	
	@Test
	public void goodValidatorIntegrationFlow() throws IOException  {
		String fromPath = "src/main/resources/files/input.txt";
		ArrayList rawData = FileReader.readFromFile(fromPath);		
		FileDataValidation validator = new FileDataValidation();
		ArrayList<PuzzlePiece> actualPiecesList = validator.fileDataValidator(rawData);

		ArrayList<PuzzlePiece> expectedPiecesList = new ArrayList<>();
		expectedPiecesList.add(new PuzzlePiece(1, -1, 0, 0, 0));
		expectedPiecesList.add(new PuzzlePiece(2, 0, 0, 0, 0));
		expectedPiecesList.add(new PuzzlePiece(3, 0, 0, 0, 0));
		
		assertEquals(expectedPiecesList,actualPiecesList);
	}

	@Ignore
	@Test
	public void goodTestOFfirstLineValidator() throws IOException {
		int expected = 2;
		FileDataValidation validator = new FileDataValidation();
		String str = "NumElements=2";
		int actual = validator.firstLineValidator(str);
		assertEquals(expected, actual);
	}
	@Ignore
	@Test
	public void goodTestOFfirstLineValidatorIncludedSpaces() throws IOException {
		int expected = 2;
		FileDataValidation validator = new FileDataValidation();
		String str = "NumElem ents=    2";
		int actual = validator.firstLineValidator(str);
		assertEquals(expected, actual);
	}
	@Ignore
	@Test
	public void goodTestOFfirstLineValidatorIncludedSpaces1() throws IOException {
		int expected = 2;
		FileDataValidation validator = new FileDataValidation();
		String str = "NumElem ents=2";
		int actual = validator.firstLineValidator(str);
		assertEquals(expected, actual);
		
	}
	@Ignore
	@Test
	public void badTestOFfirstLineValidatorInvalidChar_$() throws IOException {
		int expected = -1;
		FileDataValidation validator = new FileDataValidation();
		String str = "NumElem ents=$";
		int actual = validator.firstLineValidator(str);
		assertEquals(expected, actual);
		
	}
	@Ignore
	@Test
	public void badTestOFfirstLineValidatorIncorrectNumOfPieces_0() throws IOException {
		int expected = -1;
		FileDataValidation validator = new FileDataValidation();
		String str = "NumElements=0";
		int actual = validator.firstLineValidator(str);
		assertEquals(expected, actual);
	}

	@Ignore
	@Test
	public void badTestOFfirstLineValidatorIncorrectNumOfPieces_1() throws IOException {
		int expected = -1;
		FileDataValidation validator = new FileDataValidation();
		String str = "NumElements=1";
		int actual = validator.firstLineValidator(str);
		assertEquals(expected, actual);
	}

	@Ignore
	@Test
	public void badTestOFfirstLineValidatorWrongStringFormat() throws IOException {
		int expected = -1;
		FileDataValidation validator = new FileDataValidation();
		String str = "Num$$Elements=0";
		int actual = validator.firstLineValidator(str);
		assertEquals(expected, actual);
	}
	@Ignore
	@Test
	public void goodTestIntegersListValidation() throws IOException {
		ArrayList<Integer> expectedList = new ArrayList<>();
		expectedList.add(1);
		expectedList.add(-1);
		expectedList.add(0);
		expectedList.add(0);
		expectedList.add(0);
		FileDataValidation validator = new FileDataValidation();
		String secondLine = "1 -1 0 0 0";
		validator.setNumOfPieces(2);
		ArrayList<Integer> actualList = validator.integersListValidation(secondLine);
		assertEquals(expectedList, actualList);

	}

	
	@Test
	public void badTestIntegersListValidation() throws IOException {
		ArrayList<Integer> expectedList = new ArrayList<>();
		expectedList.add(1);
		expectedList.add(-1);
		expectedList.add(0);
		expectedList.add(0);
		
		FileDataValidation validator = new FileDataValidation();
		validator.setNumOfPieces(1);
		String str = "1 -1 3 0 0";
		ArrayList<Integer> actualList = validator.integersListValidation(str);
		assertEquals(expectedList, actualList);

	}
	@Ignore
	@Test
	public void badTestIntegersListValidationMoreThen5Elements() throws IOException {
		ArrayList<Integer> expectedList = new ArrayList<>();
		expectedList.clear();
		FileDataValidation validator = new FileDataValidation();
		String str = "1 -1 3 0 0 0";
		ArrayList<Integer> actualList = validator.integersListValidation(str);
		assertEquals(expectedList, actualList);
	}
	@Ignore
	@Test
	public void badTestIntegersListValidationInvaldChar() throws IOException {
		ArrayList<Integer> expectedList = new ArrayList<>();
		expectedList.clear();
		FileDataValidation validator = new FileDataValidation();
		String str = "1 -1 3 0 b ";
		ArrayList<Integer> actualList = validator.integersListValidation(str);
		assertEquals(expectedList, actualList);
	}
	@Ignore
	@Test
	public void badTestIntegersListValidationInvaldChar_ID() throws IOException {
		ArrayList<Integer> expectedList = new ArrayList<>();
		expectedList.clear();
		FileDataValidation validator = new FileDataValidation();
		String str = "b -1 3 0 0 ";
		ArrayList<Integer> actualList = validator.integersListValidation(str);
		assertEquals(expectedList, actualList);
	}
	
	@Ignore
	@Test
	public void goodTestIdNumberValidation() throws IOException {
		int expected = 1;
		FileDataValidation validator = new FileDataValidation();
		int idNum = 1;
		String str = "NumElements=2";
		validator.firstLineValidator(str);
		int actual = validator.idNumberValidation(idNum);
		assertEquals(expected, actual);
	}

	@Ignore
	@Test
	public void badTestIdNumberValidation_BadIdNumber_0() {
		int expected = -1;
		FileDataValidation validator = new FileDataValidation();
		int idNum = 0;
		int actual = validator.idNumberValidation(idNum);
		assertEquals(expected, actual);

	}
	@Ignore
	@Test
	public void badTestIdNumberValidation_BadIdNumber_NegativeNumber() {
		int expected = -1;
		FileDataValidation validator = new FileDataValidation();
		int idNum = -1;
		int actual = validator.idNumberValidation(idNum);
		assertEquals(expected, actual);

	}
	@Ignore
	@Test
	public void goodPuzzlePieceBuilder() {
		FileDataValidation validator = new FileDataValidation();
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
	@Ignore
	@Test
	public void badPuzzlePieceBuilder() {
		FileDataValidation validator = new FileDataValidation();
		ArrayList<PuzzlePiece> expected = new ArrayList<>();
		expected.add(new PuzzlePiece(2, -1, 0, 0, 0));
		ArrayList<Integer> listOfIntegers = new ArrayList<>();
		listOfIntegers.add(1);
		listOfIntegers.add(-1);
		listOfIntegers.add(0);
		listOfIntegers.add(0);
		listOfIntegers.add(0);
		ArrayList<PuzzlePiece> actual = validator.PuzzlePieceBuilder(listOfIntegers);
		assertNotEquals(expected.get(0).getId(), actual.get(0).getId());
	}
	@Ignore
	@Test 
	public void goodfileDataValidator() throws IOException {
			
		ArrayList<String> input = new ArrayList<>();
		input.add("NumElements=2");
		input.add("1 -1 0 0 0");
		input.add("2 0 0 0 0");
		
		PuzzlePiece piece1 = new PuzzlePiece(1, -1, 0, 0, 0);
		PuzzlePiece piece2 = new PuzzlePiece(2, 0, 0, 0, 0);
		ArrayList<PuzzlePiece> expected = new ArrayList<>();
		expected.add(piece1);
		expected.add(piece2);
		
		FileDataValidation validator = new FileDataValidation();
		ArrayList<PuzzlePiece> actual = validator.fileDataValidator(input);
		assertEquals(expected.get(0).getId(), actual.get(0).getId());
		
	}
	@Ignore
	@Test 
	public void badfileDataValidator1() throws IOException {
			
		ArrayList<String> input = new ArrayList<>();
		input.add("NumElements=2");
		input.add("1 -1 0 0 0");
		// the id more then total elements.
		input.add("3 0 0 0 0");
		
		ArrayList<PuzzlePiece> expected = new ArrayList<>();
		
		FileDataValidation validator = new FileDataValidation();
		ArrayList<PuzzlePiece> actual = validator.fileDataValidator(input);
		// Empty list was received .
		assertEquals(expected, actual);
		
	}
	@Ignore
	@Test 
	public void badfileDataValidator2() throws IOException {
			
		ArrayList<String> input = new ArrayList<>();
		// NumElements more then actual received.
		input.add("NumElements=3");
		input.add("1 -1 0 0 0");
		input.add("2 0 0 0 0");
		
		ArrayList<PuzzlePiece> expected = new ArrayList<>();
		
		FileDataValidation validator = new FileDataValidation();
		ArrayList<PuzzlePiece> actual = validator.fileDataValidator(input);
		// Empty list was received .
		assertEquals(expected, actual);
		
	}
}
