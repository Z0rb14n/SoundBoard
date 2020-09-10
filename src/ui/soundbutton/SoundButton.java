package ui.soundbutton;

import javax.swing.*;

/**
 * Represents an abstract SoundButton
 */
public abstract class SoundButton extends JPanel {
    public static final int MIN_SLIDER_VOL = 0;
    public static final int MAX_SLIDER_VOL = 100;
    public static final int MIN_SLIDER_PITCH = 0;
    public static final int MAX_SLIDER_PITCH = 100;
    public static final int NORM_SLIDER_PITCH = (MIN_SLIDER_PITCH + MAX_SLIDER_PITCH)/2;
    /**
     * Updates this SoundButton.
     */
    public abstract void update();

    /**
     * Determines whether this sound button is playing
     * @return boolean value of whether the sound is playing
     */
    public abstract boolean isPlaying();

    /**
     * Stops the sound from playing (if it is).
     */
    public abstract void stop();

    /**
     * Pauses the sound (if it is playing).
     */
    public abstract void pause();

    /**
     * Resets this given SoundButton object.
     */
    public abstract void reset();

    /**
     * Plays this SoundButton.
     */
    public abstract void play();

    /**
     * Sets the volume of this SoundButton
     * @param volume new volume, (ideally, but optional) in ideal ranges
     */
    public abstract void setVolume(int volume);

    /**
     * Sets the pitch of this SoundButton
     * @param pitch new pitch, (ideally, but optional) in ideal ranges
     */
    public abstract void setPitch(int pitch);
}
