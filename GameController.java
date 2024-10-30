import javax.swing.JOptionPane;

public class GameController {
    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public void handleMove(Tile tile) {
        if (tile.getText().isEmpty()) {
            tile.setText(game.getCurrentPlayer().getSymbol());
            if (game.checkWin()) {
                endGame(game.getCurrentPlayer().getName() + " wins!");
            } else if (game.getBoard().isFull()) {
                endGame("It's a tie!");
            } else {
                game.switchPlayer();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid move! Try again.");
        }
    }

    private void endGame(String message) {
        int response = JOptionPane.showConfirmDialog(null, message + " Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            game.reset();
        } else {
            System.exit(0);
        }
    }
}
