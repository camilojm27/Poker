package poker;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sonidos {

    private URL url;
    private Clip clip;
    public static String secondSong = "Music/Psycho (8 bits).wav", firstSong = "Music/Supermassive black hole (8 bits fusion).wav";
    private AudioInputStream audioIn;
    Sonidos(String relativePath){
        try {
            // Open an audio input stream.
             url = this.getClass().getClassLoader().getResource(relativePath);
             audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
             clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        clip.stop();
        clip.close();
    }


    public  void start(){

        clip.start();
    }

    }

