package course.puzzle.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;

import course.puzzle.puzzle.PuzzlePiece;

public class FileOutput {
	public static String path = "src/main/resources/files/output.txt";
	public static boolean append_to_file = true;
	
	public static void cleanOutputFile(){
		try{
			PrintWriter writer = new PrintWriter(path);
			writer.print("");
			writer.close();
		}
		catch(IOException e){
			
		}
	}
	
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
	
	public static void printSolution(PuzzlePiece[][] solution){
		
		PrintWriter print=null;
		try{
			FileWriter write = new FileWriter(path,append_to_file);
			print = new  PrintWriter(write);
			for(int i =0;i<solution.length;i++){
				for(int j=0;j<solution[i].length;j++){
					int id =solution[i][j].getId();
					print.print(id + " ");
				}
				print.print('\n');
			}
		}
		catch(IOException e){	
			  			
		}
		finally{
			print.close();
		}
	}
	
}


