package poker;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sonidos {

    Sonidos(){

    }

    public static void pop(){
        try {
            URL url = Sonidos.class.getResource("pop.flac");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
