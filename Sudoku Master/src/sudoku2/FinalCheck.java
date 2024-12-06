package sudoku2;
/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #5
 * 1 - 5026231175 - Muhammad Farrel Danendra
 * 2 - 5026231150 - Muhammad Dzaki Adfiz
 * 3 - 5026231212 - Baqhiz Faruq S.
 */
import javax.swing.*;

public class FinalCheck {

    public static void check(){
        int sum;

        /*
        If a field in the array equals zero the sudoku isn't solved completely.
        Therefore, we don't need to continue to check the checksum.
         */

        for (int i = 0; i< 9; i++){
            for (int j = 0; j<9; j++){
                if (Gui.riddle[i][j] == 0){
                    return;
                }
            }
        }

        /*
        Will calculate the checksum of every 3*3 field:
        Because every field should contain the numbers 1-9 the checksum should be 45.
        Technically you could cheat and "win" as long as every number is in a field, but come on:
        Conflicting entries are marked, no-one should be that dumb. Right? I mean - Right? :'D
        If the checksum isn't 45 the function will be aborted and a message dialog (that try again thingy) will be shown.
        Otherwise, the user will see a message dialog that he won the game.
         */

        for (int i = 0; i< 9; i++) {

            sum = 0;
            for (int j = 0; j < 9; j++) {
                sum = sum + Gui.riddle[i][j];
            }
            if (sum != 45) {
                JOptionPane.showMessageDialog(null, "Sorry, this solution is incorrect. Please try again.");
                return;
            }
        }

       JOptionPane.showMessageDialog(null,"Congratulations. You have solved the sudoku successfully.");


    }

}
