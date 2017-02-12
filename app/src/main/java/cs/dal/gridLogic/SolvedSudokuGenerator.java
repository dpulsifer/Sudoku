package cs.dal.gridLogic;

/**
 * Created by duncanpulsifer on 2017-02-11.
 *
 * Generates a solved sudoku grid based on the manipulation of a hard-coded
 * solved sudoku grid. generatePuzzle() performs between 100 and 10,000
 * manipulations to the grid and returns a solved permutation.
 *
 * Manipulations are as follows:
 *
 * 1. flipHorizontal: flips the grid along the axis of the 5th row.
 * 2. flipVertical: flips the grid along the axis of the 5th column.
 * 3. flipMinorDiagonal: flips the grid along the minor diagonal.
 * 4. flipMajorDiagonal: flips the grid along the major diagonal.
 * 5. exchangeDigits: exchanges 2 random digits within the grid.
 * 6. interchangeRows: interchanges rows within 3 row sections of the
 *    grid, or interchanges the 3 sections.
 * 7. interchangeColumns: interchanges columns within 3 column
 *    sections of the grid, or interchanges the 3 sections.
**/

import java.util.Random;

public class SolvedSudokuGenerator {

    private static Random random = new Random();

    private static int [][] sudokuGrid = new int[][] {
            {4,3,5,2,6,9,7,8,1},
            {6,8,2,5,7,1,4,9,3},
            {1,9,7,8,3,4,5,6,2},
            {8,2,6,1,9,5,3,4,7},
            {3,7,4,6,8,2,9,1,5},
            {9,5,1,7,4,3,6,2,8},
            {5,1,9,3,2,6,8,7,4},
            {2,4,8,9,5,7,1,3,6},
            {7,6,3,4,1,8,2,5,9}
    };

    public static String[] generatePuzzle() {

        int[][] puzzle = sudokuGrid;
        int numberPermutations = random.nextInt(10000) + 100;


        for (int i = 0; i < numberPermutations; i++) {
            int selectRandomMethod = random.nextInt(7) + 1;

            switch(selectRandomMethod) {
                case 1: puzzle = flipHorizontal(puzzle);
                    break;
                case 2: puzzle = flipVertical(puzzle);
                    break;
                case 3: puzzle = flipMinorDiagonal(puzzle);
                    break;
                case 4: puzzle = flipMajorDiagonal(puzzle);
                    break;
                case 5: puzzle = exchangeDigits(puzzle);
                    break;
                case 6: puzzle = interchangeRows(puzzle);
                    break;
                case 7: puzzle = interchangeColumns(puzzle);
                    break;
            }
        }

        String[] puzzleIntToString = new String[81];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzleIntToString[(i*9) + j] = Integer.toString(puzzle[i][j]);
            }
        }

        return puzzleIntToString;

    }


    private static int[][] flipHorizontal(int[][] sudokuGrid) {

        int[][] flippedHorizontalGrid = new int[9][9];

        int gridRow = 0;

        for (int i = 8; i >= 0; i--) {
            flippedHorizontalGrid[gridRow] = sudokuGrid[i];
            gridRow++;
        }

        return flippedHorizontalGrid;
    }

    private static int[][] flipVertical(int[][] sudokuGrid) {

        int[][] flippedVerticalGrid = new int[9][9];

        int gridColumn = 0;

        for (int i = 8; i >= 0; i--) {
            for (int j = 0; j < 9; j++) {
                flippedVerticalGrid[j][gridColumn] = sudokuGrid[j][i];
            }
            gridColumn++;
        }

        return flippedVerticalGrid;
    }

    private static int[][] flipMinorDiagonal(int[][] sudokuGrid) {

        int[][] flippedMinorDiagonalGrid = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 8; j >= 0; j--) {
                flippedMinorDiagonalGrid[i][j] = sudokuGrid[j][i];
            }
        }

        return flippedMinorDiagonalGrid;
    }

    private static int[][] flipMajorDiagonal(int[][] sudokuGrid) {

        int[][] flipMajorDiagonalGrid = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                flipMajorDiagonalGrid[j][i] = sudokuGrid[i][j];
            }
        }

        return flipMajorDiagonalGrid;
    }

    private static int[][] exchangeDigits(int[][] sudokuGrid) {

        int num1 = random.nextInt(9) + 1;
        int num2 = random.nextInt(9) + 1;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuGrid[i][j] == num1) {
                    sudokuGrid[i][j] = num2;
                    continue;
                }
                if (sudokuGrid[i][j] == num2) {
                    sudokuGrid[i][j] = num1;
                }
            }
        }

        return sudokuGrid;

    }

    private static int[][] interchangeRows(int[][] sudokuGrid) {

        int[][] interchangedGrid;

        int rowSection = random.nextInt(3);
        int switchDeterminer = random.nextInt(6) + 1;

        switch (switchDeterminer) {
            case 1: interchangedGrid = interchangeIndividualRowHelper(sudokuGrid, rowSection, 0, 1);
                break;
            case 2: interchangedGrid = interchangeIndividualRowHelper(sudokuGrid, rowSection, 0, 2);
                break;
            case 3: interchangedGrid = interchangeIndividualRowHelper(sudokuGrid, rowSection, 1, 2);
                break;
            case 4: interchangedGrid = interchangeRowBlockHelper(sudokuGrid, 0, 1);
                break;
            case 5: interchangedGrid = interchangeRowBlockHelper(sudokuGrid, 0, 2);
                break;
            case 6: interchangedGrid = interchangeRowBlockHelper(sudokuGrid, 1, 2);
                break;
            default: interchangedGrid = new int[9][9];
                break;
        }

        return interchangedGrid;
    }

    private static int[][] interchangeIndividualRowHelper(int[][] sudokuGrid, int rowSection, int rowOne, int rowTwo) {

        int tempNumber;
        int firstRow = (rowSection * 3) + rowOne;
        int secondRow = (rowSection * 3) + rowTwo;

        for (int i = 0; i< 9; i++) {
            tempNumber = sudokuGrid[firstRow][i];
            sudokuGrid[firstRow][i] = sudokuGrid[secondRow][i];
            sudokuGrid[secondRow][i] = tempNumber;
        }

        return sudokuGrid;

    }

    private static int[][] interchangeRowBlockHelper(int[][] sudokuGrid, int blockOne, int blockTwo) {

        int tempNumber;
        int firstBlock = blockOne * 3;
        int secondBlock = blockTwo * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                tempNumber = sudokuGrid[firstBlock + i][j];
                sudokuGrid[firstBlock + i][j] = sudokuGrid[secondBlock + i][j];
                sudokuGrid[secondBlock + i][j] = tempNumber;
            }
        }

        return sudokuGrid;

    }

    private static int[][] interchangeColumns(int[][] sudokuGrid) {

        int[][] interchangedGrid;

        int columnSection = random.nextInt(3);
        int switchDeterminer = random.nextInt(6) + 1;

        switch (switchDeterminer) {
            case 1: interchangedGrid = interchangeIndividualColumnHelper(sudokuGrid, columnSection, 0, 1);
                break;
            case 2: interchangedGrid = interchangeIndividualColumnHelper(sudokuGrid, columnSection, 0, 2);
                break;
            case 3: interchangedGrid = interchangeIndividualColumnHelper(sudokuGrid, columnSection, 1, 2);
                break;
            case 4: interchangedGrid = interchangeColumnBlockHelper(sudokuGrid, 0, 1);
                break;
            case 5: interchangedGrid = interchangeColumnBlockHelper(sudokuGrid, 0, 2);
                break;
            case 6: interchangedGrid = interchangeColumnBlockHelper(sudokuGrid, 1, 2);
                break;
            default: interchangedGrid = new int[9][9];
                break;
        }

        return interchangedGrid;
    }

    private static int[][] interchangeIndividualColumnHelper(int[][] sudokuGrid, int columnSection, int columnOne, int columnTwo) {

        int tempNumber;
        int firstColumn = (columnSection * 3) + columnOne;
        int secondColumn = (columnSection * 3) + columnTwo;

        for (int i = 0; i< 9; i++) {
            tempNumber = sudokuGrid[i][firstColumn];
            sudokuGrid[i][firstColumn] = sudokuGrid[i][secondColumn];
            sudokuGrid[i][secondColumn] = tempNumber;
        }

        return sudokuGrid;

    }

    private static int[][] interchangeColumnBlockHelper(int[][] sudokuGrid, int blockOne, int blockTwo) {

        int tempNumber;
        int firstBlock = blockOne * 3;
        int secondBlock = blockTwo * 3;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                tempNumber = sudokuGrid[i][firstBlock + j];
                sudokuGrid[i][firstBlock + j] = sudokuGrid[i][secondBlock + j];
                sudokuGrid[i][secondBlock + j] = tempNumber;
            }
        }

        return sudokuGrid;

    }

}