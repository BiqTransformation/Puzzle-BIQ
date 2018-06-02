package course.puzzle.model;

public class ServerOutput {
    private Object puzzleSolution;

    public ServerOutput(Object puzzleSolution) {
        this.puzzleSolution = puzzleSolution;
    }

    @Override
    public String toString() {
        return puzzleSolution.toString();
    }

}

