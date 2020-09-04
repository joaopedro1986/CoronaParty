package felinux.gameobjects.objects;

import felinux.gameobjects.GameObject;
import felinux.gameobjects.people.Player;
import felinux.grid.Grid;
import felinux.grid.GridDirection;
import felinux.grid.GridPosition;

public class Virus extends GameObject {

    private GridPosition playerPos;
    private final int SPEED = 3;

    public Virus(Grid grid, GridPosition playerPos, int x, int y) {
        super(grid, "resources/images/objects/coronavirus.png", x, y);
        this.playerPos = playerPos;
    }

    public GridDirection chooseDirection() {
        if (super.getPos().getY() +20 < playerPos.getY()) {
            return GridDirection.DOWN;
        }
        if (super.getPos().getY() - 20 > playerPos.getY()) {
            return GridDirection.UP;
        }
        if (super.getPos().getX() < playerPos.getX()) {
            return GridDirection.RIGHT;
        }
        return GridDirection.LEFT;
    }

    public void walking() {
        for (int i = 0; i < SPEED; i++) { // Cicle is needed to make people walk
            getPos().moveInDirection(chooseDirection(), 2);
            super.getSprite().translate(super.getPos().getX() - super.getSprite().getX(), super.getPos().getY() - super.getSprite().getY());
        }
    }


    public boolean checkProximity(Player player) {
        if (super.getPos().isNear(player.getPos())) {
            player.setHealth(-100);
            return true;
        }
        return false;
    }
}
