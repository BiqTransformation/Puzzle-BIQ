package course.puzzle.file;

import course.puzzle.puzzle.PuzzlePiece;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
	
	public static List<String> fromFile;
	
	private static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	public static List<String> readFromFile(String fromPath) throws IOException  { 
		fromFile = new ArrayList<>();
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(new FileInputStream(fromPath),"UTF-8"));
				 String currentLine ;
					while((currentLine = in.readLine()) != null && !currentLine.equals("")) {
						fromFile.add(currentLine);	
					}
			} catch (FileNotFoundException e) {
			}
			finally{
				in.close();	
			}
		return fromFile;	
	}

}
