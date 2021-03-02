package ui;

import ui.soundbutton.SoundButton;
import ui.soundbutton.SoundFileButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Main JPanel for the Modified Soundboard.
 */
class MainPanel extends JPanel {
    private static final ArrayList<SoundButton> buttons = new ArrayList<>(35);
    private static final File DATA_PATH = new File("./data/");
    private static final File LABEL_PATH = new File(DATA_PATH.getPath() + "/labels.txt");
    private static final String DELIMITER = ",";

    private static HashMap<String, String> initializeLabelMap() {
        HashMap<String, String> map = new HashMap<>();
        try (Scanner scanner = new Scanner(LABEL_PATH)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] sections = line.split(DELIMITER);
                if (sections.length >= 2) map.put(sections[0], sections[1]);
            }
            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    static {
        final File[] files = DATA_PATH.listFiles();
        if (files != null) {
            final String[] extensions = SoundFileButton.SUPPORTED_EXT;
            HashMap<String, String> labels = initializeLabelMap();
            for (File file : files) {
                if (!file.isFile()) continue;
                for (String ext : extensions) {
                    String fileName = file.getName();
                    if (fileName.endsWith(ext)) {
                        if (labels != null && labels.containsKey(fileName.trim())) {
                            buttons.add(new SoundFileButton(file.getPath(), labels.get(fileName.trim())));
                            break;
                        } else {
                            buttons.add(new SoundFileButton(file.getPath(), file.getName()));
                        }
                    }
                }
            }
        }
    }

    /**
     * Initializes the main panel with given size and contents.
     */
    MainPanel() {
        setPreferredSize(MainFrame.SIZE);
        setSize(MainFrame.SIZE);
        setLayout(new BorderLayout());
        add(new OutputChoice(),BorderLayout.PAGE_START);
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(3, 0));
        for (SoundButton sb : buttons) {
            jp.add(sb);
        }
        JScrollPane jsp = new JScrollPane(jp);
        add(jsp,BorderLayout.CENTER);
    }

    /**
     * Updates all buttons.
     */
    void updateButtons() {
        for (SoundButton sb : buttons) sb.update();
    }

    /**
     * Stops all currently playing sounds.
     */
    void stopAllSounds() {
        for (SoundButton sb : buttons) sb.stop();
    }
}
