package course.puzzle.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileOutput {
	
	
	
	
	
	
	
	public static void saveAsTextFile(FileOutputStream fos,String message) throws IOException {
		try (OutputStreamWriter writer = new OutputStreamWriter(fos)) {
			writer.write(message + '\n');			
		}
	}
	
	
	public static String loadFromTextFile(FileInputStream fis) throws IOException {
		 StringBuilder sb = new StringBuilder();
		try (InputStreamReader reader = new InputStreamReader(fis);
		     BufferedReader br = new BufferedReader(reader)) {
			String line;
			while((line=br.readLine())!=null){
				sb.append(line);
			}
		}
		return sb.toString();		
	}
	
}


