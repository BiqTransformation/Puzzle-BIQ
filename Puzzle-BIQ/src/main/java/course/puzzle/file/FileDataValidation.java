package course.puzzle.file;

import java.util.ArrayList;

public class FileDataValidation {

	private ArrayList<String> list = new ArrayList<>();
	
	public FileDataValidation(ArrayList<String> list) {
	  this.list=list;	
	}

	public ArrayList<String> fileDataValidator() {
		
		if (list.size() == 24) {
			for (String currentString : list) {	
				currentString.replace(" ", "");
				    currentString.toCharArray();
				       for(int i = 0 ;  i <  currentString.toCharArray().length ; i++ ) {
				    	   
				       }
				
				
				
				
				
				//int foo = Integer.parseInt(myString);
			}	
		}
		else {
			
		}
		return list;
		
	}
	
}
