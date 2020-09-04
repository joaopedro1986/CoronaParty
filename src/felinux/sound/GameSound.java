package felinux.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.net.URL;

public class GameSound extends Thread {

    @Override
    public void run() {
        playSoundLoop(SoundEffect.BACKGROUNDMUSIC, 10, 0.2);
    }

    public static void playSound(SoundEffect soundEffect, int durationInMiliseconds, double gain) {

        String path = soundEffect.soundPath;
        URL soundURL = GameSound.class.getResource(path);
        AudioInputStream inputStream = null;
        File musicLocation = null;

        try {

            if (soundURL == null) {
                path = path.substring(1);
                musicLocation = new File(path);
                soundURL = musicLocation.toURI().toURL(); //if executing on intellij
            }

            inputStream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();

            //Control volume
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);

            Thread.sleep(durationInMiliseconds);


        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void playSoundLoop(SoundEffect soundEffect, int durationInMiliseconds, double gain) {

        String path = soundEffect.soundPath;
        URL soundURL = GameSound.class.getResource(path);
        AudioInputStream audioInput = null;
        File musicLocation = null;

        try {

            if (soundURL == null) {
                path = path.substring(1);
                musicLocation = new File(path);
                soundURL = musicLocation.toURI().toURL(); //if executing on intellij
            }


                audioInput = AudioSystem.getAudioInputStream(soundURL);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

                //Control volume
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);

                clip.loop(Clip.LOOP_CONTINUOUSLY);
                Thread.sleep(10000);



        } catch (Exception e) {
            e.getMessage();
        }
    }

    public enum SoundEffect {

        BACKGROUNDMUSIC("/resources/sound/elevatorBackground.wav"),
        PICKMASK("/resources/sound/pickMask.wav"),
        PICKALCOHOLGEL("/resources/sound/pickAlcoholGel.wav"),
        SNEEZE_1("/resources/sound/sneeze.wav"),
        SNEEZE_2("/resources/sound/sneeze2.wav"),
        GAMEOVER("/resources/sound/coffinDance.wav"),
        WINGAME("/resources/sound/wingame.wav");

        public String soundPath;

        SoundEffect(String soundPath) {
            this.soundPath = soundPath;
        }

    }


}
