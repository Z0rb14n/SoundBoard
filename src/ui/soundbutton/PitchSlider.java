package ui.soundbutton;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Represents the pitch slider that changes the pitch of the sound button
 */
class PitchSlider extends JSlider implements ChangeListener {
    private SoundFileButton caller;
    private static final int MIN = SoundButton.MIN_SLIDER_PITCH;
    private static final int MAX = SoundButton.MAX_SLIDER_PITCH;
    private static final int DEFAULT = SoundButton.NORM_SLIDER_PITCH;

    /**
     * Initializes the PitchSlider with given sound button whose pitch can be changed
     * @param sb sound button in question
     */
    PitchSlider(SoundFileButton sb) {
        super(JSlider.VERTICAL,MIN,MAX, DEFAULT);
        caller = sb;
        setFocusable(false);
        addChangeListener(this);
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        if (!getValueIsAdjusting()) {
            caller.setPitch(getValue());
        }
    }

    /**
     * Resets the slider to the default pitch (1, DEFAULT)
     */
    void reset() {
        setValue(DEFAULT);
    }
}
