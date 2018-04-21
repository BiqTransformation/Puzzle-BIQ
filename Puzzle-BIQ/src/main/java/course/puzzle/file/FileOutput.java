package course.puzzle.file;

import java.util.ArrayList;
import java.util.List;
import course.puzzle.puzzle.PuzzlePiece;

import java.io.*;

public class FileOutput {
	
    private  String path; 
    private  boolean append_to_file = true;
	private  List<String> errors = new ArrayList<>();
	
	public FileOutput(String path){
		this.path=path;
	}


    public  void cleanOutputFile() {
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();
        } catch (IOException e) {

        }
    }

    public  void printToOutputFile(String message) {
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


    public  String loadFromTextFile() throws IOException {
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

    public  void printSolution(PuzzlePiece[][] solution) throws IOException {

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
	
	
	
	public  void printListToOutputFile(List<String> errors) {
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


