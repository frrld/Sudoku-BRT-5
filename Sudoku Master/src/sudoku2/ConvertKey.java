package sudoku2;

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
