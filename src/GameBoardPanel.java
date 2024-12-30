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
import java.awt.event.*;
import javax.swing.*;

/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #5
 * 1 - 5026231175 - Muhammad Farrel Danendra
 * 2 - 5026231150 - Muhammad Dzaki Adfiz
 * 3 - 5026231212 - Baqhiz Faruq S.
 *
 * GameBoardPanel manages the Sudoku game board UI and game logic
 */
public class GameBoardPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    /** Cell size in pixels */
    public static final int CELL_SIZE = 60;
    /** Total board width */
    public static final int BOARD_WIDTH = CELL_SIZE * SudokuConstants.GRID_SIZE;
    /** Total board height */
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;

    /** 2D array of Cell objects representing the game board */
    private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    /** Puzzle generator instance */
    private Puzzle puzzle;
    /** Stores complete solution for validation */
    private int[][] solution;

    /**
     * Constructor - Initializes the game board panel
     */
    public GameBoardPanel() {
        super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));
        puzzle = new Puzzle();
        solution = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

        // Create and add cells to the panel
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col] = new Cell(row, col);
                super.add(cells[row][col]);
            }
        }

        // Add input listener to all cells
        CellInputListener listener = new CellInputListener();
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].addActionListener(listener);
            }
        }

        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    /**
     * Reset the game board and generate a new puzzle
     * Stores solution and removes numbers to create playable board
     */
    public void resetBoard() {
        puzzle.newPuzzle(2);

        // Store the complete solution
        for (int i = 0; i < SudokuConstants.GRID_SIZE; i++) {
            for (int j = 0; j < SudokuConstants.GRID_SIZE; j++) {
                solution[i][j] = puzzle.numbers[i][j];
            }
        }

        // Remove numbers to create puzzle
        puzzle.randomizeEmptyCells(20);

        // Initialize the board cells
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                int value = puzzle.numbers[row][col];
                boolean isGiven = (value != 0);
                cells[row][col].newGame(isGiven ? value : 0, isGiven);
                cells[row][col].setNumber(solution[row][col]);
            }
        }
    }

    /**
     * Start a new game by resetting the board
     */
    public void newGame() {
        resetBoard();
    }

    /**
     * Check if the puzzle is completely solved
     * @return true if all cells are correctly filled
     */
    public boolean isSolved() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].getStatus() == CellStatus.TO_GUESS ||
                        cells[row][col].getStatus() == CellStatus.WRONG_GUESS) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Inner class to handle cell input events
     */
    private class CellInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cell sourceCell = (Cell)e.getSource();

            // Skip if cell is a given number
            if (sourceCell.getStatus() == CellStatus.GIVEN) {
                return;
            }

            try {
                int userInput = Integer.parseInt(sourceCell.getText());

                // Debug output
                System.out.println("Row: " + sourceCell.getRow() +
                        ", Col: " + sourceCell.getCol() +
                        ", Input: " + userInput +
                        ", Solution: " + solution[sourceCell.getRow()][sourceCell.getCol()]);

                // Validate input range
                if (userInput >= 1 && userInput <= 9) {
                    // Check against solution
                    if (userInput == solution[sourceCell.getRow()][sourceCell.getCol()]) {
                        sourceCell.setStatus(CellStatus.CORRECT_GUESS);
                        if (isSolved()) {
                            JOptionPane.showMessageDialog(null, "Congratulations! You've solved the puzzle!");
                        }
                    } else {
                        sourceCell.setStatus(CellStatus.WRONG_GUESS);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 9");
                    sourceCell.setText("");
                }
            } catch (NumberFormatException ex) {
                sourceCell.setText("");
            }
        }
    }
}