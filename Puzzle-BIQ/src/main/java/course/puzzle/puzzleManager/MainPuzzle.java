package course.puzzle.puzzleManager;

import java.util.ArrayList;
import java.util.Arrays;
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
    private static int numOfThreads = 0;
    private static String inputFile;
    private static String outputFile;


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            printUsage();
            return;
        } else if (!validateInputFile(args)) {
            System.out.println("Mandatory input file parameter is missing");
            printUsage();
        } else if (!validateOutputFile(args)) {
            System.out.println("Mandatory output file parameter is missing");
            printUsage();
        } else {
            inputFile = getInputFile(args);
            outputFile = getOutputFile(args);

        boolean isRotate = isRotate(args);
        if (!isRotate) {
            System.out.println("Rotate parameter is not defined, the default value will be used: " + false);
        }
        if (!isThreadParam(args)) {
            System.out.println("Number of threads is not defined, the default value will be used: " + DEFAULTNUMOFTHREADS);
            numOfThreads = DEFAULTNUMOFTHREADS;
        } else {
            getNumOfThreads(args);
        }

            PuzzleManager pm = new PuzzleManager(inputFile, outputFile, isRotate, numOfThreads);
            pm.handlePuzzle();
            FileOutput fo = new FileOutput(outputFile);
            String message = fo.loadFromTextFile();
            System.out.println(message);
        }

}

    private static void printUsage() {

        System.out.println("Usage:  -input <input file> -output <output file> -rotate -threads <num of threads>");
    }

    private static boolean validateInputFile(String[] args) {

        if (!Arrays.asList(args).contains(INPUT)) {
            return false;
        } else {
            int indexOfInputParam = Arrays.asList(args).indexOf(INPUT);
            if (args[indexOfInputParam + 1].equalsIgnoreCase(OUTPUT) || args[indexOfInputParam + 1].equalsIgnoreCase(ROTATE) || args[indexOfInputParam + 1].equalsIgnoreCase(NUMOFTHREADS)) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateOutputFile(String[] args) {
        if (!Arrays.asList(args).contains(OUTPUT)) {
            return false;
        } else {
            int indexOfOutputParam = Arrays.asList(args).indexOf(OUTPUT);
            if (args[indexOfOutputParam + 1].equalsIgnoreCase(INPUT) || args[indexOfOutputParam + 1].equalsIgnoreCase(ROTATE) || args[indexOfOutputParam + 1].equalsIgnoreCase(NUMOFTHREADS)) {
                return false;
            }
        }
        return true;

    }

    private static boolean isThreadParam(String[] args) {
        if (!Arrays.asList(args).contains(NUMOFTHREADS)) {
            return false;
        }
        return true;
    }

    private static void getNumOfThreads(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(NUMOFTHREADS)) {
                try {
                    numOfThreads = Integer.parseInt(args[i + 1]);
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    private static boolean isRotate(String[] args) {
        for (String str : args) {
            if (str.equals(ROTATE)) {
                return true;
            }
        }
        return false;
    }

    private static String getOutputFile(String[] args) {
        String toPath = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(OUTPUT)) {
                toPath = args[i + 1];
            }
        }
        return toPath;
    }


    private static String getInputFile(String[] args) {
        String fromPath = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(INPUT)) {
                fromPath = args[i + 1];
            }
        }
        return fromPath;
    }
}
