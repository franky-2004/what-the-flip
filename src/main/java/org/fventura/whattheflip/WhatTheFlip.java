package org.fventura.whattheflip;

import javafx.application.Application;
import javafx.stage.Stage;
import org.fventura.whattheflip.scenes.IntroScene;

public class WhatTheFlip extends Application {
    public static Stage mainStage;

    // Shared audio player for the whole game (to prevent audio bugs)
    public static AudioPlayer audioPlayer = new AudioPlayer();

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        mainStage.setTitle("What The Flip!");
        mainStage.setScene(new IntroScene());
        mainStage.setResizable(true);
        mainStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
