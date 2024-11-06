import javax.swing.*;

public class BattleshipRunner {
    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        GameController controller = new GameController(board);
        SwingUtilities.invokeLater(() -> new BattleShipFrame(controller));



    }
}
