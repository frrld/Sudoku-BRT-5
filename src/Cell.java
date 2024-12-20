import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
/**
 * The Cell class model the cells of the Sudoku puzzle, by customizing (subclass)
 * the javax.swing.JTextField to include row/column, puzzle number and status.
 */

/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #5
 * 1 - 5026231175 - Muhammad Farrel Danendra
 * 2 - 5026231150 - Muhammad Dzaki Adfiz
 * 3 - 5026231212 - Baqhiz Faruq S.
 */

public class Cell extends JTextField {
    private static final long serialVersionUID = 1L; // To prevent serial warning

    // Named constants for JTextField's colors and fonts
    public static final Color BG_GIVEN = new Color(240, 240, 240); // RGB
    public static final Color FG_GIVEN = Color.BLACK;
    public static final Color FG_NOT_GIVEN = Color.GRAY;
    public static final Color BG_TO_GUESS  = Color.YELLOW;
    public static final Color BG_CORRECT_GUESS = new Color(0, 216, 0);
    public static final Color BG_WRONG_GUESS   = new Color(216, 0, 0);
    public static final Font FONT_NUMBERS = new Font("OCR A Extended", Font.PLAIN, 28);
    // Properties of each cell
    private final int row, col;  // Row and column of this cell
    private int number;          // Puzzle number [1-9] for this cell
    private CellStatus status;   // The status of this cell defined in enum CellStatus

    /** Constructor */
    public Cell(int row, int col) {
        super(); // JTextField constructor
        this.row = row;
        this.col = col;

        // Set default styles for the cell
        super.setHorizontalAlignment(JTextField.CENTER);
        super.setFont(FONT_NUMBERS);

        // Initialize default state
        resetCell();
    }

    /**
     * Reset this cell for a new game, given the puzzle number and its status.
     * @param number The puzzle number for this cell
     * @param isGiven True if the cell is a given clue
     */
    public void newGame(int number, boolean isGiven) {
        this.number = number;
        this.status = isGiven ? CellStatus.GIVEN : CellStatus.TO_GUESS;
        paintCell(); // Update the appearance of the cell
    }

    /**
     * Paint this cell based on its current status and number.
     */
    public void paintCell() {
        // Update display text and editable property based on status
        if (status == CellStatus.GIVEN) {
            setCellAppearance(number == 0 ? "" : String.valueOf(number), BG_GIVEN, FG_GIVEN, false);
        } else if (status == CellStatus.TO_GUESS) {
            setCellAppearance("", BG_TO_GUESS, FG_NOT_GIVEN, true);
        } else if (status == CellStatus.CORRECT_GUESS) {
            setCellAppearance(String.valueOf(number), BG_CORRECT_GUESS, FG_GIVEN, false);
        } else if (status == CellStatus.WRONG_GUESS) {
            setCellAppearance("", BG_WRONG_GUESS, FG_NOT_GIVEN, true);
        }
    }

    /**
     * Helper method to set cell appearance.
     * @param text The text to display
     * @param bgColor The background color
     * @param fgColor The foreground color
     * @param editable True if the cell is editable
     */
    private void setCellAppearance(String text, Color bgColor, Color fgColor, boolean editable) {
        super.setText(text);
        super.setBackground(bgColor);
        super.setForeground(fgColor);
        super.setEditable(editable);
    }

    /**
     * Reset the cell completely (used for resetting the entire board).
     */
    public void resetCell() {
        this.number = 0;
        this.status = CellStatus.TO_GUESS;
        paintCell();
    }

    /**
     * Explicitly set the cell's status and repaint it.
     * @param status The new status of the cell
     */
    public void setStatus(CellStatus status) {
        this.status = status;
        paintCell(); // Update the appearance immediately
    }

    /**
     * Get the current number in this cell.
     * @return The number in this cell
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set a new number for this cell.
     * @param number The new number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Get the status of this cell.
     * @return The current status of the cell
     */
    public CellStatus getStatus() {
        return status;
    }

    /**
     * Get the row of this cell.
     * @return The row of the cell
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column of this cell.
     * @return The column of the cell
     */
    public int getCol() {
        return col;
    }
}