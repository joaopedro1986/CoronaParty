package felinux.gameobjects.people;

import felinux.Main;
import felinux.ProximityDetector;
import felinux.gameobjects.GameObject;
import felinux.grid.GridDirection;
import felinux.grid.Grid;

public abstract class Person extends GameObject implements Walkable {

    private boolean infected;
    private boolean canInfect;
    private int directionChangeLevel = 7;

    protected ProximityDetector proximityDetector; //Composition Relation
    protected GridDirection currentDirection; //Composition Relation

    private String picturePathUp;
    private String picturePathDown;
    private String picturePathLeft;
    private String picturePathRight;
    private String picturePathInfected;


    //Constructor Method of Person
    public Person(Grid grid, String picturePathRight,String picturePathLeft,String picturePathUp,String picturePathDown, String pictureInfected) {
        super(grid,picturePathDown);
        this.infected = Math.random() > Main.INFECTED_PERCENTAGE ? false : true;
        this.currentDirection = GridDirection.values()[(int) (Math.random() * GridDirection.values().length)];
        this.canInfect = true;
        this.picturePathUp = picturePathUp;
        this.picturePathDown = picturePathDown;
        this.picturePathLeft = picturePathLeft;
        this.picturePathRight = picturePathRight;
        this.picturePathInfected = pictureInfected;
    }

    public void setProximityDetector(ProximityDetector proximityDetector) {
        this.proximityDetector = proximityDetector;
    }

    public void setDirectionChangeLevel(int directionChangeLevel) {
        this.directionChangeLevel = directionChangeLevel;
    }

    public boolean isInfected() {
        return infected;
    }

    public void setInfected() {
        this.infected = true;
        this.loadSprite("resources/images/infected/infected.png");
    }

    //Method constructor of ChooseDirection
    public GridDirection chooseDirection() {
        //Moving the person in the same direction
        GridDirection newDirection = currentDirection;

        //Change direction of the Person
        if (Math.random() > ((double) directionChangeLevel) / 10) {
            newDirection = GridDirection.values()[(int) (Math.random() * GridDirection.values().length)];
            //the person cannot walk on his back
            if (newDirection.isOpposite(currentDirection)) { //isOpposite is a method in GridDirection class.
                return chooseDirection();
            }
        }
        setPictureDirection(newDirection);
        return newDirection;
    }

    //Moving the Person
    public void walking(GridDirection direction, int speed) {
        GridDirection newDirection = direction;
        if (isHittingWall()) {
            newDirection = currentDirection.oppositeDirection();
        }
        this.currentDirection = newDirection;
        for (int i = 0; i < speed; i++) { // Cicle is needed to make people walk
            getPos().moveInDirection(newDirection, 2);
            super.getSprite().translate(super.getPos().getX() - super.getSprite().getX(), super.getPos().getY() - super.getSprite().getY());

        }
    }
    //  }

    public boolean isHittingWall() {
        switch (currentDirection) {
            // Waiting for GridPosition methods and properties.
            case LEFT:
                if (getPos().getX() == super.getPos().getMinX()) {
                    return true;
                }
                break;
            case RIGHT:
                if (getPos().getX() == super.getPos().getMaxX()) {
                    return true;
                }
                break;
            case UP:
                if (getPos().getY() == super.getPos().getMinY()) {
                    return true;
                }
                break;
            case DOWN:
                if (getPos().getY() == super.getPos().getMaxY()) {
                    return true;
                }
        }
        return false;
    }
    

    public void ableToInfect() {
        this.canInfect = true;
    }

    public void unAbleToInfect() {
        this.canInfect = false;
    }

    public boolean getCanInfect() {
        return this.canInfect;
    }

    public void setPictureDirection(GridDirection newDirection) {

        switch (newDirection) {
            case UP:
                this.loadSprite(picturePathUp);
                break;
            case DOWN:
                this.loadSprite(picturePathDown);
                break;
            case LEFT:
                this.loadSprite(picturePathLeft);
                break;
            case RIGHT:
                this.loadSprite(picturePathRight);
                break;
        }

    }

}
