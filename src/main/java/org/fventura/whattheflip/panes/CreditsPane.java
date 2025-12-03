package org.fventura.whattheflip.panes;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import org.fventura.whattheflip.FontManager;
import org.fventura.whattheflip.WhatTheFlip;
import org.fventura.whattheflip.scenes.IntroScene;

/**
 * @author Sean Manser
 * @studentID 0520988
 * @date June 13th 2025
 *
 * This class creates the scene that shows the credits pane
 * For credits, we put our game name, group name, author names (our names), and the authors for each one of our resources (Music, SFX, Images, Fonts)
 */


public class CreditsPane extends BorderPane {
    public CreditsPane(){

        // Credits for resources, group members
        Text gameName = new Text("What The Flip");
        gameName.setFont(FontManager.titleFont);
        gameName.setFill(Color.DODGERBLUE);

        Text groupName = new Text("Flip Happens");
        groupName.setFont(FontManager.titleFont);
        groupName.setFill(Color.DODGERBLUE);

        Text authorName = new Text("Sean Manser\n&\nFranky Ventura");
        authorName.setFont(FontManager.authorFont);
        authorName.setFill(Color.DODGERBLUE);
        authorName.setTextAlignment(TextAlignment.CENTER);

        Text resourcesName = new Text("Resources");
        resourcesName.setFont(FontManager.titleFont);
        resourcesName.setFill(Color.DODGERBLUE);

        Text musicResourcesTitle = new Text("Music From:");
        musicResourcesTitle.setFont(FontManager.titleFont);
        musicResourcesTitle.setFill(Color.DODGERBLUE);

        Text musicResources = new Text("DELOSound from Pixabay\n" +
                "music_for_video from Pixabay ");
        musicResources.setFont(FontManager.creditsFont);
        musicResources.setFill(Color.BLACK);

        Text sfxResourcesTitle = new Text("Sound Effects From:");
        sfxResourcesTitle.setFont(FontManager.titleFont);
        sfxResourcesTitle.setFill(Color.DODGERBLUE);

        Text soundEffectsResources = new Text("\n freesound_community from Pixabay\n" +
                "u_8g40a9z0la from Pixabay\n" +
                "floraphonic from Pixabay\n" +
                "CreatorsHome from Pixabay\n" +
                "LIECIO from Pixabay");
        soundEffectsResources.setFont(FontManager.creditsFont);
        soundEffectsResources.setFill(Color.BLACK);

        Text fontName = new Text("Fonts: ");
        fontName.setFont(FontManager.titleFont);
        fontName.setFill(Color.DODGERBLUE);

        Text fontResources = new Text("Dynapuff Font:\nToshi Omagari\nJennifer Daniel" +
                "\n\nRaleway Font:\nMatt McInerney\nPablo Impallari\nRodrigo Fuenzalida ");
        fontResources.setFont(FontManager.creditsFont);
        fontResources.setFill(Color.BLACK);


        // Back button to return to intro screen
        Button backButton = new Button("Back");
        backButton.setFont(FontManager.creditsFont);
        backButton.setPrefWidth(400);
        backButton.setPrefHeight(200);

        // BUG FIXING: Back button height was not being enforced, so I set a min and max height so it would be forced to be at least that height.
        backButton.setMinHeight(200);
        backButton.setMaxHeight(200);

        // Button action to return to intro screen
        backButton.setOnAction(e-> {
            WhatTheFlip.mainStage.setScene(new IntroScene());
        });


        // Layout for credits
        VBox creditsBox = new VBox(gameName, groupName, authorName, resourcesName,musicResourcesTitle, musicResources, sfxResourcesTitle, soundEffectsResources, fontName, fontResources, backButton);
        this.setCenter(creditsBox);
        creditsBox.setAlignment(Pos.CENTER);
        creditsBox.setSpacing(15);

        // Move everything off screen before credits start playing
        gameName.setTranslateY(1000);
        groupName.setTranslateY(1000);
        authorName.setTranslateY(1000);
        resourcesName.setTranslateY(1000);
        musicResourcesTitle.setTranslateY(1000);
        musicResources.setTranslateY(1000);
        sfxResourcesTitle.setTranslateY(1000);
        soundEffectsResources.setTranslateY(1000);
        fontName.setTranslateY(1000);
        fontResources.setTranslateY(1000);
        backButton.setTranslateY(1000);

        // Translate animations (scroll upward) for each text element
        TranslateTransition translateGameName = new TranslateTransition(Duration.millis(2700), gameName);
        translateGameName.setFromY(1000);
        translateGameName.setToY(-1000);

        TranslateTransition translateGroupName = new TranslateTransition(Duration.millis(2700), groupName);
        translateGroupName.setFromY(1000);
        translateGroupName.setToY(-1000);

        TranslateTransition translateAuthorName = new TranslateTransition(Duration.millis(2700), authorName);
        translateAuthorName.setFromY(1000);
        translateAuthorName.setToY(-1000);

        TranslateTransition translateResourcesName = new TranslateTransition(Duration.millis(3100), resourcesName);
        translateResourcesName.setFromY(1000);
        translateResourcesName.setToY(-1000);

        TranslateTransition translateMusicResourcesTitle = new TranslateTransition(Duration.millis(3100), musicResourcesTitle);
        translateMusicResourcesTitle.setFromY(1000);
        translateMusicResourcesTitle.setToY(-1000);

        TranslateTransition translateMusicResources = new TranslateTransition(Duration.millis(3100), musicResources);
        translateMusicResources.setFromY(1000);
        translateMusicResources.setToY(-1000);

        TranslateTransition translateSfxTitle = new TranslateTransition(Duration.millis(3100), sfxResourcesTitle);
        translateSfxTitle.setFromY(1000);
        translateSfxTitle.setToY(-1500);

        TranslateTransition translateSoundEffectResources = new TranslateTransition(Duration.millis(3100), soundEffectsResources);
        translateSoundEffectResources.setFromY(1000);
        translateSoundEffectResources.setToY(-1500);

        TranslateTransition translateFontName = new TranslateTransition(Duration.millis(3100), fontName);
        translateFontName.setFromY(1000);
        translateFontName.setToY(-1750);

        TranslateTransition translateFontResources = new TranslateTransition(Duration.millis(3100), fontResources);
        translateFontResources.setFromY(1000);
        translateFontResources.setToY(-1750);

        TranslateTransition translateBackButton = new TranslateTransition(Duration.millis(4000), backButton);
        translateBackButton.setFromY(1000);
        translateBackButton.setToY(-1275); // Back button stays visible

        //Play the title and text for the music resources, sfx resources, and fonts together
        ParallelTransition musicResourcesTransition = new ParallelTransition(translateMusicResourcesTitle, translateMusicResources);

        ParallelTransition sfxTransition = new ParallelTransition(translateSfxTitle, translateSoundEffectResources);
        
        ParallelTransition fontResourceTransition = new ParallelTransition(translateFontName, translateFontResources);

        // Run all animations in sequence and play them
        SequentialTransition scrollingText = new SequentialTransition(translateGameName, translateGroupName, translateAuthorName , translateResourcesName, musicResourcesTransition, sfxTransition, fontResourceTransition, translateBackButton);
        scrollingText.play();

    }
}
