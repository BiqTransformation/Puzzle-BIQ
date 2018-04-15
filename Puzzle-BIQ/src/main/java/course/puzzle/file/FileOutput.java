package course.puzzle.file;

import java.util.ArrayList;
import java.util.List;
import course.puzzle.puzzle.PuzzlePiece;

import java.io.*;

public class FileOutput {
	
    public static String path = "src/main/resources/files/output.txt";
    public static boolean append_to_file = true;
	private static List<String> errors = new ArrayList<>();

    public static void cleanOutputFile() {
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();
        } catch (IOException e) {

        }
    }

    public static void printToOutputFile(String message) {
        PrintWriter print = null;
        try {
            FileWriter write = new FileWriter(path, append_to_file);
            print = new PrintWriter(write);
            print.println(message);
        } catch (IOException e) {

        } finally {
            print.close();
        }
    }


    public static String loadFromTextFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(path)) {
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        return sb.toString();
    }

    public static void printSolution(PuzzlePiece[][] solution) {

        PrintWriter print = null;
        try {
            FileWriter write = new FileWriter(path, append_to_file);
            print = new PrintWriter(write);
            for (int i = 0; i < solution.length; i++) {
                for (int j = 0; j < solution[i].length; j++) {
                    int id = solution[i][j].getId();
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
	
	public static  void loadErrors(List<String> errosCollected){
		for(String str : errosCollected){
			errors.add(str);
		}		
	}
	
	public static void printListToOutputFile(List<String> errors) {
		PrintWriter print=null;
		try{
			FileWriter write = new FileWriter(path,append_to_file);
			print = new  PrintWriter(write);
			for(String str: errors)
			print.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            print.close();
        }
    }

  
}


