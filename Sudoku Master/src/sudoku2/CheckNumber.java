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
import java.awt.*;


public class CheckNumber {


    /*
    Because Color.BLUE sucks.
     */

    public static Color NOTBLUE = new Color(70, 78, 255, 255);

    

    /*
    clearColor resets the colors that might have been changed by the check-
    Every field will be checked using a for-loop.
    When a panel is blue it and its label will be reset:
    Cyan / blue -> dynamic numbers
    black / blue -> static numbers
     */

    public static void clearColor(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Gui.alllabels[i][j].getForeground() == Color.CYAN) {
                    Gui.alllabels[i][j].setForeground(Color.BLUE);
                    Gui.allpanels[i][j].setBackground(Color.WHITE);
                } else if(Gui.alllabels[i][j].getForeground() == Color.ORANGE) {
                    Gui.alllabels[i][j].setForeground(Color.BLACK);
                    Gui.allpanels[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    public static void check(int[][] riddle, int currenti, int currentj, int key) {

        /*
        Checks for conflict in the grid:
        If the input number (key) exists more than once the field will be marked using the paint function
         */

        int keys = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (riddle[currenti][j] == key) {
                    keys++;
                    if (keys > 1) {
                        paint(currenti, j, key, riddle);
                    }
                }

            }
        }

        /*
        Checks lines 0-3
         */

        if (currenti == 0 || currenti == 1 || currenti == 2) {

            if (currentj <= 2) {

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        paint(i, j, key, riddle);

                    }
                }

            } else if ( currentj <= 5) {

                for (int i = 0; i < 3; i++) {
                    for (int j = 3; j < 6; j++) {

                        paint(i, j, key, riddle);
                    }
                }

            } else {

                for (int i = 0; i < 3; i++) {
                    for (int j = 6; j < 9; j++) {

                        paint(i, j, key, riddle);

                    }
                }
            }


        }

        /*
        Checks lines (3-5)
         */

        else if (currenti == 3 || currenti == 4 || currenti == 5) {

            if (currentj <= 2) {

                for (int i = 3; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        paint(i, j, key, riddle);
                    }
                }

            }else if ( currentj <= 5) {

                for (int i = 3; i < 6; i++) {
                    for (int j = 3; j < 6; j++) {
                        paint(i, j, key, riddle);
                    }
                }

            } else {

                for (int i = 3; i < 6; i++) {
                    for (int j = 6; j < 9; j++) {
                        paint(i, j, key, riddle);
                    }
                }
            }

        }

        /*
        Checks lines (6-8)
        */

        else if (currenti == 6 || currenti == 7 || currenti == 8) {

            if (currentj <= 2) {

                for (int i = 6; i < 9; i++) {
                    for (int j = 0; j < 3; j++) {
                        paint(i, j, key, riddle);

                    }
                }

            } else if (currentj <= 5) {

                for (int i = 6; i < 9; i++) {
                    for (int j = 3; j < 6; j++) {
                        paint(i, j, key, riddle);

                    }
                }
            } else {

                for (int i = 6; i < 9; i++) {
                    for (int j = 6; j < 9; j++) {
                        paint(i, j, key, riddle);

                    }
                }
            }


        }


         /*
        Checks columns (0,3,6)
         */

        if (currenti == 0 || currenti == 3 || currenti == 6) {

            if (currentj == 0 || currentj == 3 || currentj == 6) {

                for (int i = 0; i <= 6; i = i+3) {
                    for (int j = 0; j <= 6; j = j+3) {
                        paint(i, j, key, riddle);

                    }
                }

            }

            if (currentj == 1 || currentj == 4 || currentj == 7) {

                for (int i = 0; i <= 6; i = i+3) {
                    for (int j = 1; j <= 7; j = j+3) {
                        paint(i, j, key, riddle);

                    }
                }

            }

            if (currentj == 2 || currentj == 5 || currentj == 8) {

                for (int i = 0; i <= 6; i = i+3) {
                    for (int j = 2; j <= 8; j = j+3) {
                        paint(i, j, key, riddle);

                    }
                }

            }
        }

         /*
        Checks columns (1,4,7)
          */
        if (currenti == 1 || currenti == 4 || currenti == 7) {

            if (currentj == 0 || currentj == 3 || currentj == 6) {

                for (int i = 1; i <= 7; i = i+3) {
                    for (int j = 0; j <= 6; j = j+3) {
                        paint(i, j, key, riddle);
                    }
                }

            }

            if (currentj == 1 || currentj == 4 || currentj == 7) {

                for (int i = 1; i <= 7; i = i+3) {
                    for (int j = 1; j <= 7; j = j+3) {
                        paint(i, j, key, riddle);

                    }
                }

            }

            if (currentj == 2 || currentj == 5 || currentj == 8) {

                for (int i = 1; i <= 7; i = i+3) {
                    for (int j = 2; j <= 8; j = j+3) {
                        paint(i, j, key, riddle);

                    }
                }

            }
        }

         /*
         Checks columns (2,5,8)
         */
        if (currenti == 2 || currenti == 5 || currenti == 8) {

            if (currentj == 0 || currentj == 3 || currentj == 6) {

                for (int i = 2; i <= 8; i = i+3) {
                    for (int j = 0; j <= 6; j = j+3) {
                        paint(i, j, key, riddle);

                    }
                }

            }

            if (currentj == 1 || currentj == 4 || currentj == 7) {

                for (int i = 2; i <= 8; i = i+3) {
                    for (int j = 1; j <= 7; j = j+3) {
                        paint(i, j, key, riddle);
                    }
                }

            }

            if (currentj == 2 || currentj == 5 || currentj == 8) {

                for (int i = 2; i <= 8; i = i+3) {
                    for (int j = 2; j <= 8; j = j+3) {
                        paint(i, j, key, riddle);
                    }
                }

            }
        }

    }





    /*
   Paints the received field cyan/blue (notblue) if the text is blue
   When the text is back, the field will be painted orange/blue (notblue)
   The current field will be painted cyan/blue
    */

    private static void paint(int i, int j, int key, int[][] riddle) {

        if (Gui.alllabels[i][j].getForeground() == Color.BLUE && riddle[i][j] == key && key != -1) {

            Gui.alllabels[i][j].setForeground(Color.CYAN);
            Gui.allpanels[i][j].setBackground(NOTBLUE);
            Gui.currentlabel.setForeground(Color.BLUE);
            Gui.currentpanel.setBackground(Color.CYAN);


        } else if (Gui.alllabels[i][j].getForeground() == Color.BLACK && riddle[i][j] == key) {

            Gui.alllabels[i][j].setForeground(Color.ORANGE);
            Gui.allpanels[i][j].setBackground(NOTBLUE);

        }

    }
}


