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
                // Create a SwingWorker to handle the new game logic
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        // Simulate a time-consuming task (e.g., resetting the board)
                        board.resetBoard(); // Reset the board
                        board.newGame(); // Start a new game
                        return null;
                    }

                    @Override
                    protected void done() {
                        // Update the UI if necessary after the task is done
                        board.revalidate(); // Revalidate the board
                        board.repaint();    // Repaint the board
                        board.newGame(); // Start a new game
                    }
                }.execute(); // Execute the SwingWorker
            }
        });

        pack();     // Pack the UI components, instead of using setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
        setTitle("Sudoku");
        setVisible(true);
    }

    /** The entry main() entry method */
    public static void main(String[] args) {
        // Run the constructor of "SudokuMain" on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuMain();
            }
        });
    }
}