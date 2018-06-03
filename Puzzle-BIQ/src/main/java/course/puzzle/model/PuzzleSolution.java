package course.puzzle.model;

import course.puzzle.puzzle.PuzzlePiece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PuzzleSolution {
    private SolutionPieces solution;


    public PuzzleSolution(PuzzlePiece[][] solutionPieces) {

        this.solution = new SolutionPieces(solutionPieces);
    }

    @Override
    public String toString() {
        return solution.toString();
    }

    public class SolutionPieces {

        private int rows;
        private int cols;
        private PuzzlePiece[][] solutionPieces;

        public SolutionPieces(PuzzlePiece[][] solutionPieces) {
            rows = solutionPieces.length;
            cols = solutionPieces[0].length;
            this.solutionPieces = solutionPieces;

        }

        @Override
        public String toString() {
            List<String> toPrint = new ArrayList<>();
            for (PuzzlePiece[] row : solutionPieces) {

                toPrint.add(Arrays.stream(row)
                        .map(p -> String.join(" ", "" + p.getId() + (p.getRotateAngle() != 0 ? "[" + p.getRotateAngle() + "]" : "")))
                        .collect(Collectors.joining(" ")));

            }

            return String.join("\n", toPrint);

        }

    }


}
