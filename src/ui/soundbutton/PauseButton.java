package ui.soundbutton;

import java.awt.event.ActionEvent;

/**
 * Represents the pause button for the SoundFileButton class.
 */
class PauseButton extends RelatedButton {
    /**
     * Initializes the PauseButton instance with given SoundFileButton caller
     * @param sb SoundFileButton instance to pause on mouse click
     */
    PauseButton(SoundFileButton sb) {
        super(sb,"PAUSE");
        setEnabled(false);
    }

    /**
     * Pauses the sound button on mouse click.
     * @param e ActionEvent created by java
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        caller.pause();
    }
}
