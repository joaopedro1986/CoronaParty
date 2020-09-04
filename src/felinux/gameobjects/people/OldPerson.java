package felinux.gameobjects.people;

import felinux.grid.Grid;

public class OldPerson extends Person  {

    public final static int SPEED = 1;

    public OldPerson( Grid grid) {
        super(grid, "resources/images/oldman/oldmanRIGHT.png","resources/images/oldman/oldmanLEFT.png","resources/images/oldman/oldmanUP.png","resources/images/oldman/oldmanDOWN.png","resources/images/infected/oldmanINFECTED.png");
    }

    @Override
    public void walk() {
        walking(chooseDirection(), SPEED);
    }

    @Override
    public void setInfected() {
        this.loadSprite("resources/images/infected/oldmanINFECTED.png");
    }
}
