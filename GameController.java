import javax.swing.*;

public class GameController {
    private GameBoard gameBoard;
    private int missCounter = 0;
    private int strikeCounter = 0;

    public GameController(GameBoard board) {
        this.gameBoard = board;  // Ensure gameBoard is properly initialized
    }

    // Accessor to retrieve the GameBoard
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void handleCellClick(int row, int col) {
        Cell cell = gameBoard.getCell(row, col);
        if (cell.isHit()) {
            JOptionPane.showMessageDialog(null, "Already clicked!");
            return;
        }

        cell.hit();
        if (cell.hasShip()) {
            gameBoard.Hitscount();
            missCounter = 0;  // reset miss counter on hit
            JOptionPane.showMessageDialog(null, "Hit!");
            if (gameBoard.allShipsSunk()) {
                JOptionPane.showMessageDialog(null, "You won! All ships are sunk.");
            }

        } else {
            gameBoard.Missescount();
            missCounter++;
            JOptionPane.showMessageDialog(null, "Miss!");
            if (missCounter == 5) {
                strikeCounter++;
                missCounter = 0;  // reset miss counter
                if (strikeCounter == 3) {
                    JOptionPane.showMessageDialog(null, "Game over! You lost.");
                }
            }
        }
    }

    public void resetGame ()
    {
        gameBoard = new GameBoard();
        missCounter = 0;
        strikeCounter = 0;
        gameBoard.resetHits();
    }

    public int getStrikeCounter() {
        return strikeCounter;
    }
}
