package org.fventura.whattheflip;

import javafx.application.Application;
import javafx.stage.Stage;
import org.fventura.whattheflip.scenes.IntroScene;

/**
 * @author Franky Ventura
 * @studentID 0816694
 * @date June 13th 2025
 *
 * This class launches the game and stores the shared audio player and main stage for global use
 */

public class WhatTheFlip extends Application {
    // Public access so all panes and scenes can access and update the main stage
    public static Stage mainStage;

    // Shared audio player for the whole game (avoids stacking / overlapping audio issues)
    public static AudioPlayer audioPlayer = new AudioPlayer();

    // Store the selected theme (default: fish)
    public static String selectedTheme = "fish";

    // This is a boolean to do the intro animations only if the game has just been booted up
    public static boolean hasPlayedIntro = false;


    @Override
    public void start(Stage stage) throws Exception {
        // Load all the fonts
        FontManager.loadFonts();

        mainStage = stage; // Saves the stage for use in other classes
        mainStage.setTitle("What The Flip!"); // Game window title
        mainStage.setScene(new IntroScene()); // Loads the intro screen on start
        mainStage.setResizable(true); // Allows the game to be resized
        mainStage.show(); // Displays the game
    }
    public static void main(String[] args) {
        launch(args);
    }
}
