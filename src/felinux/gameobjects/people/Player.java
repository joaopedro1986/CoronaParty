package felinux.gameobjects.people;

import felinux.gameobjects.GameObject;
import felinux.grid.GridDirection;
import felinux.grid.GridPosition;
import felinux.grid.Grid;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

//Keyboard Controlled Person

public class Player extends GameObject implements KeyboardHandler {

    private String picturePathUp = "resources/images/player/heroeUP.png";
    private String picturePathDown = "resources/images/player/heroeDOWN.png";
    private String picturePathLeft = "resources/images/player/heroeLEFT.png";
    private String picturePathRight = "resources/images/player/heroeRIGHT.png";
    private String picturePathUpRelease = "resources/images/player/releaseUP.png";
    private String picturePathDownRelease = "resources/images/player/releaseDOWN.png";
    private String picturePathRightRelease = "resources/images/player/releaseRIGHT.png";
    private String picturePathLeftRelease = "resources/images/player/releaseLEFT.png";
    private int health;
    private boolean mask;
    private boolean dead;
    private int speed = 0;
    private Text displayHealth;
    private Keyboard keyboard;
    private GridDirection direction;
    private boolean exit;


    private GridPosition gridPosition;

    //Player constructor
    public Player(Grid grid, int x, int y) {
        super(grid, "resources/images/player/releaseDOWN.png", x, y);
        this.health= 100;
        this.mask = false;
        this.dead = false;
        this.exit = false;
        keyboard = new Keyboard(this);

        init();

    }



    public boolean isDead() {
        return this.dead;
    }

    public void setHealth(int incrementHealth) {
        if (this.health + incrementHealth > 100) {
            this.health = 100;
            return;
        }
        if (this.health + incrementHealth <= 0) {
            this.health = 0;
            this.dead = true;
            return;
        }
        this.health = health + incrementHealth;
    }

    public void withMask() {
        this.mask = true;
    }

    public void withoutMask() {
        this.mask = false;
    }

    public int getHealth() {
        return this.health;
    }

    public boolean getMask() {
        return this.mask;
    }

    public void exit() {
        this.exit = true;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public boolean isExit() {
        return exit;
    }

    //Initialize keyboard handlers
    public void init() {

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent upRelease = new KeyboardEvent();
        upRelease.setKey(KeyboardEvent.KEY_UP);
        upRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent downRelease = new KeyboardEvent();
        downRelease.setKey(KeyboardEvent.KEY_DOWN);
        downRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent rightRelease = new KeyboardEvent();
        rightRelease.setKey(KeyboardEvent.KEY_RIGHT);
        rightRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent leftRelease = new KeyboardEvent();
        leftRelease.setKey(KeyboardEvent.KEY_LEFT);
        leftRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        keyboard.addEventListener(up);
        keyboard.addEventListener(upRelease);
        keyboard.addEventListener(down);
        keyboard.addEventListener(downRelease);
        keyboard.addEventListener(left);
        keyboard.addEventListener(leftRelease);
        keyboard.addEventListener(right);
        keyboard.addEventListener(rightRelease);


    }


    public void walk() {
        walking(direction, speed);
    }


    public void walking(GridDirection direction, int speed) {

        if (!dead) {
            setPictureDirection(direction);
            for (int i = 0; i < speed; i++) {
                getPos().moveInDirection(direction, 2);
                super.getSprite().translate(super.getPos().getX() - super.getSprite().getX(), super.getPos().getY() - super.getSprite().getY());
            }
        }
    }


    //Handles key press events
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_UP:
                direction = GridDirection.UP;
                break;
            case KeyboardEvent.KEY_DOWN:
                direction = GridDirection.DOWN;
                break;
            case KeyboardEvent.KEY_RIGHT:
                direction = GridDirection.RIGHT;
                break;
            case KeyboardEvent.KEY_LEFT:
                direction = GridDirection.LEFT;
                break;
        }

        if (speed == 0) {
            walking(direction, 3);
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {

            this.loadSprite(picturePathRightRelease);

        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) {

            this.loadSprite(picturePathLeftRelease);

        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_UP) {

            super.loadSprite(picturePathUpRelease);

        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) {

            this.loadSprite(picturePathDownRelease);

        }

    }

    public void setPictureDirection(GridDirection newDirection) {

        if (dead) {
            return;
        }

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
