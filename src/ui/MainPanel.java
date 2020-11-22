package ui;

import ui.soundbutton.SoundButton;
import ui.soundbutton.SoundFileButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Main JPanel for the Modified Soundboard.
 */
class MainPanel extends JPanel {
    private static final ArrayList<SoundButton> buttons = new ArrayList<>(35);
    static {
        buttons.add(new SoundFileButton("Curb Your Enthusiasm.wav", "Curb Your Enthusiasm"));
        buttons.add(new SoundFileButton("Rickroll.wav", "Rickroll"));
        buttons.add(new SoundFileButton("Crickets.wav", "Crickets"));
        buttons.add(new SoundFileButton("Ohno.wav", "Oh no"));
        buttons.add(new SoundFileButton("AlertSoundEffect.wav", "Alert!"));
        buttons.add(new SoundFileButton("Ladies and Gentlemen We got him.wav", "Ladies and Gentlemen, we got him."));
        //
        buttons.add(new SoundFileButton( "1000 Hz.wav", "BLEEP")); // ----- TODO SIN OSC
        buttons.add(new SoundFileButton( "losing_Horn_Sound.wav", "Losing Horn"));
        buttons.add(new SoundFileButton( "Monsters Inc Earrape.wav", "Monster's Inc Theme"));
        buttons.add(new SoundFileButton( "Nope.wav", "Nope"));
        buttons.add(new SoundFileButton( "windows_error.wav", "Windows Error"));
        buttons.add(new SoundFileButton( "Roblox_Death.wav", "Roblox Death Sound"));
        //
        buttons.add(new SoundFileButton( "Terrorists_Win.wav", "Terrorists Win"));
        buttons.add(new SoundFileButton( "EZ.wav", "EZ"));
        buttons.add(new SoundFileButton( "Meow.wav", "Meow"));
        buttons.add(new SoundFileButton( "To be continued.wav", "To be continued..."));
        buttons.add(new SoundFileButton( "AWPSOUND.wav", "AWP"));
        buttons.add(new SoundFileButton( "pew.wav", "pew"));
        buttons.add(new SoundFileButton( "Bruh.wav", "Bruh"));
        buttons.add(new SoundFileButton( "fortnite death sound.wav", "Fortnite Death Sound"));
        //
        buttons.add(new SoundFileButton( "GTA_Death.wav", "GTA Death Sound"));
        buttons.add(new SoundFileButton( "How Could This Happen To Me.wav", "How could this happen to me?"));
        buttons.add(new SoundFileButton( "Sad Violin.wav", "Sad Violin"));
        buttons.add(new SoundFileButton( "FBI Open Up.wav", "FBI OPEN UP"));
        buttons.add(new SoundFileButton( "Minecraft Death Sound.wav", "Minecraft Death Sound"));
        buttons.add(new SoundFileButton( "Mission Failed.wav", "Mission Failed..."));
        buttons.add(new SoundFileButton( "Airhorn.wav", "Airhorn"));
        buttons.add(new SoundFileButton( "CT_Wins.wav", "Counter-terrorists Win"));
        //
        buttons.add(new SoundFileButton( "Enemy Spotted.wav", "Enemy Spotted"));
        buttons.add(new SoundFileButton( "Retard Alert.wav", "Retard Alert"));
        buttons.add(new SoundFileButton( "ItWasAtThisMoment.wav", "It was at this moment he knew..."));
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
