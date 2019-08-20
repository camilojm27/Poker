/*
 * INTEGRANTES:
 * CAMILO JOSE MEZU MINA = 1824313
 * SANTIAGO MARTINEZ MESA = 1823107
 */
package poker;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

// TODO: Auto-generated Javadoc
/**
 * The Class Sonidos.
 */
public class Sonidos {

    /** The url. */
    private URL url;
    
    /** The clip. */
    private Clip clip;
    
    /** The first song. */
    public static String secondSong = "Music/Psycho (8 bits).wav", firstSong = "Music/Supermassive black hole (8 bits fusion).wav";
    
    /** The audio in. */
    private AudioInputStream audioIn;
    
    /**
     * Instantiates a new sonidos.
     *
     * @param relativePath the relative path
     */
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

    /**
     * Stop.
     */
    public void stop(){
        if (clip!= null & clip.isRunning()){

            clip.stop();
            clip.close();
        }
    }


    /**
     * Start.
     */
    public  void start(){

        clip.start();
    }

    }

