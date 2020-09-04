package felinux.gameobjects.objects;

        import felinux.gameobjects.GameObject;
        import felinux.gameobjects.people.Player;
        import felinux.grid.Grid;
        import felinux.grid.GridPosition;
        import org.academiadecodigo.simplegraphics.pictures.Picture;

public class ExitDoor extends GameObject {

    private GridPosition pos;
    private Picture rainbow;

    public ExitDoor(Grid grid, int x, int y) {
        super(grid,"resources/images/objects/invisibleDOOR.png", x, y);


    }

    public void use(Player player){

        player.exit();
    }

}
