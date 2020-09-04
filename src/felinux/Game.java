package felinux;

import felinux.gameobjects.GameObjectFactory;
import felinux.gameobjects.objects.AlcoholGel;
import felinux.gameobjects.objects.ExitDoor;
import felinux.gameobjects.objects.Mask;
import felinux.gameobjects.objects.Virus;
import felinux.gameobjects.people.Person;
import felinux.gameobjects.people.Player;
import felinux.grid.Grid;
import felinux.gameobjects.StatusBar;
import felinux.grid.GridMenu;
import felinux.sound.GameSound;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game extends Thread {

    private Grid grid;
    private StatusBar statusBar;
    private Picture gameOver;
    private Person[] bots;
    private Player player;
    private Mask mask;
    private AlcoholGel alcoholGel;
    private ExitDoor exitDoor;
    private Picture endGame;
    private Virus virus;
    private LevelType level;
    private boolean won;
    private boolean lost;

    private ProximityDetector botProximityDetector;
    private ProximityDetector playerProximityDetector;
    private ProximityDetector maskProximity;
    private ProximityDetector alcoholGelProximity;
    private ProximityDetector exitProximity;

    private GameSound gameSound = new GameSound();

    public void run () {

        init(LevelType.LEVEL1);

        gameSound.start();

        try {
            startGame();
        } catch (InterruptedException e) {
            e.getMessage();
        }

        gameSound.interrupt();

    }

    //creates game objects ant puts them randomly in the grid
    public void init ( LevelType level ) {

        this.level = level;
        won = false;
        lost = false;

        StatusBar.startTimer();

        grid = level.getGrid();
        grid.init();

        statusBar = new StatusBar();
        statusBar.init(grid);

        //create player
        player = new Player(grid, 185, 110);

        //create bots
        bots = new Person[level.getNumberOfBots()];
        for (int i = 0; i < bots.length; i++) {
            bots[i] = GameObjectFactory.getNewPerson(grid);
            bots[0].setInfected();

        }

        //create mask
        if ( level.hasMask() ) {
            mask = new Mask(grid, 120, 580);
            maskProximity = new ProximityDetector();
        } else {
            mask = null;
            maskProximity = null;
        }

        //create alcoholGel
        if ( level.hasAlcoholGel() ) {
            alcoholGel = new AlcoholGel(grid, 1100, 140);
            alcoholGelProximity = new ProximityDetector();
        } else {
            alcoholGel = null;
            alcoholGelProximity = null;
        }

        //create virus
        if ( level.hasVirus() ) {
            virus = new Virus(grid, player.getPos(), 1000, 350);
        } else {
            virus = null;
        }

        //Create exit
        exitDoor = new ExitDoor(grid, 990, 583);

        //instantiate proximity detectors
        botProximityDetector = new ProximityDetector(bots);
        playerProximityDetector = new ProximityDetector(bots);
        exitProximity = new ProximityDetector();

        System.gc();

    }

    //starts the game - bots start to move randomly
    public void startGame () throws InterruptedException {

        while (true) {

            statusBar.refresh(player.getHealth());
            StatusBar.calculateTime();
            StatusBar.displayTime();
            movePersons();

            if ( player.isDead() && !won && !lost ) {

                lost = true;
                statusBar.refresh(player.getHealth());
                gameOver = new Picture(280, 290, "resources/images/statusbar/gameOver.png");
                gameOver.draw();
                GameSound.playSound(GameSound.SoundEffect.GAMEOVER, 5000, 0.5);
                Grid gridMenu = new GridMenu();
                gameOver.delete();
                statusBar.delete();
                gridMenu.init();
                return;

            }  else if ( player.isExit() && !player.isDead() ) {

                if ( level == LevelType.LEVEL1 ) {
                    Thread.sleep(Main.DELAY);
                    init(LevelType.LEVEL2);
                    startGame();

                } else if ( level == LevelType.LEVEL2 ) {
                    Thread.sleep(Main.DELAY);
                    init(LevelType.FINAL_LEVEL);
                    startGame();

                } else if ( level == LevelType.FINAL_LEVEL && !won ) {

                    won = true;
                    endGame = new Picture(200, 100, "resources/images/statusbar/ficarTudoBem.png");
                    endGame.draw();
                    GameSound.playSound(GameSound.SoundEffect.WINGAME, 5000, 1);
                    Grid gridMenu = new GridMenu();
                    endGame.delete();
                    statusBar.delete();
                    gridMenu.init();
                    return;
                }

            }

            Thread.sleep(Main.DELAY);
        }


    }

    //move bots and player and checks for proximity and collision
    public void movePersons () {

        for (Person bot : bots) {

            bot.walk();
            botProximityDetector.checkBotProximity(bot);
            playerProximityDetector.checkPlayerProximity(player);

        }

        if ( mask != null ) {
            maskProximity.checkPlayerPickMask(player, mask);
        }

        if ( alcoholGel != null ) {
            alcoholGelProximity.checkPlayerPickAlcoholGel(player, alcoholGel);
        }

        exitProximity.checkPlayerExit(player, exitDoor);


        if ( virus != null ) {
            virus.walking();
            virus.checkProximity(player);
        }
    }
}
