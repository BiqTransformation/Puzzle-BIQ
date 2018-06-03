package course.puzzle.puzzle;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import course.puzzle.model.NoSolution;
import course.puzzle.model.PuzzleErrors;
import course.puzzle.model.PuzzleSolution;
import course.puzzle.model.ServerOutput;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ServerOutputTest {
    Gson gson = new GsonBuilder()
//            .disableHtmlEscaping()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
//            .setPrettyPrinting()
            .serializeNulls()
            .create();
    @Test
    public void testServerOutputNoSolution() {

        Boolean solved = false;


        ServerOutput solution = new ServerOutput(new NoSolution(solved));
        String json = gson.toJson(solution);
        String expected = "{\"PuzzleSolution\":{\"SolutionExists\":false}}";
        assertEquals(expected, json);
        assertEquals(LogMessages.CANNOT_SOLVE_PUZZLE, solution.toString());

    }

    @Test
    public void testServerOutputErrors() {

        List<String> errors = new ArrayList<>();
        errors.add("error1");
        errors.add("error2");
        ServerOutput error = new ServerOutput(new PuzzleErrors(errors));

        String json = gson.toJson(error);
        String expected = "{\"PuzzleSolution\":{\"Errors\":[\"error1\",\"error2\"]}}";
        assertEquals(expected, json);
        assertEquals("error1\n" +
                "error2", error.toString());
    }

    @Test
    public void testServerOutputSolution() {
        PuzzlePiece p1 = new PuzzlePiece(1, 0, 0, 1, 0);
        PuzzlePiece p2 = new PuzzlePiece(2, -1, 0, 0, 0);
        PuzzlePiece p3 = new PuzzlePiece(3, -1, 0, 1, 0);
        PuzzlePiece p4 = new PuzzlePiece(4, -1, 0, 1, 0);

        PuzzlePiece[][] board = new PuzzlePiece[][]{{p1,p3.firstRotate(p3)},{p4.secondRotate(p4),p2}};

        ServerOutput solution = new ServerOutput(new PuzzleSolution(board));

        String json = gson.toJson(solution);
        System.out.println(json);
        System.out.println(solution.toString());
        String expected = "{\"PuzzleSolution\":{\"Solution\":{\"Rows\":2,\"Cols\":2,\"SolutionPieces\":[[{\"Id\":1,\"Shape\":{\"Edges\":[0,0,1,0]},\"RotateAngle\":0},{\"Id\":3,\"Shape\":{\"Edges\":[0,-1,0,1]},\"RotateAngle\":90}],[{\"Id\":4,\"Shape\":{\"Edges\":[1,0,-1,0]},\"RotateAngle\":180},{\"Id\":2,\"Shape\":{\"Edges\":[-1,0,0,0]},\"RotateAngle\":0}]]}}}";
        assertEquals(expected, json);
        assertEquals("1 3[90]\n" +
                "4[180] 2", solution.toString());
    }

}
