package org.fventura.whattheflip.scenes;
import javafx.scene.Scene;
import org.fventura.whattheflip.panes.CreditsPane;

public class CreditsScene extends Scene {
    public CreditsScene(){
        super(new CreditsPane(), 1024, 768);
    }
}
