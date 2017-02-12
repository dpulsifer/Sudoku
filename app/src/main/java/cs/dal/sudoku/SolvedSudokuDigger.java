package cs.dal.sudoku;

/**
 * Created by duncanpulsifer on 2017-02-11.
 */

import java.util.ArrayList;
import java.util.Random;

public class SolvedSudokuDigger {

    private static ArrayList<Integer> gridCoordinatesRemoved;
    private static ArrayList<Integer> gridCoordinatesChecked;

    private static int[] totalElementsForDiffLevel = new int[]{45, 35, 30, 25};
    private static int[] lowerBoundForDiffLevel = new int[]{4,3,2,0};

    private static Random random;

    public static String[] digPuzzle(String[] sudokuGrid, int difficultyLevel) {

        random = new Random();

        gridCoordinatesRemoved = new ArrayList<Integer>(81);
        gridCoordinatesChecked = new ArrayList<Integer>(81);

        for (int j = 0; j < 81; j++) {
            gridCoordinatesRemoved.add(j, j);
            gridCoordinatesChecked.add(j, j);
        }

        int[][] puzzle = stringToIntGrid(sudokuGrid);
        int gridElement;
        int xCoord;
        int yCoord;

        while (gridCoordinatesRemoved.size() >= totalElementsForDiffLevel[difficultyLevel]) {

            gridElement = random.nextInt(gridCoordinatesChecked.size());
            xCoord = gridCoordinatesChecked.get(gridElement) % 9;
            yCoord = gridCoordinatesChecked.get(gridElement) / 9;

            if (checkRow(puzzle, xCoord, lowerBoundForDiffLevel[difficultyLevel]) &&
                    checkColumn(puzzle, yCoord, lowerBoundForDiffLevel[difficultyLevel]) &&
                    checkBox(puzzle, xCoord, yCoord, lowerBoundForDiffLevel[difficultyLevel])) {

                puzzle[xCoord][yCoord] = -1;

                gridCoordinatesRemoved.remove(gridElement);
            }

            gridCoordinatesChecked.remove(gridElement);


        }

        String[] puzzleIntToString = new String[81];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzle[i][j] == -1) {puzzleIntToString[(i*9) + j] = " ";}
                else {puzzleIntToString[(i*9) + j] = Integer.toString(puzzle[i][j]);}
            }
        }

        return puzzleIntToString;


    }

    private static boolean checkRow(int[][] sudokuGrid, int xCoord, int lowerBoundForDiffLevel) {

        int count = -1;

        for (int i = 0; i < 9; i++) {
            if (sudokuGrid[xCoord][i] != -1) { count++; }
        }

        return (count >= lowerBoundForDiffLevel);

    }

    private static boolean checkColumn(int[][] sudokuGrid, int yCoord, int lowerBoundForDiffLevel) {

        int count = -1;

        for (int i = 0; i < 9; i++) {
            if (sudokuGrid[i][yCoord] != -1) { count++; }
        }

        return (count >= lowerBoundForDiffLevel);

    }

    private static boolean checkBox(int[][] sudokuGrid, int xCoord, int yCoord, int lowerBoundForDiffLevel) {

        int startingXCoord = (xCoord / 3) * 3;
        int startingYCoord = (yCoord / 3) * 3;

        int count = -1;

        for (int i = startingXCoord; i < startingXCoord + 3; i++) {
            for (int j = startingYCoord; j < startingYCoord + 3; j++) {
                if (sudokuGrid[i][j] != -1) { count++; }
            }
        }

        return (count >= lowerBoundForDiffLevel);

    }

    private static int[][] stringToIntGrid(String[] solvedSudokuGrid) {

        int[][] puzzle = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = Integer.parseInt(solvedSudokuGrid[(i*9) + j]);
            }
        }

        return puzzle;

    }

}
