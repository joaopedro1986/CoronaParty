package felinux.gameobjects.objects;

import felinux.Main;
import felinux.gameobjects.GameObject;
import felinux.gameobjects.people.Player;
import felinux.grid.GridPosition;
import felinux.grid.Grid;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Mask extends GameObject {

    private Picture sprite;
    private GridPosition pos;
    private boolean useMask;

    public Mask(Grid grid, int x, int y) {
        super(grid,"resources/images/objects/Mask/mascara-medica.png", x, y);
    }

    public void use(Player player){
        if (!useMask) {
            player.withMask();
        }
    }

    public boolean isUseMask() {

        return useMask = true;
    }

    public boolean hasUsedMask() {
        return useMask;
    }

}
