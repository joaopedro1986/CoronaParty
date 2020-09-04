package felinux;

import felinux.grid.Grid;
import felinux.grid.GridFinalLevel;
import felinux.grid.GridLevel1;
import felinux.grid.GridLevel2;

public enum LevelType {
    LEVEL1,
    LEVEL2,
    FINAL_LEVEL;

    public Grid getGrid() {

        switch (this) {
            case LEVEL1:
                return new GridLevel1();
            case LEVEL2:
                return new GridLevel2();
            case FINAL_LEVEL:
                return new GridFinalLevel();
            default:
                return null;
        }
    }

    public int getNumberOfBots() {

        switch (this) {
            case LEVEL1:
                return Main.LV_1_NUMBER_OF_BOTS;
            case LEVEL2:
                return Main.LV_2_NUMBER_OF_BOTS;
            default:
                return 0;
        }
    }

    public boolean hasVirus() {
        if (this == FINAL_LEVEL) {
            return true;
        }
        return false;
    }

    public boolean hasMask() {
        if (this == FINAL_LEVEL) {
            return false;
        }
        return true;
    }

    public boolean hasAlcoholGel() {
        if (this == FINAL_LEVEL) {
            return false;
        }
        return true;
    }

}
