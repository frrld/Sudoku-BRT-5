/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #5
 * 1 - 5026231175 - Muhammad Farrel Danendra
 * 2 - 5026231150 - Muhammad Dzaki Adfiz
 * 3 - 5026231212 - Baqhiz Faruq S.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
/**
 * This class creates the panels used for the border on the main window.
 */
public class SPanel extends Panel   //create border panels for the display
{
    private static final long serialVersionUID = 1L;
    Color WS = new Color(0xf5, 0xf5, 0xf5);  //White Smoke background
    public SPanel(Dimension set)
    {
        super();  //construct the panel
        this.setBackground(WS);
        this.setPreferredSize(set);
    }//end of constructor
}//end of SPane
