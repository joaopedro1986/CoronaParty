package felinux;


import felinux.gameobjects.people.Player;
import felinux.grid.Grid;
import felinux.grid.GridLevel1;
import felinux.grid.GridMenu;
import felinux.sound.GameSound;

public class Main {

    /**
     * Game settings.
     */
    public static final int DELAY = 100;
    public static final int INFECTION_DAMAGE = 30;
    public static final int ALCOHOL_HEALTH_BOOST = 40;
    public static final double INFECTED_PERCENTAGE = 0.5;
    public static final int LV_1_NUMBER_OF_BOTS = 6;
    public static final int LV_2_NUMBER_OF_BOTS = 8;


    public static void main(String[] args) {

        Grid menu = new GridMenu();
        menu.init();

    }

}
