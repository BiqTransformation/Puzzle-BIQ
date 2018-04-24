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
    private boolean rotate;
    private int numOfThreads;

    public PuzzleManager(String fromPath, String toPath,boolean rotate,int numOfThreads) {
        this.rotate=rotate;
        this.numOfThreads=numOfThreads;
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
        puzzleList = getPuzzlePiecesFromInputFile(fromPath);

        if (puzzleList != null) {
            solvePuzzle();

        } else {

            //send the error list to output class
            fo.printToOutputFile("There are not any pieces in this puzzle!");
        }

    }

    private void solvePuzzle() throws IOException {
        newPuzzle = new Puzzle(puzzleList,rotate);
        validatePuzzleBeforeSolution = newPuzzle.getErrors();
        if (validatePuzzleBeforeSolution.size() > 0) {
            fo.printListToOutputFile(validatePuzzleBeforeSolution);
            return;
        }
        else{
            solvePuzzle = new PuzzleSolver(newPuzzle,numOfThreads);
            PuzzlePiece[][] puz = solvePuzzle.findSolution();
            if (solvePuzzle.validateSolution()) {
                fo.printSolution(puz);

            } else {
                fo.printToOutputFile(PuzzleErrors.CANNOT_SOLVE_PUZZLE);

            }
        }
    }

    private List<PuzzlePiece> getPuzzlePiecesFromInputFile(String inputFile) throws Exception {
        List<PuzzlePiece> puzzlePieces;
        FileReader reader = new FileReader();
        List<String> inputList;
        inputList = reader.readFromFile(inputFile);
        PuzzleInPutDataValidation fav = new PuzzleInPutDataValidation();
        puzzlePieces = fav.fileDataValidator(inputList);
        validatePuzzleInputFile = fav.getErrorList();
        if(validatePuzzleInputFile.size() > 0){
            fo.printListToOutputFile(validatePuzzleInputFile);
            return null;
        }
        return puzzlePieces;
    }

    public boolean validateSolutionViaOutputFile(String inputFile, String outputFile) throws Exception {

        List<PuzzlePiece> puzzlePieces = getPuzzlePiecesFromInputFile(inputFile);
//        Verify that number of pieces in solved matrix is equal to number of pieces in the original puzzle
        List<String> output = FileReader.readFromFile(outputFile);

        int rows = output.size();
        int cols = output.get(0).split("\\s+").length;
        if (rows * cols != puzzlePieces.size()) {
            return false;
        }

        Puzzle toValidate = new Puzzle(puzzlePieces,rotate);
        PuzzlePiece[][] actualSolution = new PuzzlePiece[rows][cols];
        int i = 0;
        for (String line : output) {
            String[] row = line.split("\\s+");
            for (int j = 0; j < row.length; j++) {
                int id = Integer.parseInt(row[j]);
                PuzzlePiece current = toValidate.getPieceById(id);
                if (current != null) {
                    actualSolution[i][j] = current;
                } else {
                    new FileOutput(outputFile).printToOutputFile("Piece with id " + id + " does not exist in the puzzle!");
                    return false;
                }
            }
            i++;
        }

        return new PuzzleSolver(toValidate,numOfThreads).checkSum(actualSolution);
    }


}
