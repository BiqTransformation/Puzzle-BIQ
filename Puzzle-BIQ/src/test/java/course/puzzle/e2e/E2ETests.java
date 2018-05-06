package course.puzzle.e2e;

import course.puzzle.file.FileReader;
import course.puzzle.puzzle.Generator;
import course.puzzle.puzzleManager.PuzzleManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class E2ETests {


    private String filesPath = "c:\\temp\\puzzles\\";

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"6x6"},{"7x7"},{"8x8"},{"9x9"},{"10x10"},{"11x11"},{"12x12"},{"10x15"}
//                {"puzzle25Pieces"},{"puzzle50Pieces"},{"puzzle64Pieces"},{"puzzle100Pieces"},{"puzzle120Pieces"},{"puzzle121Pieces"},{"puzzle140Pieces"},{"puzzle150Pieces"}
//               {"good4PiecesR"},{"good5APiecesR"},{"good9Pieces"},{"good9PiecesR"},{"good12Pieces"},{"good12PiecesA"},{"good12PiecesB"},{"good16Pieces"},{"good20Pieces"},{"good24Pieces"}
        });
    }

    @Parameterized.Parameter // first data value (0) is default
    public String fInput;

    @Test
    public void testE2E() throws Exception {
        int rows = Integer.parseInt(fInput.split("x")[0]);
        int cols = Integer.parseInt(fInput.split("x")[0]);
        String filename = filesPath + "puzzle" +  rows * cols + "Pieces";

        String in = filename + ".in";
        String out = filename + ".out";

        new Generator(rows,cols,filesPath);

        System.out.println("Puzzle from file: " + in);
        boolean rotate = true;
        int numOfTreads = 10;
        PuzzleManager pm = new PuzzleManager(in, out, rotate, numOfTreads);
        pm.handlePuzzle();
        boolean solved = pm.validateSolutionViaOutputFile(in, out);
        System.out.println(FileReader.readFromFile(out));

        assertTrue(solved);
    }


}
