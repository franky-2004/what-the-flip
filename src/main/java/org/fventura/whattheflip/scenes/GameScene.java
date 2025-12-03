package org.fventura.whattheflip.scenes;

import javafx.scene.Scene;
import org.fventura.whattheflip.panes.GamePane;

/**
 * @author Franky Ventura
 * @studentID 0816694
 * @date June 13th 2025
 *
 * This class creates the scene that shows the gameplay pane
 */

public class GameScene extends Scene {
    public GameScene() {
        // Calls the Scene constructor with GamePane as the root node, and sets the scene size to 1024x768
        super(new GamePane(), 1024, 768);
    }
}
