package ui.soundbutton;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the Stop/Pause/Reset buttons container.
 */
class StopPauseReset extends JPanel {
    private SoundFileButton caller;
    private StopButton stopButton;
    private PauseButton pauseButton;
    private ResetButton resetButton;

    /**
     * Initializes the StopPauseReset object with given buttons and soundButton caller
     * @param sb SoundFileButton object in question
     */
    StopPauseReset(SoundFileButton sb) {
        super();
        caller = sb;
        setLayout(new GridLayout(3,1));
        stopButton = new StopButton(sb);
        add(stopButton);
        pauseButton = new PauseButton(sb);
        add(pauseButton);
        resetButton = new ResetButton(sb);
        add(resetButton);
    }

    /**
     * Updates all buttons.
     */
    void updateButtons() {
        stopButton.setEnabled(caller.isPlaying());
        pauseButton.setEnabled(caller.isPlaying());
        // note that the reset button is always enabled.
        repaint();
    }
}
