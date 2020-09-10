package ui.soundbutton;

import java.awt.event.ActionEvent;

/**
 * Represents the play button for the sound button
 */
class PlayButton extends RelatedButton {
    /**
     * Initializes play button with calling sound button
     * @param sb sound button in question
     */
    PlayButton(SoundFileButton sb) {
        super(sb,"PLAY");
    }

    /**
     * On mouse click, play the sound on the sound button
     * @param e action event given
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        caller.play();
    }
}
