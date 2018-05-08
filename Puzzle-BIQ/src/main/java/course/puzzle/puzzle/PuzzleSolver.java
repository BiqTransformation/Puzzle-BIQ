package course.puzzle.puzzle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

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
        if(puzzleInstance.getRotate()){
            puzzlePieces = puzzleInstance.rotateAll(puzzlePieces);
        }
        if (puzzleSize == 1) {
            solutions.put(1, 1);
        } else if (puzzleSize > 1) {
            if (PuzzleValidation.isPossibleOneRow(puzzlePieces)) {
                solutions.put(1, puzzleSize);
            }
            if (PuzzleValidation.isPossibleOneColumn(puzzlePieces)) {
                solutions.put(puzzleSize, 1);
            }
            for (int i = 2; i < puzzleSize; i++) {
                if (puzzleSize % i == 0) {
                    int num = puzzleSize / i;
                    if (PuzzleValidation.validateNumberOfStraightEdges(puzzlePieces, i, num)) {
                    solutions.put(i, num);
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
            service.submit(solution);
       }

        for (int i = 1; i <= solutions.size(); i++) {

                  try {
                     try {
                         solvedPuzzle = service.take().get();
                     }catch (NullPointerException e){

                     }

                     if(solvedPuzzle != null){
                         System.out.println("Solved! " + puzzleInstance.getSolved());
                        break;
                     }
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 } catch (ExecutionException e) {
                     e.printStackTrace();
                 }

         }

        executor.shutdownNow();
        System.out.println("Finished all threads");
    }

}

