package course.puzzle.model;

import course.puzzle.puzzle.Puzzle;

public class ClientInput {
    private Puzzle puzzle;

    public ClientInput(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }
}
