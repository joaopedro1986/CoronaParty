package felinux;

import felinux.gameobjects.objects.AlcoholGel;
import felinux.gameobjects.objects.ExitDoor;
import felinux.gameobjects.objects.Mask;
import felinux.gameobjects.people.Person;
import felinux.gameobjects.people.Player;
import felinux.gameobjects.StatusBar;
import felinux.sound.GameSound;

public class ProximityDetector {

    private Person[] bots;
    StatusBar statusBar;

    //constructor that receives an array of Person as parameter
    public ProximityDetector(Person[] bots) {

        this.bots = bots;

    }

    //default constructor
    public ProximityDetector() {

    }

    //check if player is near a bot
    public void checkPlayerProximity(Player player) {

        for (Person bot : bots) {

            if (!player.getPos().isNear(bot.getPos())) {
                bot.ableToInfect();
            }

            // if player is near a bot and the bot is infected and the bot can infect
            if (player.getPos().isNear(bot.getPos()) && bot.isInfected() && bot.getCanInfect()) {

                if (player.getMask()) {
                    player.withoutMask();
                    bot.unAbleToInfect();
                    break;
                }


                if (Math.random() < 0.5) {
                    GameSound.playSound(GameSound.SoundEffect.SNEEZE_1, 10, 1);

                } else {
                    GameSound.playSound(GameSound.SoundEffect.SNEEZE_2, 10, 1);

                }
                player.setHealth(-Main.INFECTION_DAMAGE);
                bot.unAbleToInfect();

            }

        }

    }

    // check if two bots are near each other
    public void checkBotProximity(Person currentBot) {

        for (Person bot : bots) {

            if (bot == currentBot) {
                continue;
            }

            // if two bots are near and one of them is infected
            if (currentBot.getPos().isNear(bot.getPos()) && (currentBot.isInfected() || bot.isInfected())) {

                bot.setInfected();
                currentBot.setInfected();

            }

        }

    }

    //check if the player picks the mask
    public void checkPlayerPickMask(Player player, Mask mask) {

        //if they are in the same position
        if (player.getPos().samePosition(mask.getPos())) {
            if (!mask.hasUsedMask()) {
                mask.use(player);
                mask.getSprite().delete();
                mask.isUseMask();
                GameSound.playSound(GameSound.SoundEffect.PICKMASK, 10, 1);

            }
        }

    }

    //check if the player picks the alcohol gel
    public void checkPlayerPickAlcoholGel(Player player, AlcoholGel alcoholGel) {

        //if they are in the same position
        if (player.getPos().samePosition(alcoholGel.getPos())) {
            if (!alcoholGel.hasUsedAlcohol()) {
                alcoholGel.disinfect(player);
                alcoholGel.getSprite().delete();
                alcoholGel.isUsedAlcohol();
                GameSound.playSound(GameSound.SoundEffect.PICKALCOHOLGEL, 10, 1);

            }

        }

    }

    //check if the player gets to the exit door
    public void checkPlayerExit(Player player, ExitDoor exit) {

        //if they are in the same position
        if (player.getPos().samePosition(exit.getPos())) {
            exit.use(player);
            exit.getSprite().delete();
            player.setExit(true);

        }


    }
}
