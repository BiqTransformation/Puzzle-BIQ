package course.puzzle.puzzleManager;
import java.util.ArrayList;
import java.util.List;

import course.puzzle.file.FileDataValidation;
import course.puzzle.file.FileOutput;
import course.puzzle.file.FileReader;
import course.puzzle.puzzle.PuzzlePiece;
import course.puzzle.puzzle.SolvePuzzle;

public class PuzzleManager {
	
	private String fromPath;
	private String toPath;
	
	
	public PuzzleManager ( String fromPath , String toPath ){
		if(fromPath!=null){
			this.fromPath = fromPath;
		}
		if(toPath!=null){
			this.toPath=toPath;
		}
		
	}
	
	public void handlePuzzle() throws Exception{
		FileOutput.path=toPath;
		FileOutput.cleanOutputFile();
		List<String> inputList = new ArrayList<>();
		inputList = FileReader.readFromFile(fromPath);
		FileDataValidation fav = new FileDataValidation();
		List<PuzzlePiece> puzzleList= fav.fileDataValidator(inputList);
		if(puzzleList.size()>0){
			SolvePuzzle sp = new SolvePuzzle(puzzleList);
			PuzzlePiece[][] puz =sp.findSolution();
			FileOutput.printSolution(puz);
		}
		else{
			//send validations error to output
			List<String> errors =fav.getErrorList();
			//send the error list to output class
			FileOutput.loadErrors(errors);
		}
		
		
	}

}
