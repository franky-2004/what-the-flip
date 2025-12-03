package org.fventura.whattheflip.panes;

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.fventura.whattheflip.FontManager;
import org.fventura.whattheflip.WhatTheFlip;
import org.fventura.whattheflip.scenes.CreditsScene;
import org.fventura.whattheflip.scenes.GameScene;
import org.fventura.whattheflip.scenes.SettingsScene;
import static org.fventura.whattheflip.WhatTheFlip.audioPlayer;

/**
 * @author Sean Manser & Franky Ventura
 * @studentID 0520988 & 0816694
 * @date June 13th 2025
 *
 * This class creates the scene that shows the intro pane
 * This pane acts as a main menu, and has animations for when you first start up the game (as well a general animation for the title)
 */

public class IntroPane extends BorderPane {
    public IntroPane() {
        // Title of the game
        Text gameTitle = new Text("What The Flip?");
        gameTitle.setFont(FontManager.titleFont);
        gameTitle.setFill(Color.DODGERBLUE);

        // Play intro background music
        WhatTheFlip.audioPlayer.playIntroScreenAudio();

        // Main menu buttons
        Button playButton = new Button("Play");
        playButton.setFont(FontManager.textFont);
        playButton.setPrefWidth(250);
        playButton.setPrefHeight(50);

        Button settingsButton = new Button("Settings");
        settingsButton.setFont(FontManager.textFont);
        settingsButton.setPrefWidth(250);
        settingsButton.setPrefHeight(50);

        Button creditButton = new Button("Credits");
        creditButton.setFont(FontManager.textFont);
        creditButton.setPrefWidth(250);
        creditButton.setPrefHeight(50);

        Button exitButton = new Button("Exit");
        exitButton.setFont(FontManager.textFont);
        exitButton.setPrefWidth(250);
        exitButton.setPrefHeight(50);

        // Button actions
        playButton.setOnAction(actionEvent -> {
            audioPlayer.playButtonAudio();
            WhatTheFlip.mainStage.setScene(new GameScene());
        });

        settingsButton.setOnAction(e -> {
            audioPlayer.playOpenMenuAudio();
            WhatTheFlip.mainStage.setScene(new SettingsScene());
        });

        creditButton.setOnAction(e -> {
            WhatTheFlip.mainStage.setScene(new CreditsScene());
        });

        exitButton.setOnAction(e -> {
            WhatTheFlip.mainStage.close(); // Exit the game
        });


        // VBox layout for vertical stacking of title + buttons
        VBox menuItems = new VBox(gameTitle, playButton, settingsButton, creditButton, exitButton);
        this.setCenter(menuItems);
        menuItems.setAlignment(Pos.CENTER);
        menuItems.setSpacing(15); // Space between buttons


        // Animation section
        Button[] buttons = {playButton, settingsButton, creditButton, exitButton}; // This is for button fade in animations later down

        // Pulse effect for title (loops infinitely)
        ScaleTransition pulse = new ScaleTransition(Duration.seconds(1), gameTitle);
        pulse.setFromX(1.0);
        pulse.setFromY(1.0);
        pulse.setToX(1.1);
        pulse.setToY(1.1);
        pulse.setCycleCount(ScaleTransition.INDEFINITE);
        pulse.setAutoReverse(true);
        pulse.play();

        // Startup intro animations
        if (!WhatTheFlip.hasPlayedIntro) {
            WhatTheFlip.hasPlayedIntro = true;

            // Initial title positioning (falls into place)
            gameTitle.setTranslateY(-200);
            gameTitle.setScaleX(1.0);
            gameTitle.setScaleY(1.0);

            // Button fade-in setup
            for (Button button : buttons) button.setOpacity(0);

            // Drop into place
            TranslateTransition drop = new TranslateTransition(Duration.seconds(0.6), gameTitle);
            drop.setToY(0);

            // Grow slightly
            ScaleTransition grow = new ScaleTransition(Duration.seconds(0.5), gameTitle);
            grow.setToY(1.3);
            grow.setToX(1.3);

            // Shrink back to normal
            ScaleTransition shrink = new ScaleTransition(Duration.seconds(0.5), gameTitle);
            shrink.setToX(1.0);
            shrink.setToY(1.0);

            // After shrinking, play the pulse animation, and fade the buttons
            shrink.setOnFinished(e -> {
                pulse.play();

                // Fade in buttons after pulse starts
                Duration delay = Duration.ZERO;
                for (Button button : buttons) {
                    FadeTransition fade = new FadeTransition(Duration.seconds(0.5), button);
                    fade.setFromValue(0);
                    fade.setToValue(1);
                    fade.setDelay(delay);
                    fade.play();
                    delay = delay.add(Duration.seconds(0.2));
                }
            });

            // Play drop > grow > shrink in sequence
            SequentialTransition introAnimation = new SequentialTransition(drop, grow, shrink);
            introAnimation.play();
        } else {
            // Skip animations and show everything immediately
            gameTitle.setTranslateY(0);
            gameTitle.setScaleX(1.0);
            gameTitle.setScaleY(1.0);
            for (Button button: buttons) button.setOpacity(1);
            pulse.play();
        }
















    }
}
