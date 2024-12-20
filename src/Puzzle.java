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

public class Puzzle {
    public int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    private Random random = new Random();

    /**
     * Generate a new puzzle with a given difficulty level.
     * @param level Difficulty level (e.g., 1 for easy, 4 for hard)
     */
    public void newPuzzle(int level) {
        // Clear the grid first
        clearGrid();

        // Generate a valid Sudoku solution
        if (generateCompletePuzzle()) {
            // Remove numbers based on difficulty level
            int cellsToRemove = level * 10;
            randomizeEmptyCells(cellsToRemove);
        } else {
            System.out.println("Failed to generate puzzle. Trying again with empty grid.");
            newPuzzle(level); // Retry with fresh grid
        }
    }

    /**
     * Clear the entire grid
     */
    private void clearGrid() {
        for (int i = 0; i < SudokuConstants.GRID_SIZE; i++) {
            for (int j = 0; j < SudokuConstants.GRID_SIZE; j++) {
                numbers[i][j] = 0;
            }
        }
    }

    /**
     * Generate a complete valid Sudoku solution.
     * @return true if generation successful, false otherwise
     */
    private boolean generateCompletePuzzle() {
        return fillGrid(0, 0);
    }

    /**
     * Fill the grid using backtracking to ensure a valid Sudoku solution.
     */
    private boolean fillGrid(int row, int col) {
        // If we've filled all rows, we're done
        if (row == SudokuConstants.GRID_SIZE) {
            return true;
        }

        // Move to next row when we reach end of current row
        if (col == SudokuConstants.GRID_SIZE) {
            return fillGrid(row + 1, 0);
        }

        // Skip if cell is already filled
        if (numbers[row][col] != 0) {
            return fillGrid(row, col + 1);
        }

        // Try numbers 1-9 in random order
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
     * Generate array of numbers 1-9 in random order
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
     * Check if placing a number is valid according to Sudoku rules.
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
     * Randomly remove numbers to create empty cells based on difficulty level.
     * @param emptyCells Number of cells to empty.
     */
    private void randomizeEmptyCells(int emptyCells) {
        while (emptyCells > 0) {
            int row = random.nextInt(SudokuConstants.GRID_SIZE);
            int col = random.nextInt(SudokuConstants.GRID_SIZE);

            if (numbers[row][col] != 0) {
                numbers[row][col] = 0;
                emptyCells--;
            }
        }
    }
}