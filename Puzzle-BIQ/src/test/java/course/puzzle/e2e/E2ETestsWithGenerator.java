package course.puzzle.e2e;

import course.puzzle.file.FileReader;
import course.puzzle.puzzle.PuzzleGenerator;
import course.puzzle.puzzleManager.PuzzleManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * @author Svetlana
 */

@RunWith(Parameterized.class)
public class E2ETestsWithGenerator {
    private static String tempDir;

    @BeforeClass
    public static void initTest() {
        try {
            tempDir = Files.createTempDirectory("puzzles").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"7x6"},{"2x20"}, {"5x8"}, {"7x7"}, {"6x9"}, {"8x7"}, {"8x8"}, {"9x9"}, {"10x10"}, {"11x11"}, {"14x10"}, {"10x15"}
        });
    }

    @Parameterized.Parameter
    public String fInput;

    @Test
    public void testE2EWithRotation() throws Exception {
        int rows = Integer.parseInt(fInput.split("x")[0]);
        int cols = Integer.parseInt(fInput.split("x")[1]);
        PuzzleGenerator pg = new PuzzleGenerator(rows, cols, tempDir);

        String in = pg.PUZZLE_NAME;
        String out = pg.PUZZLE_NAME.replace(".in",".out");

        boolean rotate = true;
        int numOfTreads = 3;
        System.out.println("Puzzle from file: " + pg.PUZZLE_NAME + "; rotate: " + rotate + "; number of threads: " + numOfTreads);

        PuzzleManager pm = new PuzzleManager(in, out, rotate, numOfTreads);
        pm.handlePuzzle();
        boolean solved = pm.validateSolutionViaOutputFile(in, out);
        System.out.println(FileReader.readFromFile(out));

        assertTrue(solved);
    }

    @Test
    public void testE2EWithoutRotation() throws Exception {
        int rows = Integer.parseInt(fInput.split("x")[0]);
        int cols = Integer.parseInt(fInput.split("x")[1]);
        PuzzleGenerator pg = new PuzzleGenerator(rows, cols, tempDir);

        String in = pg.PUZZLE_NAME;
        String out = pg.PUZZLE_NAME.replace(".in",".out");

        boolean rotate = false;
        int numOfTreads = 3;
        System.out.println("Puzzle from file: " + pg.PUZZLE_NAME + "; rotate: " + rotate + "; number of threads: " + numOfTreads);

        PuzzleManager pm = new PuzzleManager(in, out, rotate, numOfTreads);
        pm.handlePuzzle();
        boolean solved = pm.validateSolutionViaOutputFile(in, out);
        System.out.println(FileReader.readFromFile(out));

        assertTrue(solved);
    }

}
