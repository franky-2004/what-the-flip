package org.fventura.whattheflip.panes;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.fventura.whattheflip.WhatTheFlip;
import org.fventura.whattheflip.scenes.CreditsScene;
import org.fventura.whattheflip.scenes.GameScene;
import org.fventura.whattheflip.scenes.SettingsScene;
import static org.fventura.whattheflip.WhatTheFlip.audioPlayer;

public class IntroPane extends BorderPane {
    public IntroPane() {
        // Title
        Text gameTitle = new Text("What The Flip?");
        gameTitle.setFont(Font.font("Book Antiqua", 50));
        gameTitle.setFill(Color.LIGHTBLUE);

        // Play intro screen audio
        WhatTheFlip.audioPlayer.playIntroScreenAudio();

        // Buttons (Play, settings, credit, exit)
        Button playButton = new Button("Play");
        playButton.setPrefWidth(200);

        Button settingsButton = new Button("Settings");
        settingsButton.setPrefWidth(200);

        Button creditButton = new Button("Credits");
        creditButton.setPrefWidth(200);

        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(200);
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
            WhatTheFlip.mainStage.close();
        });


        // Layout
        VBox menuItems = new VBox(gameTitle, playButton, settingsButton, creditButton, exitButton);
        this.setCenter(menuItems);
        menuItems.setAlignment(Pos.CENTER);
        menuItems.setSpacing(15);

        // TODO: Transitions for title, and for buttons
        ScaleTransition increasePlaySize = new ScaleTransition(Duration.millis(100), playButton);
        increasePlaySize.setByX(0.2f);
        increasePlaySize.setByY(0.2f);

        RotateTransition jiggleSettingsButton = new RotateTransition(Duration.millis(100), settingsButton);
//        jiggleSettingsButton.
//        jiggleSettingsButton.

        ScaleTransition increaseCreditSize = new ScaleTransition(Duration.millis(100), creditButton);
        increaseCreditSize.setByX(0.2f);
        increaseCreditSize.setByY(0.2f);

        ScaleTransition increaseExitSize = new ScaleTransition(Duration.millis(100), exitButton);
        increaseExitSize.setByX(0.2f);
        increaseExitSize.setByY(0.2f);



        // setOnMouseEntered effects for buttons
//        playButton.setOnMouseEntered(e -> {
//            increasePlaySize.play();
//        });
//        playButton.setOnMouseEntered(e -> {
//            increaseSettingSize.play();
//        });
//        playButton.setOnMouseEntered(e -> {
//            increaseCreditSize.play();
//        });
//        playButton.setOnMouseEntered(e -> {
//            increaseExitSize.play();
//        });

    }
}
