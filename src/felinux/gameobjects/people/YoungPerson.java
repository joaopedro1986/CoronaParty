package felinux.gameobjects.people;

import felinux.grid.Grid;

public class YoungPerson extends Person  {

    public final static int SPEED = 6;

    public YoungPerson( Grid grid) {
        super(grid, "resources/images/youngperson/youngRIGHT.png","resources/images/youngperson/youngLEFT.png","resources/images/youngperson/youngUP.png","resources/images/youngperson/youngDOWN.png","resources/images/infected/youngINFECTED.png");
    }

    @Override
    public void walk() {
        walking(chooseDirection(),SPEED);
    }

    @Override
    public void setInfected() {
        this.loadSprite("resources/images/infected/youngINFECTED.png");
    }
}
