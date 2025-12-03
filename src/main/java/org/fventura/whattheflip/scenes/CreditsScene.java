package org.fventura.whattheflip.scenes;
import javafx.scene.Scene;
import org.fventura.whattheflip.panes.CreditsPane;

/**
 * @author Sean Manser
 * @studentID 0520988
 * @date June 13th 2025
 *
 * This class creates the scene that shows the game's credits
 */


public class CreditsScene extends Scene {
    public CreditsScene(){
        // Calls the Scene constructor with CreditsPane as the root node, and sets the scene size to 1024x768
        super(new CreditsPane(), 1024, 768);
    }
}
