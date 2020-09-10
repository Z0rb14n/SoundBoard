package ui.soundbutton;

import java.awt.event.ActionEvent;

/**
 * Represents the stop button for the sound button
 */
class StopButton extends RelatedButton {
    /**
     * Initializes the stop button with given sound button caller and label
     * @param sb sound button in question
     */
    StopButton(SoundFileButton sb) {
        super(sb,"STOP");
        setEnabled(false);
    }

    /**
     * Stops the sound button from playing on mouse click
     * @param e ActionEvent created on mouse click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        caller.stop();
    }
}
