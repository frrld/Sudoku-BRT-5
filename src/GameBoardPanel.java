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

public class GameBoardPanel extends JPanel {
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // Define named constants for UI sizes
    public static final int CELL_SIZE = 60;   // Cell width/height in pixels
    public static final int BOARD_WIDTH = CELL_SIZE * SudokuConstants.GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;

    // Define properties
    private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    private Puzzle puzzle;

    /** Constructor */
    public GameBoardPanel() {
        super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));

        // Initialize puzzle
        puzzle = new Puzzle();

        // Allocate the 2D array of Cell, and add into JPanel.
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col] = new Cell(row, col);
                super.add(cells[row][col]);
            }
        }

        // Add common listener to all cells
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
     */
    public void resetBoard() {
        // Generate new puzzle first
        puzzle.newPuzzle(4); // Generate new puzzle with difficulty level 4

        // Reset and update all cells
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                // Get the value from the newly generated puzzle
                int value = puzzle.numbers[row][col];
                boolean isGiven = (value != 0);

                // Reset the cell and set new value
                cells[row][col].newGame(value, isGiven);
            }
        }
    }

    /**
     * Generate a new game
     */
    public void newGame() {
        // Simply call resetBoard as it now handles both reset and new game generation
        resetBoard();
    }

    /**
     * Return true if the puzzle is solved
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

    private class CellInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cell sourceCell = (Cell) e.getSource();

            try {
                int numberIn = Integer.parseInt(sourceCell.getText());

                // Validate input number (1-9)
                if (numberIn >= 1 && numberIn <= 9) {
                    System.out.println("You entered " + numberIn);

                    // Check if the number matches the solution
                    if (numberIn == sourceCell.getNumber()) {
                        sourceCell.setStatus(CellStatus.CORRECT_GUESS);

                        // Check if puzzle is solved after correct guess
                        if (isSolved()) {
                            JOptionPane.showMessageDialog(null,
                                    "Congratulations! You've solved the puzzle!");
                        }
                    } else {
                        sourceCell.setStatus(CellStatus.WRONG_GUESS);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a number between 1 and 9");
                    sourceCell.setText("");
                }
            } catch (NumberFormatException ex) {
                // Clear invalid input
                sourceCell.setText("");
            }
        }
    }
}