import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleShipFrame extends JFrame {
    private JFrame frame;
    private JButton[][] buttons;
    private GameController controller;
    private JLabel hitCounterLabel;
    private JLabel missCounterLabel;
    private JLabel strikeCounterLabel;

    private Icon waveIcon;
    private Icon missIcon;
    private Icon hitIcon;

    public BattleShipFrame(GameController controller) {
        this.controller = controller;
        loadIcons();  // Load and scale icons

        frame = new JFrame("Battleship Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(10, 10));
        buttons = new JButton[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button = new JButton();
                button.setIcon(waveIcon);  // Set default wave icon
                int row = i;
                int col = j;

                // Add click listener to handle cell clicks
                button.addActionListener(e -> {
                    controller.handleCellClick(row, col);
                    updateButton(row, col);
                });

                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }

        // Panel for displaying game counters
        JPanel statusPanel = new JPanel(new FlowLayout());
        hitCounterLabel = new JLabel("Hits: 0");
        missCounterLabel = new JLabel("Misses: 0");
        strikeCounterLabel = new JLabel("Strikes: 0");

        statusPanel.add(hitCounterLabel);
        statusPanel.add(missCounterLabel);
        statusPanel.add(strikeCounterLabel);

        // Panel for control buttons (Quit, Play Again)
        JPanel controlPanel = new JPanel(new FlowLayout());

        // Add Play Again Button (if applicable)
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    frame, "Are you sure you want to start a new game?", "Play Again",
                    JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                resetGame();
            }
        });
        controlPanel.add(playAgainButton);

        // Add Quit Button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    frame, "Are you sure you want to quit?", "Quit Game",
                    JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                frame.dispose();  // Close the window
            }
        });
        controlPanel.add(quitButton);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }



    private void loadIcons() {
        waveIcon = new ImageIcon("C:/Users/ianwo/OneDrive - University of Cincinnati/Computer Programming II/Lab07C_BattleShip/wave.jpg");
        missIcon = new ImageIcon("C:/Users/ianwo/OneDrive - University of Cincinnati/Computer Programming II/Lab07C_BattleShip/miss.jpg");
        hitIcon = new ImageIcon("C:/Users/ianwo/OneDrive - University of Cincinnati/Computer Programming II/Lab07C_BattleShip/hit.jpg");
    }

    private void updateButton(int row, int col) {
        Cell cell = controller.getGameBoard().getCell(row, col);
        JButton button = buttons[row][col];

        if (cell.isHit()) {
            if (cell.hasShip()) {
                button.setIcon(hitIcon);  // Display hit icon
                button.setDisabledIcon(hitIcon); // Ensure disabled icon remains in color
            } else {
                button.setIcon(missIcon);  // Display miss icon
                button.setDisabledIcon(missIcon); // Ensure disabled icon remains in color
            }
        }
        button.setEnabled(false);  // Disable button after click
        updateStatus();
    }

    private void updateStatus() {
        hitCounterLabel.setText("Hits: " + controller.getGameBoard().getTotalHits());
        missCounterLabel.setText("Misses: " + controller.getGameBoard().getTotalMisses());
        strikeCounterLabel.setText("Strikes: " + controller.getStrikeCounter());
    }

    private void resetGame() {
        controller.resetGame();  // Reset game state in controller and UI
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setIcon(waveIcon); // Reset to wave icon
                buttons[i][j].setEnabled(true);  // Enable button for new game
            }
        }
        updateStatus();
    }
}
