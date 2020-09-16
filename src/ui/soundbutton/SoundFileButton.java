package ui.soundbutton;

import processing.sound.SoundFile;
import ui.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Represents an individual sound button
 */
public class SoundFileButton extends SoundButton {
    private static final float VOLUME_SCALING = 100f; // volume of range 0-100
    private boolean isPaused = false;
    private String filePath;
    private SoundFile soundFile;
    private long millisTimeStart = System.currentTimeMillis();
    private long pauseTime = 0;
    private float pitch = 1; // pitch of range 0.5 - 2
    private float volume = VOLUME_SCALING;
    private PlayButton playButton = new PlayButton(this);
    private VolumePitch volumePitch = new VolumePitch(this);
    private StopPauseReset spr = new StopPauseReset(this);
    private static final float SECONDS_TO_MILLIS = 1000f;

    /**
     * Initializes SoundFileButton with given file name ("/path/to/jar/data/fileName") and sound button title
     *
     * @param fileName file name of file
     * @param title    title of the sound button
     */
    public SoundFileButton(String fileName, String title) {
        this.filePath = fileName;
        setLayout(new BorderLayout());
        JLabel label = new JLabel(title);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(label, BorderLayout.PAGE_START);
        add(playButton, BorderLayout.CENTER);
        add(volumePitch, BorderLayout.LINE_END);
        add(spr, BorderLayout.PAGE_END);
    }

    /**
     * Updates components (disables them if need be) and unloads sound file from memory if finished playing
     */
    @Override
    public void update() {
        // WARNING: POTENTIAL OVERFLOW IF System.currentTimeMillis() OVERFLOWS
        if (soundFile != null && (System.currentTimeMillis() > millisTimeStart + (soundFile.duration() * SECONDS_TO_MILLIS) || !soundFile.isPlaying())) {
            soundFile.stop();
            millisTimeStart = -1;
            unloadSFile();
        }
        spr.updateButtons();
        playButton.setEnabled(!isPlaying());
        repaint();
    }

    /**
     * Returns whether the sound file is playing
     *
     * @return boolean of whether the sound is playing
     */
    @Override
    public boolean isPlaying() {
        return soundFile != null && soundFile.isPlaying();
    }

    /**
     * Loads the sound file into memory
     */
    private void loadSFile() {
        soundFile = new SoundFile(MainFrame.EXTRA_OVERHEAD, filePath, false);
    }

    /**
     * Unloads the soundfile from memory and stops it if need be.
     */
    private void unloadSFile() {
        if (soundFile != null) {
            soundFile.stop();
            soundFile.removeFromCache();
            soundFile = null;
        }
    }

    /**
     * Resets the sound button to default values
     */
    @Override
    public void reset() {
        volume = VOLUME_SCALING;
        pitch = 1;
        volumePitch.reset();
        if (soundFile == null) return;
        if (soundFile.isPlaying()) soundFile.stop();
        isPaused = false;
        unloadSFile();
        update();
    }

    /**
     * Stops the sound file from playing (if it is)
     */
    public void stop() {
        if (soundFile == null) return;
        if (soundFile.isPlaying()) soundFile.stop();
        unloadSFile();
        isPaused = false;
        update();
    }

    /**
     * Pauses the sound file if it is playing.
     * Note that this actually unloads the sound file from memory and records its position,
     * and when the sound is resumed, restores its original position.
     */
    @Override
    public void pause() {
        if (soundFile == null) return;
        if (soundFile.isPlaying()) {
            pauseTime = (long) Math.ceil(soundFile.position() * millisTimeStart);
            soundFile.stop();
            unloadSFile();
            isPaused = true;
        }
        update();
    }

    /**
     * Plays the sound file (if it is not playing),
     * and loads the sound file into memory (if required).
     */
    @Override
    public void play() {
        if (soundFile == null) loadSFile();
        if (!soundFile.isPlaying()) {
            soundFile.rate(pitch);
            soundFile.amp(volume / VOLUME_SCALING);
            soundFile.play();
            millisTimeStart = System.nanoTime();
            if (!isPaused) {
                soundFile.jump(0);
            } else {
                soundFile.jump(pauseTime / millisTimeStart);
                millisTimeStart -= pauseTime;
            }
        }
        update();
    }

    /**
     * Converts the slider value to a volume from 0 - VOLUME_SCALING
     * @param value slider value in ranges of SoundButton (ideally)
     * @return float value of volume
     */
    private static float valueToVolume(int value) {
        if (value >= SoundButton.MAX_SLIDER_VOL) return VOLUME_SCALING;
        else if (value <= SoundButton.MIN_SLIDER_VOL) return 0;
        return VOLUME_SCALING * (value - MIN_SLIDER_VOL) / (MAX_SLIDER_VOL-MIN_SLIDER_VOL);
    }

    /**
     * Sets the volume of the sound file.
     *
     * @param newVolume new volume (ideally in range 0-100)
     */
    @Override
    public void setVolume(int newVolume) {
        volume = valueToVolume(newVolume);
        if (soundFile == null) return;
        boolean isPlaying = soundFile.isPlaying();
        if (isPlaying) soundFile.pause();
        soundFile.amp(volume / VOLUME_SCALING);
        if (isPlaying) soundFile.play();
        update();
    }

    /**
     * Converts the integer value from the slider to a float pitch value (from 0.5-2)
     * Note that the pitch ranges from 0.5-2, but the int value scales from MIN-MAX linearly.
     * Instead, the range is split from 0.5 - 1 with MIN-AVG (scaling linearly),
     * and scales linearly for 1-2 for AVG-MAX. Note that the scaling factor may be different.
     *
     * @param value integer value of slider.
     * @return float pitch value.
     */
    private static float valueToPitch(int value) {
        if (value == SoundButton.NORM_SLIDER_PITCH) return 1;
        if (value < SoundButton.NORM_SLIDER_PITCH) {
            return 1 - (0.5f * (SoundButton.NORM_SLIDER_PITCH - value) /
                    (SoundButton.NORM_SLIDER_PITCH - SoundButton.MIN_SLIDER_PITCH));
        } else {
            return 1 + (1f * (value - SoundButton.NORM_SLIDER_PITCH) /
                    (SoundButton.MAX_SLIDER_PITCH - SoundButton.NORM_SLIDER_PITCH));
        }
    }

    /**
     * Sets the pitch of the sound file
     *
     * @param newPitch new pitch (in range of SoundButton slider pitches ideally)
     */
    @Override
    public void setPitch(int newPitch) {
        pitch = valueToPitch(newPitch);
        if (pitch > 2) pitch = 2;
        else if (pitch < 0.5) pitch = 0.5f;
        if (soundFile == null) return;
        boolean isPlaying = soundFile.isPlaying();
        if (isPlaying) soundFile.pause();
        soundFile.rate(pitch);
        if (isPlaying) soundFile.play();
        update();
    }
}
