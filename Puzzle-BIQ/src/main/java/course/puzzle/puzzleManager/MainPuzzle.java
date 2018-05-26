package course.puzzle.puzzleManager;

import java.util.ArrayList;
import java.util.List;

import course.puzzle.file.FileOutput;


/*
 * @author Alex
 * The class that contains the main method 
 */
public class MainPuzzle {
	private final static String INPUT = "-input";
	private final static String OUTPUT = "-output";
	private final static String ROTATE = "-rotate";
	private final static String NUMOFTHREADS = "-threads";
	private final static int DEFAULTNUMOFTHREADS = 4;
	
   
	public static void main(String[] args) throws Exception {
		String fromPath="",toPath="";
		boolean inputFile = validateInputFile(args);
		boolean outputFile = validateOutputFile(args);
		//boolean rotate = validateRotate(args);
		//boolean threads = validateThreads(args);
		if(!inputFile){
			System.out.println("mandatory input file parameter is missing");
		}
		else{
			fromPath = getInputFile(args);			
		}
		if(!outputFile){
			System.out.println("mandatory output file parameter is missing");
		}
		else{
			 toPath = getOutputFile(args);
		}
		
		
		 		 
		 boolean isRotate= isRotate(args);	
		 System.out.println("rotate= " + isRotate);
		 int numOfThreads = getNumOfThreads(args);
		 System.out.println("numOfThreads = " + numOfThreads);
		 
		 if(inputFile && outputFile ){			 
		 PuzzleManager pm = new PuzzleManager(fromPath,toPath,isRotate,numOfThreads);
			pm.handlePuzzle();
			FileOutput fo = new FileOutput(toPath);
			String message = fo.loadFromTextFile();	
			System.out.println(message);
		 }
		 
		

	}
	
	
//	private static boolean validateThreads(String[] args) {
//		for (int i = 0; i < args.length; i++) {
//			if (args[i].equals(NUMOFTHREADS)) {
//				return true;
//			}
//		}
//		return false;
//	}


//	private static boolean validateRotate(String[] args) {
//		for(int i = 0 ;i <args.length;i++){
//			if(args[i].equals(ROTATE)){
//				return true;
//			}		
//		}
//		return false;
//	}


	private static boolean validateInputFile(String [] args) {
		for(int i =0; i < args.length;i++){
			if(args[i].equals(INPUT)){
				if(i+1 < args.length-1){
					if(args[i+1].equals(OUTPUT)){
						return false;
					}
				}
			}		
		}
			return true;
	}
	
	private static boolean validateOutputFile(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(OUTPUT)) {
				if (i < args.length - 1) {
					if (args[i + 1].equals(ROTATE) || args[i + 1].equals(NUMOFTHREADS)) {
						return false;
					}
				}
			}
		}
		return true;

	}

			

		
		
			
						
	

	private static int getNumOfThreads(String[] args) {
		int numOfThreads = 0;
		for(int i = 0 ; i<args.length;i++){
			if(args[i].equals(NUMOFTHREADS)){
				try{
					 numOfThreads = Integer.parseInt(args[i+1]);
					}
				catch(NumberFormatException e){}
				return numOfThreads;
			}
		}
		return DEFAULTNUMOFTHREADS;
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
		String toPath="";
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(OUTPUT)){					
				toPath = args[i + 1];			
				}
		}
		return toPath;
	}	
	



	private static String getInputFile(String[] args) {	
		String fromPath = "";
		for(int i =0 ;i<args.length;i++){
			if(args[i].equals(INPUT)){				
				fromPath =args[i+1];					
				}				
			}		
		return fromPath;
	}
}
