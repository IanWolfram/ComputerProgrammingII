import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {
    private Cell[][] grid;
    private ArrayList<Ship> ships;
    private static final int SIZE = 10;
    private int totalHits = 0;
    private int totalMisses = 0;

    public GameBoard() {
        grid = new Cell[SIZE][SIZE];
        ships = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Cell();
            }
        }
        placeShips();
    }

    private void placeShips() {
        int[] shipSizes = {5, 4, 3, 3, 2};  // Ships of varying lengths
        Random random = new Random();

        for (int size : shipSizes) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(SIZE);
                int col = random.nextInt(SIZE);
                boolean horizontal = random.nextBoolean();

                if (canPlaceShip(row, col, size, horizontal))
                {
                    Ship ship = new Ship(size);
                    for (int i = 0; i < size; i++)
                    {
                        Cell cell = horizontal ? grid[row][col + i] : grid[row + i][col];
                        ship.addCell(cell);
                    }
                    ships.add(ship);
                    placed = true;
                }
            }
        }
    }

    private boolean canPlaceShip(int row, int col, int size, boolean horizontal) {
        if (horizontal)
        {
            if (col + size > SIZE) return false;
            for (int i = 0; i < size; i++) {
                if (grid[row][col + i].hasShip()) return false;
            }
        }
        else
        {
            if (row + size > SIZE) return false;
            for (int i = 0; i < size; i++)
            {
                if (grid[row + i][col].hasShip()) return false;
            }
        }
        return true;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) return false;
        }
        return true;
    }

    public void Hitscount() {
        totalHits++;
    }

    public void resetHits()
    {
        totalHits = 0;
    }

    public void Missescount() {
        totalMisses++;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public int getTotalMisses() {
        return totalMisses;
    }
}
