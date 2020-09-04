package felinux.gameobjects;

import felinux.gameobjects.people.*;
import felinux.grid.Grid;

public class GameObjectFactory {

    public static Person getNewPerson(Grid grid) {

        int random = (int) (Math.random() * PersonType.values().length);
        PersonType personType = PersonType.values()[random];

        Person person;

        switch (personType) {
            case OLDPERSON:
                person = new OldPerson(grid);
                break;
            case YOUNGPERSON:
                person = new YoungPerson(grid);
                break;
            default:
                person = new Adult(grid);
        }

        return person;
    }

}
        