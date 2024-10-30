
import javax.swing.*;
import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wulft
 */
public class Tile extends JButton
{
    private final int row;
    private final int col;
    private final Font font = new Font("Arial", Font.BOLD, 60);

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        setText("");
        setFont(font);

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public void reset() {
        setText("");
        setEnabled(true);
    }
    
    
    
    
}
