package org.fventura.whattheflip.panes;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.fventura.whattheflip.WhatTheFlip;
import org.fventura.whattheflip.scenes.IntroScene;


public class CreditsPane extends BorderPane {
    public CreditsPane(){
        // Credits for resources, teachers/group members - also trying all the blue variants to see what we want to use for the game
        Text gameName = new Text("What The Flip");
        gameName.setFont(Font.font("Impact", 60));
        gameName.setFill(Color.LIGHTBLUE);
        Text groupName = new Text("Flip Happens");
        groupName.setFont(Font.font("Impact", 60));
        groupName.setFill(Color.CORNFLOWERBLUE);
        Text authorName = new Text("Sean Manser + Franky Ventura");
        authorName.setFont(Font.font("Impact", 60));
        authorName.setFill(Color.DODGERBLUE);
        Text resourcesName = new Text("Resources");
        resourcesName.setFont(Font.font("Impact", 60));
        resourcesName.setFill(Color.MIDNIGHTBLUE);

        Button backButton = new Button("Back");
        backButton.setPrefWidth(200);

        //Button Actions
        backButton.setOnAction(e-> {
            WhatTheFlip.mainStage.setScene(new IntroScene());
        });


        //Vbox holding items
        VBox creditsBox = new VBox(gameName, groupName, authorName, resourcesName, backButton);
        this.setCenter(creditsBox);
        creditsBox.setAlignment(Pos.CENTER);
        creditsBox.setSpacing(15);

        // Animation for scrolling text - Star Wars Style?
        TranslateTransition translateGameName = new TranslateTransition(Duration.millis(8000), gameName);
        translateGameName.setFromY(500);
//        translateGameName.setFromX();
        translateGameName.setToY(-1000);
//        translateGameName.setToX();

        TranslateTransition translateGroupName = new TranslateTransition(Duration.millis(8000), groupName);
        translateGroupName.setFromY(500);
//        translateGroupName.setFromX();
        translateGroupName.setToY(-1000);
//        translateGroupName.setToX();

        TranslateTransition translateAuthorName = new TranslateTransition(Duration.millis(8000), authorName);
        translateAuthorName.setFromY(500);
//        translateSName.setFromX();
        translateAuthorName.setToY(-1000);
//        translateSName.setToX();


        TranslateTransition translateResourcesName = new TranslateTransition(Duration.millis(8000), resourcesName);
        translateResourcesName.setFromY(500);
//        translateResourcesName.setFromX();
        translateResourcesName.setToY(-2000);
//        translateResourcesName.setToX();

        TranslateTransition translateBackButton = new TranslateTransition(Duration.millis(8000), backButton);
        translateBackButton.setFromY(500);
        translateBackButton.setToY(0);

        SequentialTransition scrollingText = new SequentialTransition(translateGameName, translateGroupName, translateAuthorName , translateResourcesName, translateBackButton);
        scrollingText.play();

    }
}
