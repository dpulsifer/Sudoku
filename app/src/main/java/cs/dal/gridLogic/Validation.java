package cs.dal.gridLogic;

/**
 * Created by duncanpulsifer on 2017-02-11.
 *
 * Determines if number selection is valid. Checks row, column, and 3x3 square for
 * for presence of number selection. Returns true if number not found (selection valid), false if
 * found (not valid).
 */

public class Validation {

    public static boolean confirmNumber(String[] sudokuGrid, String numberSelection, int position) {

        String[][] puzzle = toGrid(sudokuGrid);

        int xCoord = position / 9;
        int yCoord = position % 9;

        if (checkRow(puzzle, xCoord, numberSelection) &&
                checkColumn(puzzle, yCoord, numberSelection) &&
                checkBox(puzzle, xCoord, yCoord, numberSelection)) {
            return true;
        }

        return false;

    }

    private static String[][] toGrid(String[] solvedSudokuGrid) {

        String[][] puzzle = new String[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = solvedSudokuGrid[(i*9) + j];
            }
        }

        return puzzle;

    }

    private static boolean checkRow(String[][] puzzle, int xCoord, String numberSelection) {

        for (int i = 0; i < 9; i++) {
            if (puzzle[xCoord][i].equals(numberSelection)) { return false; }
        }

        return true;

    }

    private static boolean checkColumn(String[][] sudokuGrid, int yCoord, String numberSelection) {

        for (int i = 0; i < 9; i++) {
            if (sudokuGrid[i][yCoord].equals(numberSelection)) {
                return false; }
        }

        return true;

    }

    private static boolean checkBox(String[][] sudokuGrid, int xCoord, int yCoord, String numberSelection) {

        int startingXCoord = (xCoord / 3) * 3;
        int startingYCoord = (yCoord / 3) * 3;

        for (int i = startingXCoord; i < startingXCoord + 3; i++) {
            for (int j = startingYCoord; j < startingYCoord + 3; j++) {
                if (sudokuGrid[i][j].equals(numberSelection)) {
                    return false; }
            }
        }

        return true;

    }

    public static boolean confirmWin(String[] solvedGrid) {

        for (int i = 0; i < 81; i++) {
            if (solvedGrid[i].equals(" ")) { return false; }
        }

        return true;
    }
}
