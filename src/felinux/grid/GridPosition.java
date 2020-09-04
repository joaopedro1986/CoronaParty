package felinux.grid;

import felinux.gameobjects.GameObject;

import static felinux.grid.Grid.PADDING_LEFT;
import static felinux.grid.Grid.PADDING_UP;

public class GridPosition {

    private Grid grid;
    private GameObject gameObject;
    private int x;
    private int y;

    public GridPosition(Grid grid, GameObject gameObject) {
        this.grid = grid;
        this.gameObject = gameObject;
        this.x = getMinX() + (int) (Math.random() * (getMaxX() - getMinX()));
        this.y = getMinY() + (int) (Math.random() * (getMaxY() - getMinY()));
    }

    public GridPosition(Grid grid, GameObject gameObject, int x, int y) {
        this.grid = grid;
        this.gameObject = gameObject;
        this.x = x;
        this.y = y;
    }

    public int getMinX() {
        return PADDING_LEFT + grid.getWallThickness();
    }

    public int getMinY() {
        return PADDING_UP + grid.getWallThickness();
    }

    public int getMaxX() {
        return PADDING_LEFT + grid.getWidth() - grid.getWallThickness() -  gameObject.getSpriteWidth();
    }

    public int getMaxY() {
        return PADDING_UP + grid.getHeight() - grid.getWallThickness() - gameObject.getSpriteHeight();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void moveInDirection(GridDirection direction, int distance) {

        switch (direction) {
            case UP:
                y = Math.max(y - distance, getMinY());
                break;
            case DOWN:
                y = Math.min(y + distance, getMaxY());
                break;
            case LEFT:
                x = Math.max(x - distance, getMinX());
                break;
            case RIGHT:
                x = Math.min(x + distance, getMaxX());
                break;
        }

    }

    //check if two objects are near each other
    public boolean isNear(GridPosition position) {

        if (Math.abs(this.getX() - position.getX()) <=60 && Math.abs(this.getY() - position.getY()) <=100) {
            return true;
        }
        return false;
    }

    //check if two objects are in same position (with an error margin)
    public boolean samePosition(GridPosition position) {

        if (Math.abs(this.getX() - position.getX()) <=20 && Math.abs(this.getY() - position.getY()) <=20) {
            return true;
        }
        return false;

    }
}
