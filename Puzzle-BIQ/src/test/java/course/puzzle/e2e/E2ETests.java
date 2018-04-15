package course.puzzle.e2e;

import course.puzzle.file.FileOutput;
import course.puzzle.file.FileReader;
import course.puzzle.puzzleManager.PuzzleManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class E2ETests {


    private String filesPath = "src//test//resources//files//";

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"good3Pieces"}, {"good4Pieces"},
                {"good9Pieces"}, {"good12Pieces"},
                {"good12PiecesA"}, {"good20Pieces"},
                {"good12PiecesB"}, {"good16Pieces"},
                {"good24Pieces"}
        });
    }
//
    @Parameterized.Parameter // first data value (0) is default
    public String fInput;

    @Test
    public void testE2E() throws Exception {
        String in = filesPath + fInput + ".in";
        String out = filesPath + fInput + ".out";

        System.out.println("Puzzle from file: " + in);
        PuzzleManager pm = new PuzzleManager(in, out);
        pm.handlePuzzle();

        assertTrue(pm.validateSolutionViaOutputFile());
    }


//
//	@Test
//	public void testSimpleNegativeE2EWithLessIDsNotInSequance() throws Exception{
//		String fromPath = "src//test//resources//files//test4.in";
//		String toPath = "src//test//resources//files//test4.out";
//		//clean output
//		FileOutput.path=toPath;
//	    FileOutput.cleanOutputFile();
//		List<String> inputList =FileReader.readFromFile(fromPath);
//		FileDataValidation fav = new FileDataValidation();
//		List<PuzzlePiece> puzzleList= fav.fileDataValidator(inputList);
//		String message = FileOutput.loadFromTextFile();
//		System.out.println(message);
//		//assertTrue(message.contains("missing puzzle elements with the following IDs:3,4"));
//	}
//


}
