package org.fventura.whattheflip.scenes;

import javafx.scene.Scene;
import org.fventura.whattheflip.panes.IntroPane;

/**
 * @author Franky Ventura
 * @studentID 0816694
 * @date June 13th 2025
 *
 * This class creates the scene that shows the intro pane (main menu)
 */


public class IntroScene extends Scene {
    public IntroScene() {
        // Calls the Scene constructor with IntroPane as the root node, and sets the scene size to 1024x768
        super(new IntroPane(), 1024, 768);
    }
}
