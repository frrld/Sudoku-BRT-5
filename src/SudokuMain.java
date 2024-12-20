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
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main Sudoku program
 */
public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // private variables
    GameBoardPanel board = new GameBoardPanel();
    JButton btnNewGame = new JButton("New Game");

    // Constructor
    public SudokuMain() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(board, BorderLayout.CENTER);
        cp.add(btnNewGame, BorderLayout.SOUTH);

        // Initialize the game board to start the game
        board.newGame();

        // Add ActionListener to the New Game button
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Disable the button temporarily to prevent multiple clicks
                btnNewGame.setEnabled(false);

                try {
                    // First reset the board to clear all cells
                    board.resetBoard();

                    // Then generate and set up a new game
                    board.newGame();

                    // Update the UI
                    board.revalidate();
                    board.repaint();
                } finally {
                    // Re-enable the button
                    btnNewGame.setEnabled(true);
                }
            }
        });

        pack();     // Pack the UI components, instead of using setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
        setTitle("Sudoku");
        setVisible(true);
    }

    /** The entry main() entry method */
    public static void main(String[] args) {
        // Start with the home screen instead of directly launching the game
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuHome();
            }
        });
    }
}