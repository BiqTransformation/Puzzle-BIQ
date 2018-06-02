package course.puzzle.model;

import java.util.List;
import java.util.stream.Collectors;

public class PuzzleErrors {
    private List<String> errors;

    public PuzzleErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return errors.stream().collect(Collectors.joining("\n"));
    }
}
