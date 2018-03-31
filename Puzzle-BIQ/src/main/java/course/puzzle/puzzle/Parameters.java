package course.puzzle.puzzle;

public class Parameters {
	private static int size;
	private static int id;
	private static int element;
	
    public static String CANNOT_SOLVE_PUZZLE = "Cannot solve puzzle:";
    public static String WRONG_NUMBER_OF_STRAIGHT_EDGES = "Wrong number of straight edges";
    public static String MISSING_CORNER_TL = "missing corner element: "+ element;
    public static String MISSING_CORNER_TR = "missing corner element: TR";
    public static String MISSING_CORNER_BL = "missing corner element: BL";
    public static String MISSING_CORNER_BR = "missing corner element: BR";
    public static String MISSING_PUZZLE_ELEMENTS = "missing puzzle elements with the following IDs:";
    public static String WRONG_ELEMENT_ID = "Puzzle of size "+size + "cannot have the following IDs:";
    public static String WRONG_ELEMENT_FORMAT = "Puzzle ID" + id +"has wrong data:";   
    public static String SUM_OF_EDGES_IS_NOT_ZERO = "Sum of edges is not zero";
    public static String NO_PROPER_SULOTION = "it seems that there is no proper solution";


}
