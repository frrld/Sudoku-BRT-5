package sudoku2;

import javax.swing.*;
import java.awt.*;

//TODO Translate

public class PrintScreen {

    public PrintScreen(int key, JLabel currentlabel, int currenti, int currentj, int[][] riddle) {

        /*
        Will return when a label is black. Because of that fixed numbers can't be overwritten.
        Otherwise, the label's text and the riddle-array (current position) will be set to the provided key
         */

    if (currentlabel.getForeground() == Color.BLACK) {
            return;
        }

        if (key == 0) {
            return;
        }

        currentlabel.setText(String.valueOf(key));
        riddle[currenti][currentj] = key;

        if (key == -1) {
            currentlabel.setText("");
            riddle[currenti][currentj] = 0;
        }

        /*
        Clearing the colors and checking stuff
         */
        CheckNumber.clearColor();
        CheckNumber.check(riddle, currenti, currentj, key);
        FinalCheck.check();

    }

}
