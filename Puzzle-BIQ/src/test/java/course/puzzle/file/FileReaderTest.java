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

public class FileReaderTest {
	@Ignore
	@Test
	public void fileReaderVerification() throws IOException {
		PuzzlePiece piece1 = new PuzzlePiece(1, -1, 0, 0, 0);
		PuzzlePiece piece2 = new PuzzlePiece(2, 0, 0, 0, 0);
		ArrayList<PuzzlePiece> expected = new ArrayList<>();
		expected.add(piece1);
		expected.add(piece2);

		String fromPath = "src/main/resources/files/puzzle1";
		FileReader file = new FileReader();
		ArrayList actual = file.readFromFile(fromPath);

		FileDataValidation validator = new FileDataValidation();

		ArrayList<PuzzlePiece> pieces = validator.fileDataValidator(actual);

		assertTrue(expected.equals(pieces));
	}

	@Ignore
	@Test
	public void goodTestOFfirstLineValidator() {
		int expected = 2;
		FileDataValidation validator = new FileDataValidation();
		String str = "NumElements=2";
		int actual = validator.firstLineValidator(str);
		assertEquals(expected, actual);
	}

	@Ignore
	@Test
	public void badTestOFfirstLineValidatorWringNumOfPieces_0() {
		int expected = -1;
		FileDataValidation validator = new FileDataValidation();
		String str = "NumElements=0";
		int actual = validator.firstLineValidator(str);
		assertEquals(expected, actual);
	}

	@Ignore
	@Test
	public void badTestOFfirstLineValidatorWringNumOfPieces_1() {
		int expected = -1;
		FileDataValidation validator = new FileDataValidation();
		String str = "NumElements=1";
		int actual = validator.firstLineValidator(str);
		assertEquals(expected, actual);
	}

	@Ignore
	@Test
	public void badTestOFfirstLineValidatorWrongStringFormat() {
		int expected = -1;
		FileDataValidation validator = new FileDataValidation();
		String str = "Num$$Elements=0";
		int actual = validator.firstLineValidator(str);
		assertEquals(expected, actual);
	}

	@Ignore
	@Test
	public void goodTestIntegersListValidation() {
		ArrayList<Integer> expectedList = new ArrayList<>();
		expectedList.add(1);
		expectedList.add(-1);
		expectedList.add(0);
		expectedList.add(0);
		expectedList.add(0);
		FileDataValidation validator = new FileDataValidation();
		String secondLine = "1 -1 0 0 0";
		String firstLine = "NumElements=2";
		validator.firstLineValidator(firstLine);
		ArrayList<Integer> actualList = validator.integersListValidation(secondLine);
		assertEquals(expectedList, actualList);

	}

	@Ignore
	@Test
	public void badTestIntegersListValidation() {
		ArrayList<Integer> expectedList = new ArrayList<>();
		expectedList.clear();
		FileDataValidation validator = new FileDataValidation();
		String str = "1 -1 3 0 0";
		ArrayList<Integer> actualList = validator.integersListValidation(str);
		assertEquals(expectedList, actualList);

	}

	@Ignore
	@Test
	public void goodTestIdNumberValidation() {
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
	public void badTestIdNumberValidation_BadIdNumber_Negative() {
		int expected = -1;
		FileDataValidation validator = new FileDataValidation();
		int idNum = -1;
		int actual = validator.idNumberValidation(idNum);
		assertEquals(expected, actual);

	}

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
}
