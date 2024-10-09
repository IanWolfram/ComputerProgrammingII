import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {
    private final TicTacToeButton[][] board;
    private String currentPlayer;
    private int moveCount;
    private static final int MOVES_FOR_WIN = 5;
    private static final int MOVES_FOR_TIE = 7;


    public TicTacToeFrame() {
        board = new TicTacToeButton[3][3];
        currentPlayer = "X";
        moveCount = 0;

        setTitle("Tic Tac Toe");
        setLayout(new GridLayout(4, 3));
        initializeBoard();
        initializeQuitButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = new TicTacToeButton(row, col, this);
                board[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                add(board[row][col]);
            }
        }
    }

    private void initializeQuitButton() {
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        add(quitButton);
    }

    public void switchPlayer() {
        if(moveCount % 2 == 0)
            currentPlayer = "X";
        else
            currentPlayer = "O";
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String[][] getBoard() {
        String[][] newboard = new String[3][3];

        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                newboard[row][col] = board[row][col].getText();
            }
        }
        return newboard;
    }



    public boolean checkWin() {
        if(isColWin(currentPlayer) || isRowWin(currentPlayer) || isDiagonalWin(currentPlayer))
        {
            return true;
        }

        return false;



    }
    public boolean isColWin(String player)
    {
        String[][] b = getBoard();
        for(int col=0; col < 3; col++)
        {
            if(b[0][col].equals(player) &&
                    b[1][col].equals(player) &&
                    b[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isRowWin(String player)
    {
        String[][] b = getBoard();
        for(int row=0; row < 3; row++)
        {
            if(b[row][0].equals(player) &&
                    b[row][1].equals(player) &&
                    b[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private boolean isDiagonalWin(String player)
    {
        String b [][] = getBoard();
        if(b[0][0].equals(player) &&
                b[1][1].equals(player) &&
                b[2][2].equals(player) )
        {
            return true;
        }
        if(b[0][2].equals(player) &&
                b[1][1].equals(player) &&
                b[2][0].equals(player) )
        {
            return true;
        }
        return false;
    }


    public boolean checkTie() {
        return moveCount >= 9;
    }

    public void endGame(String message) {
        JOptionPane.showMessageDialog(this, message);
        resetBoard();
    }

    public void resetBoard() {
        currentPlayer = "X";
        moveCount = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].reset();
            }
        }
    }

    public void increaseMoveCount() {
        moveCount++;
    }


}
