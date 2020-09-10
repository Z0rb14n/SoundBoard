package ui.soundbutton;

import java.awt.event.ActionEvent;

/**
 * Represents the reset button of the sound button
 */
class ResetButton extends RelatedButton {
    /**
     * Initializes the reset button with a label and calling sound button.
     * @param sb sound button in question.
     */
    ResetButton(SoundFileButton sb) {
        super(sb,"RESET");
    }

    /**
     * Resets the sound button on mouse click
     * @param e action performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        caller.reset();
    }
}
