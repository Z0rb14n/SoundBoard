package ui;


import com.jsyn.devices.AudioDeviceFactory;
import com.jsyn.devices.AudioDeviceManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

// TODO FIX SOUND MUTED ON AUDIO DEVICE CHANGE

/**
 * Represents the JPanel for the given output device.
 * Note that the functionality of changing audio devices is disabled,
 * since the sound appears to be muted (permanently) when the audio device changes.
 */
class OutputChoice extends JPanel {
    private static boolean IS_DISABLED = true;
    private static HashMap<String,Integer> outputDevices = new HashMap<>(10);
    private static String defaultOutput = "";
    private OutputsDropdownBox odb = new OutputsDropdownBox();
    private static AudioDeviceManager adm = AudioDeviceFactory.createAudioDeviceManager();
    private OutputChoice instance = this;
    static {
        initializeDevices();
    }

    /**
     * Initializes the JPanel with Refresh button/label and the drop down box (which is disabled)
     */
    OutputChoice() {
        super();
        add(new RefreshButton());
        add(new JLabel("Output Device:"));
        add(odb);
    }

    /**
     * Represents the refresh button that refreshes the list of audio devices
     */
    private class RefreshButton extends JButton implements ActionListener {
        /**
         * Initializes the refresh button with given label and ActionListener
         */
        RefreshButton() {
            super("Refresh Audio Devices");
            setFocusable(false);
            /*
            if (IS_DISABLED) {
                setEnabled(false);
                setToolTipText("Disabled until output device changing works.");
            }
            */
            addActionListener(this);
        }

        /**
         * Clears contents of drop down box and repaints the panel on mouse click
         * @param e action event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            instance.invalidate();
            instance.remove(odb);
            initializeDevices();
            odb = new OutputsDropdownBox();
            instance.add(odb);
            System.gc();
            instance.validate();
            instance.repaint();
        }
    }

    /**
     * Represents the dropdown box of the list of audio devices.
     *
     * Note that the functionality of changing audio devices is disabled,
     * since the sound appears to be muted (permanently) when the audio device changes.
     */
    private class OutputsDropdownBox extends JComboBox<String> implements ItemListener {
        /**
         * Initializes the dropdown box with given contents and disables it.
         */
        OutputsDropdownBox() {
            super(outputDevices.keySet().toArray(new String[]{}));
            setSelectedItem(defaultOutput);
            setEditable(false);
            if (IS_DISABLED) {
                setEnabled(false);
                setToolTipText("Disabled until output device changing works.");
            } else addItemListener(this);
            setFocusable(false);
        }

        /**
         * Changes the audio device when a different item is selected.
         * @param e itemEvent to get new item selected
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            String item = (String) e.getItem();
            MainFrame.getInstance().stopAllSounds();
            MainFrame.SOUND.outputDevice(outputDevices.get(item));
            System.out.println("Changed output device to: " + item + ", index: " + outputDevices.get(item));
        }
    }

    /**
     * Re-initializes the list of output devices.
     */
    private static void initializeDevices() {
        outputDevices.clear();
        int numDevices = adm.getDeviceCount();
        for (int i = 0; i < numDevices; i++) {
            String deviceName = adm.getDeviceName(i);
            int maxInputs = adm.getMaxInputChannels(i);
            int maxOutputs = adm.getMaxOutputChannels(i);
            String label = deviceName + " " + "(" + maxInputs + " in " + maxOutputs + " out)";
            System.out.println(label);
            boolean isDefaultOutput = (i == adm.getDefaultOutputDeviceID());
            if (isDefaultOutput) defaultOutput = label;
            if (maxOutputs > 0) {
                outputDevices.put(label,i);
            }
        }
    }
}
