package org.fventura.whattheflip.scenes;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import org.fventura.whattheflip.panes.GamePane;

public class GameScene extends Scene {
    public GameScene() {
        super(new GamePane(), 1024, 768);
    }
}
