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
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Gui {

    /*
    Initializes objects that are used vor accessing the fields
    Riddles are stored in the riddles.java
     */

    public static JLabel currentlabel;
    public static JPanel currentpanel;
    public static JLabel[][] alllabels = new JLabel[9][9];
    public static JPanel[][] allpanels = new JPanel[9][9];

    private static int currenti;
    private static int currentj;
    public static int[][] riddle = Riddles.riddle();

    /*
    This is where gui generation starts
     */

    public static void window() {

        /*
        Definition of the listeners:
        The KeyListener manages the user's input:
        It converts the KeyChars using the convertKey.java.
         */

        KeyListener keylistener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                int keychar = (e.getKeyChar());
                int key = ConvertKey.convert(keychar);

                new PrintScreen(key, currentlabel, currenti, currentj, riddle);

            }
        };

        /*
        Creation of the JFrame
        The upper label is being created and is added to a panel
        Also two borders are created
          */

        JFrame window = new JFrame();
        window.setTitle("Sudoku");
        window.setBackground(Color.WHITE);
        window.setSize(600, 650);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.addKeyListener(keylistener);

        JPanel up = new JPanel();
        up.setBackground(Color.WHITE);

        JLabel name = new JLabel();
        name.setText("Sudoku");
        name.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        name.setHorizontalAlignment(SwingConstants.CENTER);

        up.add(name);
        window.add(up, BorderLayout.NORTH);

        JPanel background = new JPanel();
        background.setBackground(Color.WHITE);
        background.setLayout(new GridLayout(3, 3));

        window.add(background);

        Border borderlarge = BorderFactory.createLineBorder(Color.DARK_GRAY, 3);
        Border bordersmall = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);

         /*
        Creation of the grid:
        We'll start with a for-loop that creates 9 small panels
        (because the background has a 3*3 GridLayout)
          */

        for (int i = 0; i < 9; i++) {

            JPanel grid = new JPanel();
            grid.setBorder(borderlarge);
            grid.setLayout(new GridLayout(3, 3));
            grid.setBackground(Color.WHITE);

         /*
        Now we'll create each field and a corresponding label.
        The number of the fields will be taken from our riddle array.
        It'll be the label's text (if it's not 0)
          */

            for (int j = 0; j < 9; j++) {

                JPanel field = new JPanel();
                field.setBorder(bordersmall);
                field.setBackground(Color.WHITE);
                field.setLayout(new BorderLayout());

                JLabel text = new JLabel();
                text.setHorizontalAlignment(SwingConstants.CENTER);
                text.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
                text.setForeground(Color.BLUE);

                if (riddle[i][j] != 0) {

                    text.setForeground(Color.BLACK);
                    text.setText(String.valueOf(riddle[i][j]));
                }

                alllabels[i][j] = text;
                field.add(text, BorderLayout.CENTER);

                  /*
                  MouseListener for each field:
                  Manages hover animations and field selection
                  */

                int finalI = i;
                int finalJ = j;

                field.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                        /*
                        Selecting a field:
                        If a field has been selected prior to the new one it's color will be reset to white.
                        After that the global variables will  be set to new field and its label.
                        Then the printscreen will check for conflicting fields.
                         */

                        if (currentpanel != null) {
                            currentpanel.setBackground(Color.WHITE);
                        }

                        field.setBackground(Color.cyan);

                        currentlabel = text;
                        currentpanel = field;
                        currenti = finalI;
                        currentj = finalJ;

                        if (!currentlabel.getText().isEmpty() && currentlabel.getForeground() != Color.BLACK) {
                            int key = Integer.parseInt(currentlabel.getText());
                            CheckNumber.clearColor();
                            new PrintScreen(key, currentlabel, currenti, currentj, riddle);

                        }

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    /*
                    Hover Stuff
                     */

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (field.getBackground() == Color.WHITE) {
                            field.setBackground(Color.LIGHT_GRAY);
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (field.getBackground() == Color.LIGHT_GRAY) {
                            field.setBackground(Color.WHITE);
                        }

                    }
                });

                 /*
                 The field is added to the grid
                 The panels are added to a global array
                  */

                grid.add(field);
                allpanels[i][j] = field;

            }

            background.add(grid);
        }

        /*
        Button for loading a new sudoku
        Gets a riddle from the riddle.java and prints it to the field
         */

        JPanel newriddle = new JPanel();
        newriddle.setBackground(Color.WHITE);

        JLabel newtext = new JLabel("New Sudoku");
        newtext.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));

        MouseListener newlistener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                riddle = Riddles.riddle();
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (riddle[i][j] != 0) {
                            alllabels[i][j].setText(String.valueOf(riddle[i][j]));
                            alllabels[i][j].setForeground(Color.BLACK);
                        } else {
                            alllabels[i][j].setText("");
                            alllabels[i][j].setForeground(Color.BLUE);
                        }
                        allpanels[i][j].setBackground(Color.WHITE);
                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                newtext.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newtext.setForeground(Color.BLACK);
            }
        };

        newriddle.add(newtext);
        newriddle.addMouseListener(newlistener);

        window.add(newriddle, BorderLayout.SOUTH);
        window.setVisible(true);
    }


}
