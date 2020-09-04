package felinux.grid;

public enum GridDirection {
    UP,
    DOWN,
    LEFT,
    RIGHT;


    public boolean isOpposite(GridDirection dir) {
        return dir.equals(this.oppositeDirection());
    }


    public GridDirection oppositeDirection() {

        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return null;
        }
    }
}
