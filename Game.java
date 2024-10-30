public class Game {

    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    public Game(Player Player1, Player Player2) {
        this.board = new Board();
        this.player1 = Player1;
        this.player2 = Player2;
        this.currentPlayer = player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public void switchPlayer() {
        if(currentPlayer.getName().equals(player1.getName())) {
            currentPlayer = player2;
        }
        else {
            currentPlayer = player1;
        }
    }

    public Board getBoard() {
        return board;
    }

    public boolean checkWin() {
        Tile[][] tiles = board.getTiles();
        // Check rows, columns, and diagonals
        return checkRows(tiles) || checkColumns(tiles) || checkDiagonals(tiles);
    }

    private boolean checkRows(Tile[][] tiles) {
        for (int row = 0; row < 3; row++) {
            if (tiles[row][0].getText().equals(currentPlayer.getSymbol()) &&
                    tiles[row][1].getText().equals(currentPlayer.getSymbol()) &&
                    tiles[row][2].getText().equals(currentPlayer.getSymbol())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(Tile[][] tiles) {
        for (int col = 0; col < 3; col++) {
            if (tiles[0][col].getText().equals(currentPlayer.getSymbol()) &&
                    tiles[1][col].getText().equals(currentPlayer.getSymbol()) &&
                    tiles[2][col].getText().equals(currentPlayer.getSymbol())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(Tile[][] tiles) {
        return (tiles[0][0].getText().equals(currentPlayer.getSymbol()) &&
                tiles[1][1].getText().equals(currentPlayer.getSymbol()) &&
                tiles[2][2].getText().equals(currentPlayer.getSymbol())) ||
                (tiles[0][2].getText().equals(currentPlayer.getSymbol()) &&
                        tiles[1][1].getText().equals(currentPlayer.getSymbol()) &&
                        tiles[2][0].getText().equals(currentPlayer.getSymbol()));
    }

    public void reset() {
        board.reset();
        currentPlayer = player1;
    }





}
