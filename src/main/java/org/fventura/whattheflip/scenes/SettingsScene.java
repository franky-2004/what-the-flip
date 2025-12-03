package org.fventura.whattheflip.scenes;

import javafx.scene.Scene;
import org.fventura.whattheflip.panes.SettingsPane;

/**
 * @author Sean Manser
 * @studentID 0520988
 * @date June 13th 2025
 *
 * This class creates the scene that shows the game's settings
 */


public class SettingsScene extends Scene {
    public SettingsScene (){
        // Calls the Scene constructor with SettingsPane as the root node, and sets the scene size to 1024x768
        super(new SettingsPane(), 1024, 768);
    }
}
