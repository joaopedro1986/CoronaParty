package felinux.gameobjects.people;

public enum PersonType {

    ADULT("adult/putImagesHereAndDeleteMe.png"),
    OLDPERSON ("olderperson/putImagesHereAndDeleteMe.png"),
    YOUNGPERSON ("youngperson/putImagesHereAndDeleteMe.png");


    private String personString;

    //Temporary constructor method
    PersonType(String personString) {
        this.personString = personString;
    }

    public String getPersonString() {
        return this.personString;
    }
}
