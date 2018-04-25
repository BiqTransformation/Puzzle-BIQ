package course.puzzle.puzzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class PuzzleSolver {

    //members
    private List<PuzzlePiece> puzzle;
    private PuzzlePiece[][] solvedPuzzle;
    private Map<Integer, Integer> solutions = new LinkedHashMap<>();
    private Puzzle puzzleInstance;
    private AtomicBoolean solved;





    public PuzzleSolver(Puzzle puzzleInstance) {
        this.puzzleInstance = puzzleInstance;
        puzzle = puzzleInstance.getPuzzle();
    }

    public Map<Integer, Integer> getPossibleSolutions() {

        int puzzleSize = puzzle.size();
        if (puzzleSize == 1) {
            solutions.put(1, 1);
        } else if (puzzleSize > 1) {
            if (PuzzleValidation.isPossibleOneRow(puzzle)) {
                solutions.put(1, puzzleSize);
            }
            if (PuzzleValidation.isPossibleOneColumn(puzzle)) {
                solutions.put(puzzleSize, 1);
            }
            for (int i = 2; i < puzzleSize; i++) {
                if (puzzleSize % i == 0) {
                    int num = puzzleSize / i;
                    if (PuzzleValidation.validateNumberOfStraightEdges(puzzle, i, num)) {
                        solutions.put(i, num);
                    }

                }
            }
        }
        return solutions;
    }

        public PuzzlePiece[][] findSolution() {
        getPossibleSolutions();

        for (Map.Entry<Integer, Integer> s : solutions.entrySet()) {
            int rows = s.getKey();
            int cols = s.getValue();
            RunSolution run = new RunSolution(rows, cols);

            solved.set(run.puzzleSolution());
            solvedPuzzle = run.getSolvedPuzzle();

        }
            solvedPuzzle = null;
            return solvedPuzzle;
        }

    }

