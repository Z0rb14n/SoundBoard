package ui.soundbutton;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Represents the volume slider for the SoundFileButton
 */
class VolumeSlider extends JSlider implements ChangeListener {
    private SoundFileButton caller;

    /**
     * Initializes the volume slider with default value, min and max value.
     * Note that the default is the max volume
     * @param sb sound button in question
     */
    VolumeSlider(SoundFileButton sb) {
        super(JSlider.VERTICAL,SoundButton.MIN_SLIDER_VOL,SoundButton.MAX_SLIDER_VOL,SoundButton.MAX_SLIDER_VOL);
        caller = sb;
        addChangeListener(this);
        setFocusable(false);
    }

    /**
     * Changes the volume of the sound button when the state changes (and is not adjusting)
     * @param e change event created on state change
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if (!getValueIsAdjusting()) {
            caller.setVolume(getValue());
        }
    }

    /**
     * Resets the value of the slider (to max volume)
     */
    void reset() {
        setValue(SoundButton.MAX_SLIDER_VOL);
    }
}
