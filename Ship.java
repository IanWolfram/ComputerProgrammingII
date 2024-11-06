import java.util.ArrayList;
import java.util.List;

public class Ship {
    private int size;
    private List<Cell> cells;

    public Ship(int size) {
        this.size = size;
        this.cells = new ArrayList<>();
    }

    public void addCell(Cell cell) {
        cells.add(cell);
        cell.placeShip();
    }

    public boolean isSunk() {
        for (Cell cell : cells) {
            if (!cell.isHit()) {
                return false;
            }
        }
        return true;
    }
}
