package course.puzzle.model;

import course.puzzle.puzzle.LogMessages;

public class NoSolution{
    private boolean solutionExists;


    public NoSolution(Boolean solutionExists) {
        this.solutionExists = solutionExists;

    }
    @Override
    public String toString() {
        return LogMessages.CANNOT_SOLVE_PUZZLE;
    }
}
