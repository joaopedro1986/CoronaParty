package felinux.gameobjects.objects;
import felinux.grid.Grid;



import felinux.Main;
import felinux.gameobjects.GameObject;
import felinux.gameobjects.people.Player;
import felinux.grid.GridPosition;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class AlcoholGel extends GameObject {
    //ola
    private Picture sprite;
    private GridPosition pos;
    private boolean usedAlcohol;

    public AlcoholGel(Grid grid, int x, int y) {
        super(grid, "resources/images/objects/AlcoolGel/desinfetante-para-as-maos.png", x, y);
    }


    public void disinfect(Player player) {
        if (!usedAlcohol) {
            player.setHealth(Main.ALCOHOL_HEALTH_BOOST);
        }
    }
    public boolean isUsedAlcohol() {

        return usedAlcohol = true;
    }

    public boolean hasUsedAlcohol() {
        return usedAlcohol;
    }

}
