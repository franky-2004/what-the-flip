package org.fventura.whattheflip.scenes;

import javafx.scene.Scene;
import org.fventura.whattheflip.panes.IntroPane;

public class IntroScene extends Scene {
    public IntroScene() {
        super(new IntroPane(), 1024, 768);
    }
}
