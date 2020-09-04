package felinux.grid;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Grid {

    public static final int PADDING_LEFT = 10;
    public static final int PADDING_UP = 80;
    private Picture background;
    private String path;
    private int wallThickness;

    public Grid(String path, int wallThickness) {
        this.path = path;
        this.wallThickness = wallThickness;
    }

    public void init() {
        this.background = new Picture(PADDING_LEFT,PADDING_UP,path);
        this.background.draw();
    }

    public int getWidth() {
        return background.getWidth();
    }

    public int getHeight() {
        return background.getHeight();
    }

    public int getWallThickness() {
        return wallThickness;
    }

}
