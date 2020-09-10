package ui.soundbutton;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a container for the Volume slider and the Pitch slider
 */
class VolumePitch extends JPanel {
    private VolumeSlider vs;
    private PitchSlider ps;

    /**
     * Initializes the VolumePitch object with calling SoundFileButton
     * @param sb sound button in question
     */
    VolumePitch(SoundFileButton sb) {
        super();
        setLayout(new GridLayout(1,2));

        JPanel vsPanel = new JPanel();
        vsPanel.setLayout(new BoxLayout(vsPanel,BoxLayout.Y_AXIS));
        JLabel vslabel = new JLabel("Volume");
        vslabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        vslabel.setHorizontalAlignment(JLabel.CENTER);
        vsPanel.add(vslabel);
        vs = new VolumeSlider(sb);
        vsPanel.add(vs);


        JPanel psPanel = new JPanel();
        psPanel.setLayout(new BoxLayout(psPanel,BoxLayout.Y_AXIS));
        JLabel psLabel = new JLabel("Pitch");
        psLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        psLabel.setHorizontalAlignment(JLabel.CENTER);
        psPanel.add(psLabel);
        ps = new PitchSlider(sb);
        psPanel.add(ps);

        add(vsPanel);
        add(psPanel);
    }

    /**
     * Resets both sliders to default values.
     */
    void reset() {
        ps.reset();
        vs.reset();
    }
}
