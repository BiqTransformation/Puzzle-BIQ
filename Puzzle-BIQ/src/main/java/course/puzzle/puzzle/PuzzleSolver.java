package course.puzzle.puzzle;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class PuzzleSolver {

    //members
    private List<PuzzlePiece> puzzle;
    private PuzzlePiece[][] solvedPuzzle;
    private Map<Integer, Integer> solutions = new LinkedHashMap<>();
    private Puzzle puzzleInstance;
    private AtomicBoolean solved = new AtomicBoolean();
    private int numOfThreads;


    public PuzzleSolver(Puzzle puzzleInstance, int numOfThreads) {
        this.numOfThreads = numOfThreads;
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
            ExecutorService executor = Executors.newFixedThreadPool(2);
            Callable<PuzzlePiece[][]> callable = new Callable<PuzzlePiece[][]>() {
                @Override
                public PuzzlePiece[][] call() {
                    RunSolution worker = new RunSolution(puzzle,rows, cols);
                    worker.puzzleSolution();
                    solvedPuzzle = worker.getSolvedPuzzle();
                    return solvedPuzzle;
                }
            };
            Future<PuzzlePiece[][]> future = executor.submit(callable);
             executor.shutdown();

            while (!executor.isTerminated()) {
            }

            System.out.println("Finished all threads");

          }

        return solvedPuzzle;
    }

    public boolean getSolved() {
        return solved.get();
    }
}

