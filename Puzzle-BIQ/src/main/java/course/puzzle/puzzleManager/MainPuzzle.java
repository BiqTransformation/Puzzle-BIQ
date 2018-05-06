package course.puzzle.puzzleManager;

import java.util.ArrayList;
import java.util.List;

import course.puzzle.file.FileOutput;
import course.puzzle.puzzle.PuzzlePiece;
/*
 * @author Alex
 */
public class MainPuzzle {
	private final static String INPUT = "-input";
	private final static String OUTPUT = "-output";
	private final static String ROTATE = "-rotate";
	private final static String THREADS = "-threads";

	public static void main(String[] args) throws Exception {
		 String fromPath = getInputFile(args);
		 String toPath = getOutputFile(args);
		 boolean isRotate= isRotate(args);
		 //System.out.println(isRotate);
		 int numOfThreads = getNumOfThreads(args);
		 //System.out.println(numOfThreads);
		
		
		
		PuzzleManager pm = new PuzzleManager(fromPath,toPath,isRotate,numOfThreads);
		pm.handlePuzzle();
		FileOutput fo = new FileOutput(toPath);
		String message = fo.loadFromTextFile();	
		System.out.println(message);
		
	

	}

	private static int getNumOfThreads(String[] args) {
		int numOfThreads = 0;
		for(String str :args){
			if(str.startsWith(THREADS)){
				try{
					 numOfThreads = Integer.parseInt(str.substring(9));
					}
				catch(NumberFormatException e){}
				return numOfThreads;
			}
		}
		return 4;
	}

	private static boolean isRotate(String[] args) {
		for(String str : args){
			if(str.equals(ROTATE)){
				return true;
			}
			
		}
		return false;
	}

	private static String getOutputFile(String[] args) {
		String toPath = "";
		for(String str : args){
			if(str.startsWith(OUTPUT)){
				toPath =str.substring(8,str.length());
				//System.out.println(toPath);
			}
		}
		return toPath;
	}
		
	

	private static String getInputFile(String[] args) {	
		String fromPath = "";
		for(String str : args){
			if(str.startsWith(INPUT)){
				fromPath =str.substring(7,str.length());
				//System.out.println(fromPath);
			}
		}
		return fromPath;
	}
}
