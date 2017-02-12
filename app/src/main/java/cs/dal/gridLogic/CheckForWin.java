package cs.dal.gridLogic;

/**
 * Created by duncanpulsifer on 2017-02-12.
 */

public class CheckForWin {

    public static boolean confirmWin(String[] solvedGrid, String[] userGrid) {

        for (int i = 0; i < 81; i++) {
            if (solvedGrid[i] != userGrid[i]) { return false; }
        }

        return true;
    }
}
