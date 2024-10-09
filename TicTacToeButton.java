import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeButton extends JButton {
    private final int row;
    private final int col;
    private final TicTacToeFrame frame;

    public TicTacToeButton(int row, int col, TicTacToeFrame frame) {
        this.row = row;
        this.col = col;
        this.frame = frame;
        setText(""); // Start with empty button
        addActionListener(new ButtonClickListener());
    }

    public void reset() {
        setText("");
        setEnabled(true);
    }

    public boolean isEmpty() {
        return getText().isEmpty();
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isEmpty()) {
                // Update the board array
                frame.getBoard()[row][col] = frame.getCurrentPlayer();

                setText(frame.getCurrentPlayer());
                frame.increaseMoveCount();

                // Check for win or tie
                if (frame.checkWin())
                {
                    int result = JOptionPane.showConfirmDialog(frame,
                             "Would you like to play again", "Player " + frame.getCurrentPlayer() + " wins!",
                            JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION)
                    {
                        frame.resetBoard();
                    } else
                    {
                        frame.endGame("Thanks for playing!");
                    }
                }
                else if (frame.checkTie())
                {
                    int result = JOptionPane.showConfirmDialog(frame,
                             "Would you like to play again", "It's a tie!",
                            JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION)
                    {
                        frame.resetBoard(); // Reset the board for a new game
                    }
                    else
                    {
                        frame.endGame("Thanks for playing!");
                    }
                }
                else
                {
                    frame.switchPlayer(); // Switch to the next player
                }
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Illegal move! Try again.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}
