package course.puzzle.puzzle;

import course.puzzle.file.FileOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/*
 * This class build a puzzle input file randomly  by the size of the puzzle
 * (rows*cols) 
 * @author Svetlana
 */
public class Generator {
    private int rows;
    private int cols;
    private String dir;
    private PuzzlePiece[][] board;
    List<PuzzlePiece> list = new ArrayList<>();

    public Generator(int rows, int cols, String dir) {
        this.rows = rows;
        this.cols = cols;
        this.dir = dir;
        this.board = new PuzzlePiece[rows][cols];
        prepareList();
        board = generatePuzzle();
        printToFile();
    }

    private void prepareList() {

        List<Integer> ids = IntStream.rangeClosed(1, rows * cols)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(ids);
        for (int id : ids) {
            list.add(new PuzzlePiece(id, createPuzzleShape()));
        }

    }

    private int[] createPuzzleShape() {
        //populate the array with random values
        int[] edges = new int[4];
        for (int i = 0; i < 4; i++) {
            Random randomNo = new Random();
            int num = randomNo.nextInt(3);
            if (num == 2) {
                num = -1;
            }
            edges[i] = num;
        }
        return edges;
    }

    public PuzzlePiece[][] generatePuzzle() {
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                PuzzlePiece current = list.get(index);

                if (i == 0) {
                    current.setTopValue(0);

                }
                if (i == rows - 1) {
                    current.setBottomValue(0);
                }
                if (j == 0) {
                    current.setLeftValue(0);
                }
                if (j == cols - 1) {
                    current.setRightValue(0);
                }
                if (j >= 1) {
                    current.setLeftValue(0 - board[i][j - 1].getRightValue());
                }
                if (i >= 1) {
                    current.setTopValue(0 - board[i - 1][j].getBottomValue());
                }
                current.setShape();
                board[i][j] = current;
                index++;
            }

        }
        return board;
    }
    private void printToFile() {
        FileOutput fo = new FileOutput(dir + "puzzle" + rows * cols + "Pieces.in");
        fo.cleanOutputFile();
        fo.printToOutputFile(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NumElements = " + rows*cols + "\n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                PuzzlePiece p = board[i][j];
                sb.append(p.getId() + " " + p.getShape() +"\n");
            }
        }
        return sb.toString();
    }
}
