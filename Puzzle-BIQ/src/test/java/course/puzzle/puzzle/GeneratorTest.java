package course.puzzle.puzzle;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class GeneratorTest {
    @Test
    public void generatePuzzleTest(){
        PuzzleGenerator puzzle = new PuzzleGenerator(12,12,"c:\\temp\\puzzles\\");
        System.out.println(puzzle);
        assertTrue(PuzzleValidation.checkSum(puzzle.generatePuzzle()));
    }
}
