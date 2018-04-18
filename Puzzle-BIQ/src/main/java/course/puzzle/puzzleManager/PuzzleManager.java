package course.puzzle.puzzleManager;

import course.puzzle.file.PuzzleInPutDataValidation;
import course.puzzle.file.FileOutput;
import course.puzzle.file.FileReader;
import course.puzzle.puzzle.PuzzleErrors;
import course.puzzle.puzzle.Puzzle;
import course.puzzle.puzzle.PuzzlePiece;
import course.puzzle.puzzle.PuzzleSolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzleManager {

    private String fromPath;
    private String toPath;
    private List<PuzzlePiece> puzzleList;
    private PuzzleSolver solvePuzzle;
    private Puzzle newPuzzle;
    private List<String> validatePuzzleInputFile = new ArrayList<>();
    private List<String> validatePuzzleBeforeSolution = new ArrayList<>();
    FileOutput fo;


    public PuzzleManager(String fromPath, String toPath) {
        if (fromPath != null) {
            this.fromPath = fromPath;
        }
        if (toPath != null) {
            this.toPath = toPath;
           fo= new FileOutput(toPath);
        }

    }

    public void handlePuzzle() throws Exception {
        
        fo.cleanOutputFile();
        FileReader reader = new FileReader();
        List<String> inputList = new ArrayList<>();
        inputList = reader.readFromFile(fromPath);
        PuzzleInPutDataValidation fav = new PuzzleInPutDataValidation();
        puzzleList = fav.fileDataValidator(inputList);
        validatePuzzleInputFile = fav.getErrorList();
        if(validatePuzzleInputFile.size() > 0){
            fo.printListToOutputFile(validatePuzzleInputFile);
            return;
        }
        if (puzzleList != null) {
            newPuzzle = new Puzzle(puzzleList);
            validatePuzzleBeforeSolution = newPuzzle.getErrors();
            if (validatePuzzleBeforeSolution.size() > 0) {
                fo.printListToOutputFile(validatePuzzleBeforeSolution);
                return;
            }
            else{
                solvePuzzle = new PuzzleSolver(newPuzzle);
                PuzzlePiece[][] puz = solvePuzzle.findSolution();
                if (solvePuzzle.validateSolution()) {
                    fo.printSolution(puz);
                } else {
                    fo.printToOutputFile(PuzzleErrors.CANNOT_SOLVE_PUZZLE);

                }
            }

        } else {

            //send the error list to output class
            fo.printToOutputFile("There are not any pieces in this puzzle!");
        }

    }

    public boolean validateSolutionViaOutputFile() throws IOException {

//        Verify that number of pieces in solved matrix is equal to number of pieces in the original puzzle
        String[] rows = fo.loadFromTextFile().split("\n");
        String[] cols = rows[0].split("\\s+");
        if (rows.length * cols.length != puzzleList.size()) {
            return false;
        }

        PuzzlePiece[][] actualSolution = new PuzzlePiece[rows.length][cols.length];
        for (int i = 0; i < rows.length; i++) {
            String[] row = rows[i].split("\\s+");
            for (int j = 0; j < row.length; j++) {
                int id = Integer.parseInt(row[j]);
                PuzzlePiece current = newPuzzle.getPieceById(id);
                if (current != null) {
                    actualSolution[i][j] = current;
                } else {
                    fo.printToOutputFile("Piece with id " + id + " does not exist in the puzzle!");
                    return false;
                }
            }
        }
        return solvePuzzle.verifySolution(actualSolution);
    }


}
