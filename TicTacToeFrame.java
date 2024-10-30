import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {
    public TicTacToeFrame(GameController controller, Board board) {
        setTitle("Tic Tac Toe");
        setLayout(new GridLayout(3, 3));
        setPreferredSize(new Dimension(500, 500));
        initializeBoard(board, controller);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void initializeBoard(Board board, GameController controller) {
        for (Tile[] row : board.getTiles()) {
            for (Tile tile : row) {
                tile.addActionListener(e -> controller.handleMove(tile));
                add(tile);
            }
        }
    }
}
