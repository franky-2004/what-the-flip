package org.fventura.whattheflip.scenes;

import javafx.scene.Scene;
import org.fventura.whattheflip.panes.SettingsPane;

public class SettingsScene extends Scene {
    public SettingsScene (){
        super(new SettingsPane(), 1024, 768);
    }
}
