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
import java.util.Random;

public class Riddles {

   private static final int[][] riddle0 = {

            {4, 1, 0, 0, 0, 6, 2, 0, 7},
            {0, 6, 5, 0, 0, 7, 4, 9, 0},
            {0, 0, 7, 4, 8, 0, 0, 0, 6},
            {0, 6, 0, 3, 0, 1, 0, 9, 0},
            {0, 7, 0, 5, 0, 0, 0, 4, 2},
            {1, 0, 0, 0, 7, 2, 3, 0, 8},
            {1, 0, 8, 0, 2, 0, 6, 0, 0},
            {6, 0, 0, 0, 1, 8, 3, 0, 0},
            {0, 2, 9, 6, 4, 0, 0, 1, 0}

    };

    private static final int[][] riddle1 = {

            {0, 1, 0, 0, 0, 0, 6, 0, 5},
            {9, 0, 0, 0, 0, 8, 0, 0, 0},
            {8, 0, 0, 0, 0, 4, 7, 0, 0},
            {0, 9, 0, 0, 0, 0, 8, 0, 0},
            {0, 6, 0, 2, 0, 7, 0, 3, 0},
            {0, 0, 8, 0, 0, 0, 0, 6, 0},
            {0, 0, 2, 1, 0, 0, 0, 0, 6},
            {0, 0, 0, 4, 0, 0, 0, 0, 2},
            {5, 0, 3, 0, 0, 0, 0, 1, 0}

    };

    private static final int[][] riddle2 = {

            {0, 0, 0, 6, 4, 9, 2, 0, 0},
            {9, 0, 3, 0, 0, 0, 4, 0, 0},
            {0, 4, 0, 0, 5, 0, 6, 0, 7},
            {0, 0, 0, 0, 0, 0, 7, 6, 1},
            {3, 0, 2, 0, 0, 6, 0, 0, 0},
            {0, 0, 0, 9, 7, 8, 0, 0, 0},
            {0, 8, 4, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 5, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 3, 6, 5}

    };

    private static final int[][] riddle3 = {

            {5, 3, 0, 0, 0, 0, 9, 0, 0},
            {8, 0, 0, 9, 2, 0, 0, 0, 0},
            {2, 0, 0, 1, 0, 0, 6, 4, 0},
            {7, 0, 0, 0, 6, 4, 0, 0, 8},
            {2, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 0, 0, 9, 4},
            {0, 0, 0, 0, 1, 6, 0, 0, 0},
            {0, 6, 3, 0, 0, 9, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 8, 0, 7}

    };

    private static final int[][] riddle4 = {

            {8, 0, 0, 0, 0, 0, 5, 0, 0},
            {0, 5, 2, 0, 0, 8, 1, 0, 3},
            {0, 0, 7, 0, 0, 2, 0, 0, 9},
            {0, 0, 5, 0, 0, 2, 0, 7, 4},
            {3, 0, 0, 9, 0, 0, 0, 0, 0},
            {2, 6, 0, 0, 1, 0, 0, 0, 3},
            {0, 0, 0, 0, 0, 0, 0, 6, 7},
            {0, 1, 4, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 3, 8, 0, 0, 0, 0}

    };

    public static int[][] riddle() {

        /*
        Will define a random int from 0-4
        Will return  the riddle above corresponding to the random number
         */

        int random = new Random().nextInt(5);

        return switch (random) {
            case 0 -> riddle0;
            case 1 -> riddle1;
            case 2 -> riddle2;
            case 3 -> riddle3;
            default -> riddle4;
        };

    }


}
