import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;


/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #5
 * 1 - 5026231175 - Muhammad Farrel Danendra
 * 2 - 5026231150 - Muhammad Dzaki Adfiz
 * 3 - 5026231212 - Baqhiz Faruq S.
 *
 * The Puzzle class handles Sudoku puzzle generation and validation.
 */
public class Puzzle {
    /** Grid to store the current state of the puzzle */
    public int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    /** Random number generator for puzzle generation */
    private Random random = new Random();
    /** Stores the complete solution for verification */
    private int[][] solution;

    /**
     * Generate a new puzzle with a given difficulty level.
     * Creates a complete solution first, then removes numbers based on difficulty.
     * @param level Difficulty level determining how many numbers to remove
     */
    public void newPuzzle(int level) {
        clearGrid();
        if (generateCompletePuzzle()) {
            solution = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
            for (int i = 0; i < SudokuConstants.GRID_SIZE; i++) {
                for (int j = 0; j < SudokuConstants.GRID_SIZE; j++) {
                    solution[i][j] = numbers[i][j];
                }
            }
        }
    }

    /**
     * Clear the entire grid by setting all values to 0
     */
    private void clearGrid() {
        for (int i = 0; i < SudokuConstants.GRID_SIZE; i++) {
            for (int j = 0; j < SudokuConstants.GRID_SIZE; j++) {
                numbers[i][j] = 0;
            }
        }
    }

    /**
     * Generate a complete valid Sudoku solution
     * @return true if generation successful, false otherwise
     */
    private boolean generateCompletePuzzle() {
        return fillGrid(0, 0);
    }

    /**
     * Fill the grid using backtracking to ensure a valid Sudoku solution
     * @param row Current row being filled
     * @param col Current column being filled
     * @return true if the grid is successfully filled
     */
    private boolean fillGrid(int row, int col) {
        if (row == SudokuConstants.GRID_SIZE) {
            return true;
        }

        if (col == SudokuConstants.GRID_SIZE) {
            return fillGrid(row + 1, 0);
        }

        if (numbers[row][col] != 0) {
            return fillGrid(row, col + 1);
        }

        int[] numbersToTry = getRandomOrderedNumbers();

        for (int num : numbersToTry) {
            if (isValidPlacement(row, col, num)) {
                numbers[row][col] = num;
                if (fillGrid(row, col + 1)) {
                    return true;
                }
                numbers[row][col] = 0; // Backtrack
            }
        }

        return false;
    }

    /**
     * Generate array of numbers 1-9 in random order for puzzle generation
     * @return Array of integers 1-9 in random order
     */
    private int[] getRandomOrderedNumbers() {
        int[] nums = new int[9];
        for (int i = 0; i < 9; i++) {
            nums[i] = i + 1;
        }

        // Fisher-Yates shuffle
        for (int i = nums.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }

    /**
     * Check if placing a number is valid according to Sudoku rules
     * @param row Row position to check
     * @param col Column position to check
     * @param num Number to validate
     * @return true if the placement is valid
     */
    private boolean isValidPlacement(int row, int col, int num) {
        // Check row
        for (int j = 0; j < SudokuConstants.GRID_SIZE; j++) {
            if (numbers[row][j] == num) return false;
        }

        // Check column
        for (int i = 0; i < SudokuConstants.GRID_SIZE; i++) {
            if (numbers[i][col] == num) return false;
        }

        // Check 3x3 box
        int boxRow = row - (row % SudokuConstants.SUBGRID_SIZE);
        int boxCol = col - (col % SudokuConstants.SUBGRID_SIZE);

        for (int i = boxRow; i < boxRow + SudokuConstants.SUBGRID_SIZE; i++) {
            for (int j = boxCol; j < boxCol + SudokuConstants.SUBGRID_SIZE; j++) {
                if (numbers[i][j] == num) return false;
            }
        }

        return true;
    }

    /**
     * Remove numbers from the complete puzzle to create the game board
     * @param emptyCells Number of cells to empty
     */
    public void randomizeEmptyCells(int emptyCells) {
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = 0; i < SudokuConstants.GRID_SIZE * SudokuConstants.GRID_SIZE; i++) {
            positions.add(i);
        }
        Collections.shuffle(positions);

        for (int i = 0; i < emptyCells; i++) {
            int pos = positions.get(i);
            int row = pos / SudokuConstants.GRID_SIZE;
            int col = pos % SudokuConstants.GRID_SIZE;
            numbers[row][col] = 0;
        }
    }

    /**
     * Get the solution number for a specific cell
     * @param row Row of the cell
     * @param col Column of the cell
     * @return The correct number for this cell in the solution
     */
    public int getSolution(int row, int col) {
        return solution[row][col];
    }
}