package course.puzzle.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
	public static ArrayList<String> readFromFile(String fromPath) throws IOException  { 
		ArrayList<String> list = new ArrayList<>();
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fromPath),"UTF-8"));
		     String currentLine ;
				while((currentLine = in.readLine()) !=null) {
					list.add(currentLine);	
				}
		return list;	
	}
}
