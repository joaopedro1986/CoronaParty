package felinux.gameobjects.people;

import felinux.grid.Grid;
import felinux.grid.GridDirection;

public class Adult extends Person {

    private static final int SPEED = 3;

    public Adult( Grid grid) {
        super(grid, "resources/images/adult/manRIGHT.png","resources/images/adult/manLEFT.png","resources/images/adult/manUP.png","resources/images/adult/manDOWN.png","resources/images/infected/manINFECTED.png");
    }

    @Override
    public void walk() {
        walking(chooseDirection(), SPEED);
    }

    @Override
    public void setInfected() {
        this.loadSprite("resources/images/infected/manINFECTED.png");
    }

}
