package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Lior (getPossibleSolutions) and Svetlana (findSolution)
 * Get all possible options of puzzle matrix
 * Try to solve each option with different thread
 */

public class PuzzleSolver {

  private static final int THREAD_TIMEOUT = 60;
    private List<PuzzlePiece> puzzlePieces;
    private PuzzlePiece[][] solvedPuzzle;
    private Map<Integer, Integer> solutions = new LinkedHashMap<>();
    private Puzzle puzzleInstance;
    private int numOfThreads;
    private long timeout;
    private AtomicBoolean solved = new AtomicBoolean(false);


    public PuzzleSolver(Puzzle puzzleInstance, int numOfThreads) {
        this.numOfThreads = numOfThreads;
        this.puzzleInstance = puzzleInstance;
        puzzlePieces = puzzleInstance.getPuzzle();
    }
    public PuzzleSolver(Puzzle puzzleInstance, int numOfThreads, long timeoutMilliseconds) {
        this.numOfThreads = numOfThreads;
        this.puzzleInstance = puzzleInstance;
        puzzlePieces = puzzleInstance.getPuzzle();
        timeout = timeoutMilliseconds;
    }

    public Map<Integer, Integer> getPossibleSolutions() {

        int puzzleSize = puzzlePieces.size();

        if (puzzleSize == 1) {
            solutions.put(1, 1);
        } else if (puzzleSize > 1) {
            if (PuzzleValidation.isPossibleOneRow(puzzlePieces, puzzleInstance.getRotate())) {
                solutions.put(1, puzzleSize);
            }
            if (PuzzleValidation.isPossibleOneColumn(puzzlePieces, puzzleInstance.getRotate())) {
                solutions.put(puzzleSize, 1);
            }
            for (int i = 2; i < puzzleSize; i++) {
                if (puzzleSize % i == 0) {
                    int num = puzzleSize / i;
                   if(num <= i){
                        if (PuzzleValidation.validateNumberOfStraightEdges(puzzlePieces, i, num, puzzleInstance.getRotate())) {
                         solutions.put(i, num);
                        }
                    }


                }
            }
        }
        return solutions;
    }

    public PuzzlePiece[][] findSolution() {
        getPossibleSolutions();

        threadManager();

        return solvedPuzzle;
    }


    private void threadManager() {
        solvedPuzzle = null;
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
        CompletionService<PuzzlePiece[][]> service = new ExecutorCompletionService<>(executor);

        List<Callable<PuzzlePiece[][]>> callables = new ArrayList<>();

        for (Map.Entry<Integer, Integer> s : solutions.entrySet()) {

            int rows = s.getKey();
            int cols = s.getValue();
            System.out.println(rows + "x" + cols);

            Callable<PuzzlePiece[][]> solution = new RunSolution(puzzleInstance, rows, cols, timeout);
            callables.add(solution);
        }

        for (Callable<PuzzlePiece[][]> solution : callables) {
            if(!solved.get()){
                service.submit(solution);
            }

       }

        try {
            solvedPuzzle = service.take().get();

            if(solvedPuzzle != null){
                solved.set(true);
                System.out.println("Solved!");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();
        try {
            if (executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("task completed");
            } else {
                System.out.println("Forcing shutdown...");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished all threads");
    }

}

