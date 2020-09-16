package ui;

import processing.core.PApplet;
import processing.sound.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * Represents the main JFrame with a container for the required PApplet object the sound library depends on.
 */
public class MainFrame extends JFrame {
    private static MainFrame instance;
    static Dimension SIZE = new Dimension(1366,719);
    public static final PApplet EXTRA_OVERHEAD = new PApplet();
    static final Sound SOUND = new Sound(EXTRA_OVERHEAD);
    static {
        System.out.println("The extra overhead's sketch path is: " + EXTRA_OVERHEAD.sketchPath());
    }

    /**
     * Restarts the program by ending the previous MainFrame and recreating one
     */
    static void restartProgram() {
        if (instance != null) {
            instance.dispatchEvent(new WindowEvent(instance, WindowEvent.WINDOW_CLOSING));
            instance.dispose();

            instance = null;
        }
        getInstance();
    }

    /**
     * Returns the singleton MainFrame object
     * @return singleton instance
     */
    static MainFrame getInstance() {
        if (instance == null) instance = new MainFrame();
        return instance;
    }
    private MainPanel mp;

    /**
     * Initializes the main JFrame with the main panel
     */
    private MainFrame() {
        super("Soundboard");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(false);
        setBackground(Color.WHITE);
        setPreferredSize(SIZE);
        setSize(SIZE);
        mp = new MainPanel();
        add(mp);
        setVisible(true);
        Timer timer = new Timer(500, ae -> {
            mp.updateButtons();
            repaint();
        });
        timer.start();
    }

    /**
     * Stops all active sounds from playing.
     */
    void stopAllSounds() {
        mp.stopAllSounds();
    }
}
