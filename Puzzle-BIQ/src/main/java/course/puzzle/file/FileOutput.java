package course.puzzle.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileOutput {
	private static String path = "output.txt";
	private static  boolean append_to_file = true;
	
	
	
	public static void saveAsTextFile(String message) throws IOException {
		PrintWriter print=null;
		try{
			FileWriter write = new FileWriter(path,append_to_file);
			print = new  PrintWriter(write);
			print.println(message);
		}
		catch(IOException e){	
			  			
		}
		finally{
			print.close();
		}
	}
	
	
	public static String loadFromTextFile() throws IOException {
		 StringBuilder sb = new StringBuilder();
		try (FileInputStream fis = new FileInputStream(path)) {
			 InputStreamReader reader = new InputStreamReader(fis);
		     BufferedReader br = new BufferedReader(reader);
			String line;
			while((line=br.readLine())!=null){
				sb.append(line +"\n");
			}
		}
		return sb.toString();		
	}
	
}


