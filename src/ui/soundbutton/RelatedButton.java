package ui.soundbutton;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Represents an abstract button with label, and SoundFileButton caller
 */
abstract class RelatedButton extends JButton implements ActionListener {
    SoundFileButton caller;

    /**
     * Initializes the RelatedButton with given SoundFileButton caller and label
     * @param caller SoundFileButton object containing this button
     * @param name label of button
     */
    RelatedButton(SoundFileButton caller, String name) {
        super(name);
        this.caller = caller;
        addActionListener(this);
        setFocusable(false);
    }
}
