package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle {
    protected List<PuzzlePiece> puzzle;

    public Puzzle(List<PuzzlePiece> puzzle) {
        this.puzzle = puzzle;
    }

    public Map<Integer, Integer> getPossibleSolutions() {
        Map<Integer, Integer> solutions = new HashMap<Integer, Integer>();
        int puzzleSize = puzzle.size();
        for(int i = 1; i <= puzzleSize;i++ ){

        }

        return solutions;
    }


}
