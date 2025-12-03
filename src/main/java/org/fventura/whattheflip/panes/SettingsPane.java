package org.fventura.whattheflip.panes;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import org.fventura.whattheflip.FontManager;
import org.fventura.whattheflip.WhatTheFlip;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.fventura.whattheflip.scenes.IntroScene;

/**
 * @author Sean Manser
 * @studentID 0520988
 * @date June 13th 2025
 *
 * This class creates the scene that shows the settings pane
 * Our settings include a mute button, a button that switches between our two themes (Our default fish theme, and our fruit theme),
 * as well as a Back button that brings you back to the intro pane.
 */

public class SettingsPane extends BorderPane {
    public SettingsPane() {
        // Title of pane
        Text settingsTitle = new Text("Settings");
        settingsTitle.setFont(FontManager.titleFont);
        settingsTitle.setTranslateY(-50);
        settingsTitle.setFill(Color.DODGERBLUE);

        // Mute button - label depends on whether audio is currently muted
        Button muteAudio;
        if (WhatTheFlip.audioPlayer.isMuted()) {
            muteAudio = new Button("Unmute Audio");
        } else {
            muteAudio = new Button("Mute Audio");
        }
        muteAudio.setFont(FontManager.textFont);
        muteAudio.setPrefWidth(200);
        muteAudio.setPrefHeight(50);

        // Theme toggle button - updates label based on current theme
        Button themeToggle = new Button();
        themeToggle.setFont(FontManager.textFont);
        themeToggle.setPrefWidth(200);
        themeToggle.setPrefHeight(50);

        if (WhatTheFlip.selectedTheme.equals("fish")) {
            themeToggle.setText("Theme: Fish");
        } else {
            themeToggle.setText("Theme: Fruit");
        }

        // Back button to return to the intro screen
        Button backButton = new Button("Back");
        backButton.setFont(FontManager.textFont);
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(50);

        // Go back to the intro screen
        backButton.setOnAction(e->{
            WhatTheFlip.mainStage.setScene(new IntroScene());
        });

        // Toggle mute state and update the button text
        muteAudio.setOnAction(e-> {
            WhatTheFlip.audioPlayer.muteAudio();

            if (muteAudio.getText().equals("Mute Audio")) {
                muteAudio.setText("Unmute Audio");
            } else {
                muteAudio.setText("Mute Audio");
            }
        });

        // Toggle theme between fish and fruit
        themeToggle.setOnAction(e -> {
            if (WhatTheFlip.selectedTheme.equals("fish")) {
                WhatTheFlip.selectedTheme = "fruit";
                themeToggle.setText("Theme: Fruit");
            } else {
                WhatTheFlip.selectedTheme = "fish";
                themeToggle.setText("Theme: Fish");
            }
        });

        // Layout setup
        VBox settingsVBox = new VBox(settingsTitle, muteAudio, themeToggle, backButton);
        this.setCenter(settingsVBox);
        settingsVBox.setAlignment(Pos.CENTER);
        settingsVBox.setSpacing(15);

    }
}
