package felinux.grid;

import felinux.Game;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class GridMenu extends Grid implements KeyboardHandler {

    private Keyboard keyboard;
    private Game game;
    private boolean pressSpace;

    public GridMenu(  ) {
        super("resources/images/backgrounds/menu.jpg", 10);
        keyboard = new Keyboard(this);
        pressSpace = true;

        initKeyboard();

    }

    public void initKeyboard() {

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(space);
    }

    @Override
    public void keyPressed ( KeyboardEvent keyboardEvent ) {

        if (pressSpace) {
            switch (keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_SPACE:
                    game = new Game();
                    game.start();
                    break;
            }
            pressSpace = false;
        }


    }

    @Override
    public void keyReleased ( KeyboardEvent keyboardEvent ) {

    }
}
