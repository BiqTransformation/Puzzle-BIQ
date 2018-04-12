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
	
	public static void printListToOutputFile() {
		PrintWriter print=null;
		try{
			FileWriter write = new FileWriter(path,append_to_file);
			print = new  PrintWriter(write);
			for(String str: errors)
			print.println(str);
        }
    }

    public static boolean fileCompare(String file1, String file2) {
        boolean areFilesIdentical = true;
        File f1 = new File(file1);
        File f2 = new File(file2);

        try {
            FileInputStream fis1 = new FileInputStream(f1);
            FileInputStream fis2 = new FileInputStream(f2);
            int i1 = fis1.read();
            int i2 = fis2.read();
            while (i1 != -1) {
                if (i1 != i2) {
                    areFilesIdentical = false;
                    break;
                }
                i1 = fis1.read();
                i2 = fis2.read();
            }
            fis1.close();
            fis2.close();
            System.out.println(areFilesIdentical);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file1 + " and " + file2 + " are identical: " + areFilesIdentical);
        return areFilesIdentical;
    }


}


