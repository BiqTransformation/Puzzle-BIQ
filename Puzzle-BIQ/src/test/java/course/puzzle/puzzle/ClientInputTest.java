package course.puzzle.puzzle;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import course.puzzle.model.ClientInput;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClientInputTest {
    Gson gson = new GsonBuilder()
//            .disableHtmlEscaping()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
//            .setPrettyPrinting()
            .serializeNulls()
            .create();
    @Test
    public void testClientInputPuzzleToJson() {

        List<PuzzlePiece> pieces = new ArrayList<>();

        pieces.add(new PuzzlePiece(1, 0, 0, 1, 0));
        pieces.add(new PuzzlePiece(2, -1, 0, 0, 0));
        pieces.add(new PuzzlePiece(3, -1, 0, 1, 0));
        pieces.add(new PuzzlePiece(4, -1, 0, 1, 0));

        ClientInput input = new ClientInput(new Puzzle(false,pieces));
        String json = gson.toJson(input);
        System.out.println(json);
        String expected = "{\"Puzzle\":{\"Rotate\":false," +
                "\"PuzzlePieces\":[{\"Id\":1,\"Shape\":{\"Edges\":[0,0,1,0]},\"RotateAngle\":0},{\"Id\":2,\"Shape\":{\"Edges\":[-1,0,0,0]},\"RotateAngle\":0},{\"Id\":3,\"Shape\":{\"Edges\":[-1,0,1,0]},\"RotateAngle\":0},{\"Id\":4,\"Shape\":{\"Edges\":[-1,0,1,0]},\"RotateAngle\":0}]}}";
        assertEquals(expected, json);
    }

    }
