public class Board {
    private final Tile[][] tiles;

    public Board() {
        tiles = new Tile[3][3];
        initiailzeTiles();
    }

    private void initiailzeTiles() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
    }


    public Tile[][] getTiles() {
        return tiles;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tiles[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tiles[i][j].reset();
            }
        }
    }































}
