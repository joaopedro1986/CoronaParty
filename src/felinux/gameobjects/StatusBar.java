package felinux.gameobjects;

import felinux.grid.Grid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class StatusBar {

    private final int LENGTH = 300;
    private final int HEIGHT = 60;
    private final int PADDING = 10;
    private int X_POSITION ;
    private final int OVERLAY = 5;

    private static long startTime;
    private static long elapsedTime;
    private static long elapsedDisplay;
    private static long secondsDisplay;
    private static Text timer = new Text(200,50,"Timer: ");

    Rectangle healthBarOverlay;
    Rectangle healthBar;
    Picture healthIcon;

    public void init(Grid grid) {

      X_POSITION = grid.getWidth() - LENGTH;
      healthBarOverlay = new Rectangle(X_POSITION, PADDING, LENGTH, HEIGHT);
      healthBarOverlay.setColor(Color.RED);
      healthBarOverlay.fill();
      healthBar = new Rectangle(X_POSITION + OVERLAY, PADDING + OVERLAY, LENGTH - 2 * OVERLAY , HEIGHT - 2 * OVERLAY);
      healthIcon = new Picture(0,PADDING,"resources/images/statusbar/heart.png");
      healthIcon.translate(X_POSITION - OVERLAY - healthIcon.getWidth(),0);
      healthIcon.draw();
    }

    public void refresh(int health) {

      healthBar.delete();
      healthBar = new Rectangle(X_POSITION + OVERLAY, PADDING + OVERLAY, health * (LENGTH - 2 * OVERLAY) / 100 , HEIGHT - 2 * OVERLAY);
      healthBar.setColor(Color.GREEN);
      healthBar.fill();
    }

    public void delete() {
      healthBar.delete();
      healthIcon.delete();
      healthBarOverlay.delete();
      timer.delete();
    }

    public static void startTimer() {
      startTime = System.currentTimeMillis();
    }

    public static void calculateTime() {
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedDisplay = elapsedTime / 1000;
        secondsDisplay = elapsedDisplay % 60;
    }

    public static void displayTime() {
        timer.delete();
        timer = new Text(100, 50, "TIME WITHOUT COVID-19:      " + secondsDisplay);
        timer.setColor(Color.BLACK);
        timer.grow(20, 20);
        timer.draw();
    }

}



