/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #5
 * 1 - 5026231175 - Muhammad Farrel Danendra
 * 2 - 5026231150 - Muhammad Dzaki Adfiz
 * 3 - 5026231212 - Baqhiz Faruq S.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuHome extends JFrame {
    private static final long serialVersionUID = 1L;

    // Components
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JButton startButton;

    public SudokuHome() {
        setTitle("Sudoku Game by BRT project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        setupLayout();
        pack();
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
    }

    private void initializeComponents() {
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 245));
        mainPanel.setPreferredSize(new Dimension(1000, 700));

        // Create title with custom font and size
        titleLabel = new JLabel("<html><div style='text-align: center;'>SUDOKU<br><span style='font-size:20px;'>by BRT-C-5</span></div></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(new Color(44, 62, 80));

        // Create start button with custom styling
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setPreferredSize(new Dimension(150, 50));
        startButton.setBackground(new Color(52, 152, 219));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);

        // Add hover effect
        startButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                startButton.setBackground(new Color(41, 128, 185));
            }

            public void mouseExited(MouseEvent e) {
                startButton.setBackground(new Color(52, 152, 219));
            }
        });

        // Add action listener to start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the home screen
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new SudokuMain(); // Start the game
                    }
                });
            }
        });
    }


    private void setupLayout() {
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0); // Add space below title
        mainPanel.add(titleLabel, gbc);

        // Add start button
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(startButton, gbc);

        // Add panel to frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SudokuHome home = new SudokuHome();
                home.setVisible(true);
            }
        });
    }
}