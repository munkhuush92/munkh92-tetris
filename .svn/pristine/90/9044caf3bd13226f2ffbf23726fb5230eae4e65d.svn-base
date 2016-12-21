/*
 * TCSS 305 - Project Tetris
 * Sounds of the game.
 */
package sound;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 *  Implements the sound effects of Tetris. It uses Clip to play the sound.
 * 
 * @author Munkhbayar Ganbold
 * @version Autumn 2015
 */
public class TetrisMusic implements Observer, PropertyChangeListener {
   
    /** A Clip for background music.  */
    private final Clip myBackgroundClip;
    
    /** A Clip for crash(Hard drop) sound effect.  */
    private final Clip myCrashClip;
    
    /** A Clip for next Level sound effect.  */
    private final Clip myNextLevelClip;
    
    /** A Clip for game over sound effect.  */
    private final Clip myGameOverClip;
    
    /** A Clip for pause sound effect.  */
    private final Clip myPauseClip;
    
    /** Integer that keeps track of which level the user is playing on. */
    private int myLevelCounter;

    /** A Boolean value that keeps track of whether game is over or not.    */
    private boolean myIsMyGameOver;
    
    /**
     * 
     * @throws LineUnavailableException for input
     */
    public TetrisMusic() throws LineUnavailableException {
        
        myBackgroundClip = AudioSystem.getClip();
        myPauseClip =  AudioSystem.getClip();
        myNextLevelClip =  AudioSystem.getClip();
        myCrashClip =  AudioSystem.getClip();
        myGameOverClip =  AudioSystem.getClip();
        myLevelCounter = 0;
        constructInputStream();
        
    }
    /**
     * Loads the sounds in the project folder named audios, and
     * Implements the clips with these audios.
     */
    private void constructInputStream() {
        try {
        // Open an audio input stream.
            final File crashSound = new File("audios/crash.wav");
            final File gameOverSound = new File("audios/gameover.wav");
            final File nextLevelSound = new File("audios/next.wav");
            final File backgroundSound = new File("audios/level1.wav");
            final File pauseSound = new File("audios/pause.wav");
            final AudioInputStream backgroundAudioIn = 
                            AudioSystem.getAudioInputStream(backgroundSound);
            final AudioInputStream hardDropAudioIn = 
                            AudioSystem.getAudioInputStream(crashSound);
            final AudioInputStream endingAudioIn = 
                            AudioSystem.getAudioInputStream(gameOverSound);
            final AudioInputStream audioPause = 
                         AudioSystem.getAudioInputStream(pauseSound);
         
            final AudioInputStream audioInNext = 
                            AudioSystem.getAudioInputStream(nextLevelSound);
            myBackgroundClip.open(backgroundAudioIn);
            myGameOverClip.open(endingAudioIn);
            myPauseClip.open(audioPause);
            myCrashClip.open(hardDropAudioIn);
            myNextLevelClip.open(audioInNext);
        } catch (final UnsupportedAudioFileException unsuppExp) {
            unsuppExp.printStackTrace();
        } catch (final IOException ioExp) {
            ioExp.printStackTrace();
        } catch (final LineUnavailableException lineExp) {
            lineExp.printStackTrace();
        }
    }

    @Override
    public void update(final Observable theObservable, final Object theArg) {
        if ((theArg instanceof Integer) 
                            && ((Integer) theArg > 0 && !myIsMyGameOver) 
                            && (myLevelCounter < (Integer) theArg)) {
            myNextLevelClip.setFramePosition(0);
            myNextLevelClip.start();
            myLevelCounter = (Integer) theArg;
        }
        
        if (theArg instanceof String) {
            if ("Sound Off".equals(theArg)) {
                myBackgroundClip.stop();
                myIsMyGameOver = true;
            } else if ("Sound On".equals(theArg)) {
                myIsMyGameOver = false;
                myBackgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            
            decideSound(theArg);
      
        }  
    }
    
    /**
     *  Helper method for observer's update to reduce CC value.
     * @param theArg Argument that passed from the update method
     */
    private void decideSound(final Object theArg) {
        if ("Open the sounds".equals(theArg)) {
            myIsMyGameOver = false;
            myBackgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    
        if ("Pause".equals(theArg) && !myIsMyGameOver) {
            myBackgroundClip.stop();
            myPauseClip.setFramePosition(0);
            myPauseClip.start();
        } else if ("Play".equals(theArg) && !myIsMyGameOver) {
            myBackgroundClip.start();
        }
    
        if ("Game Over".equals(theArg)) {
            myIsMyGameOver = true;
            myBackgroundClip.stop();
            myGameOverClip.setFramePosition(0);
            myGameOverClip.start();
        }   
    }
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if ("Hard drop pressed".equals(theEvt.getPropertyName()) && !myIsMyGameOver) {
            myCrashClip.setFramePosition(0);
            myCrashClip.start();
        }
    }
}