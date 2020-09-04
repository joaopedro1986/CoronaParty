package felinux.gameobjects;

import felinux.grid.GridPosition;
import felinux.grid.Grid;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class GameObject {

    private GridPosition pos;
    private Picture sprite;

    public GameObject(Grid grid, String picturePath) {
        this.sprite = new Picture(0, 0, picturePath);
        this.pos = new GridPosition(grid, this);
        this.sprite.translate(pos.getX(), pos.getY());
        this.sprite.draw();
    }

    public GameObject(Grid grid, String picturePath, int x, int y) {
        this.sprite = new Picture(0, 0, picturePath);
        this.pos = new GridPosition(grid, this, x, y);
        this.sprite.translate(pos.getX(), pos.getY());
        this.sprite.draw();
    }

    public GridPosition getPos() {
        return pos;
    }


    public int getSpriteHeight() {
        return sprite.getHeight();
    }

    public int getSpriteWidth() {
        return sprite.getWidth();
    }

    public Picture getSprite() {
        return sprite;
    }

    public void loadSprite(String picturePath) {
        this.sprite.load(picturePath);
    }


}
