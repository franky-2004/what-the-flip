package org.fventura.whattheflip.panes;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import org.fventura.whattheflip.WhatTheFlip;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.fventura.whattheflip.scenes.GameScene;
import org.fventura.whattheflip.scenes.IntroScene;
import org.fventura.whattheflip.scenes.SettingsScene;

public class SettingsPane extends BorderPane {
    public SettingsPane() {
        // Title of pane
        Text settingsTitle = new Text("Settings");
        settingsTitle.setFont(Font.font("Book Antiqua", 40));

        //Buttons: Mute, Themes?, Fonts?
        Button muteAudio;
        if (WhatTheFlip.audioPlayer.isMuted()) {
            muteAudio = new Button("Unmute Audio");
        } else {
            muteAudio = new Button("Mute Audio");
        }
        muteAudio.setPrefWidth(200);

        Button changeTheme = new Button("Change Theme");
        changeTheme.setPrefWidth(200);


        Button backButton = new Button("Back");
        backButton.setPrefWidth(200);

        // Action Events for buttons

        // This sends you back to the intro screen
        backButton.setOnAction(e->{
            WhatTheFlip.mainStage.setScene(new IntroScene());
        });
        
        muteAudio.setOnAction(e-> {
            WhatTheFlip.audioPlayer.muteAudio();

            // Change button text based on current state
            if (muteAudio.getText().equals("Mute Audio")) {
                muteAudio.setText("Unmute Audio");
            } else {
                muteAudio.setText("Mute Audio");
            }
        });


        // Layout
        VBox settingsVBox = new VBox(settingsTitle, muteAudio, changeTheme, backButton);
        this.setCenter(settingsVBox);
        settingsVBox.setAlignment(Pos.CENTER);
        settingsVBox.setSpacing(15);


        //Animation - translate from bottom?


        // Hover animations for buttons - slight scale increase


    }


}
