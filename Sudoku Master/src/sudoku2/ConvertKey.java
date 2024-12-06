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
public class ConvertKey {

    /*
    This function converts the KeyChars provided by the user into the corresponding number.
    A backspace will be returned with -1.
    Invalid inputs will be returned with 0 and will be ignored.
     */


    public static int convert(int keychar){

        return switch (keychar){
            case 8 -> -1;
            case 49 -> 1;
            case 50 -> 2;
            case 51 -> 3;
            case 52 -> 4;
            case 53 -> 5;
            case 54 -> 6;
            case 55 -> 7;
            case 56 -> 8;
            case 57 -> 9;
            default -> 0;
        };
    }
}
