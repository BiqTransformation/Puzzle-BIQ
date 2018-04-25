package course.puzzle.e2e;

import course.puzzle.file.FileReader;
import course.puzzle.puzzleManager.PuzzleManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class E2ETests {


    private String filesPath = "src//test//resources//files//";

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"good12Pieces"}
        });
    }

    //, {"good4Pieces"},
//                {"good9Pieces"}, {"good12Pieces"},
//                {"good12PiecesA"}, {"good20Pieces"},
//                {"good12PiecesB"}, {"good16Pieces"},
//                {"good24Pieces"}
    @Parameterized.Parameter // first data value (0) is default
    public String fInput;

    @Test
    public void testE2E() throws Exception {
        String in = filesPath + fInput + ".in";
        String out = filesPath + fInput + ".out";

        System.out.println("Puzzle from file: " + in);
        boolean rotate = false;
        int numOfTreads = 2;
        PuzzleManager pm = new PuzzleManager(in, out, rotate, numOfTreads);
        pm.handlePuzzle();
        boolean solved = pm.validateSolutionViaOutputFile(in, out);
        System.out.println(FileReader.readFromFile(out));

        assertTrue(solved);
    }


}
