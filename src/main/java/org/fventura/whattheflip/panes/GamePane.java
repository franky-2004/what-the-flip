package org.fventura.whattheflip.panes;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import static org.fventura.whattheflip.WhatTheFlip.audioPlayer;
import org.fventura.whattheflip.*;
import org.fventura.whattheflip.scenes.IntroScene;
import static org.fventura.whattheflip.WhatTheFlip.mainStage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Franky Ventura
 * @studentID 0816694
 * @date June 13th 2025
 *
 * This class creates the scene that shows the gameplay pane
 */

public class GamePane extends BorderPane {
    private GridPane gridPane; // Holds the 4x4 card grid
    private GameLogic logic; // Handles card matching and game rules

    private List<StackPane> flippedPanes = new ArrayList<>(); // Track flipped card visuals
    private List<ImageView> frontViews = new ArrayList<>(); // Card front images
    private List<ImageView> backViews = new ArrayList<>(); // Card back images
    private Label missLabel = new Label("Misses: 0"); // Misses counter
    private Label highScoreLabel = new Label(); // Highscore display

    public GamePane() {
        // Reset any background audio and start game music
        audioPlayer.stopCurrentAudio();
        WhatTheFlip.audioPlayer.playInGameAudio();

        logic = new GameLogic(WhatTheFlip.selectedTheme); // Create deck and shuffle it + assign a theme

        // Card grid setup
        gridPane = new GridPane();
        this.setCenter(gridPane);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        missLabel.setFont(FontManager.textFont);

        // Check for saved high score and display it
        int savedHighScore = HighScoreManager.getHighScore();
        if (savedHighScore != -1) {
            highScoreLabel.setText("Highscore: " + savedHighScore);
        } else {
            // If there is no high score
            highScoreLabel.setText("Highscore: N/A");
        }
        highScoreLabel.setFont(FontManager.textFont);

        HBox scoreBox = new HBox(30, missLabel, highScoreLabel); // Show both labels side by side
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.setTranslateX(-50);
        scoreBox.setTranslateY(-5);

        // Menu buttons
        Button resetButton = new Button("Reset");
        resetButton.setFont(FontManager.textFont);
        resetButton.setPrefSize(175, 50);
        resetButton.setMinWidth(175);
        Button muteButton = new Button("Mute");
        muteButton.setFont(FontManager.textFont);
        muteButton.setPrefSize(175, 50);
        muteButton.setMinWidth(175);
        Button exitButton = new Button("Exit");
        exitButton.setFont(FontManager.textFont);
        exitButton.setPrefSize(175, 50);
        exitButton.setMinWidth(175);

        // Exit returns to the intro screen and stops audio
        exitButton.setOnAction(e -> {
            audioPlayer.stopCurrentAudio();
            mainStage.setScene(new IntroScene());
        });

        // Reset reloads a new GamePane scene
        resetButton.setOnAction(e ->  {
            mainStage.setScene(new Scene(new GamePane(), 1024, 768));
        });

        // Mute or unmute audio
        muteButton.setOnAction(e -> {
            WhatTheFlip.audioPlayer.muteAudio();

            if(muteButton.getText().equals("Mute")) {
                muteButton.setText("Unmute");
            } else {
                muteButton.setText("Mute");
            }

        });

        // Layout for buttons beside the card area
        VBox buttonColumn = new VBox(15, resetButton, muteButton, exitButton);
        buttonColumn.setAlignment(Pos.TOP_LEFT);
        buttonColumn.setTranslateY(30);
        buttonColumn.setTranslateX(50);

        // Wrap the grid in a VBox
        VBox cardContainer = new VBox(gridPane);
        cardContainer.setAlignment(Pos.CENTER);
        cardContainer.setPrefWidth(600);
        cardContainer.setTranslateX(50);

        // Full horizontal layout with cards and buttons
        HBox layout = new HBox(30, cardContainer, buttonColumn);
        layout.setAlignment(Pos.CENTER);

        // Vertical layout: score + main game UI
        VBox mainLayout = new VBox(5, scoreBox, layout);
        mainLayout.setAlignment(Pos.CENTER);
        this.setCenter(mainLayout);

        placeCards(); // Add card views to grid
    }

    // Adds all the shuffled cards to the grid
    private void placeCards() {
        int col = 0;
        int row = 0;

        for (Card card : logic.getDeck()) {
            ImageView backView = new ImageView(new Image(getClass().getResourceAsStream("/cards/cardback_" + WhatTheFlip.selectedTheme + ".png")));
            ImageView frontView = new ImageView(new Image(getClass().getResourceAsStream("/" + card.getImagePath())));

            backView.setFitWidth(120);
            backView.setFitHeight(168);
            frontView.setFitWidth(120);
            frontView.setFitHeight(168);
            frontView.setVisible(false);
            frontView.setOpacity(0);

            backViews.add(backView);
            frontViews.add(frontView);

            StackPane cardPane = new StackPane(backView, frontView);
            cardPane.setOnMouseClicked(e -> handleCardFlip(card, frontView, backView, cardPane));

            gridPane.add(cardPane, col, row);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }
    }

    // Handles what happens when a card is clicked
    private void handleCardFlip(Card card, ImageView frontView, ImageView backView, StackPane cardPane) {
        if (!logic.canFlip(card)) return;

        fadeFlip(backView, frontView); // Animate flip
        logic.flip(card); // Add to flipped list
        flippedPanes.add(cardPane);

        if (logic.isReadyToCheck()) {
            checkMatch();
        }
    }

    // Checks if flipped cards are a match
    private void checkMatch() {
        List<Card> flipped = logic.getFlippedCards();
        Card card1 = flipped.get(0);
        Card card2 = flipped.get(1);

        int index1 = logic.getDeck().indexOf(card1);
        int index2 = logic.getDeck().indexOf(card2);

        if (logic.checkMatch()) {
            // If it's a match, wait and then hide both cards
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(e -> {
                flippedPanes.get(0).setVisible(false);
                flippedPanes.get(1).setVisible(false);
                logic.clearFlipped();
                flippedPanes.clear();
                audioPlayer.playCorrectMatchAudio();

                if (logic.isGameOver()) {
                    showWinScreen();
                }
            });
            pause.play();
        } else {
            // If not a match, flip them back
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                fadeFlip(frontViews.get(index1), backViews.get(index1));
                fadeFlip(frontViews.get(index2), backViews.get(index2));
                logic.clearFlipped();
                flippedPanes.clear();
                WhatTheFlip.audioPlayer.playFailMatchAudio();

            });
            missLabel.setText("Misses: " + logic.getMisses());

            pause.play();
        }
    }

    // Fade transition from one card side to another
    private void fadeFlip(ImageView hideView, ImageView showView) {
        hideView.setVisible(true);
        showView.setVisible(true);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(200), hideView);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(200), showView);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        fadeOut.setOnFinished(e -> {
            hideView.setVisible(false);
            fadeIn.play();
        });

        fadeOut.play();
    }

    // Displays win screen and triggers popup
    private void showWinScreen() {
        this.getChildren().clear(); // Clear the game board
        missLabel.setVisible(false); // Hide miss counter

        // Play audio when all cards matched
        WhatTheFlip.audioPlayer.playAllCardsMatchedAudio(); // Play win audio

        Label winLabel = new Label("You won!");
        winLabel.setFont(FontManager.youWonFont);
        winLabel.setTextFill(Color.GREEN);

        Button backButton = new Button("Back to Home");
        audioPlayer.stopCurrentAudio();
        backButton.setFont(FontManager.textFont);
        backButton.setOnAction(e -> {
            mainStage.setScene(new IntroScene());
        });

        VBox winLayout = new VBox(20, winLabel, backButton);
        winLayout.setAlignment(Pos.CENTER);
        this.setCenter(winLayout);

        // High score check + popup
        int misses = logic.getMisses();
        int savedHighScore = HighScoreManager.getHighScore();
        boolean isNewHighScore = false;

        if (savedHighScore == -1 || misses < savedHighScore) {
            HighScoreManager.trySaveHighScore(misses);
            isNewHighScore = true;
        }

        showWinPopup(isNewHighScore, misses);
    }

    // A popup stage that shows up when you win the game
    private void showWinPopup(boolean isNewHighScore, int misses) {
        Stage popupStage = new Stage();
        popupStage.setTitle("Game Complete!"); // The title of the popup

        // Label that displays "You won!"
        Label winLabel = new Label("You Won!");
        winLabel.setFont(FontManager.gameWinPopupFont);
        winLabel.setTextFill(Color.GREEN);

        // Label that displays miss counter
        Label missesLabel = new Label("Misses: " + misses);
        missesLabel.setFont(FontManager.textFont);
        missesLabel.setTextFill(Color.DARKRED);

        Label scoreLabel;
        if (isNewHighScore) {
            scoreLabel = new Label("New High Score!");
            scoreLabel.setTextFill(Color.GOLDENROD);
        } else {
            int currentHigh = HighScoreManager.getHighScore();
            scoreLabel = new Label("High Score: " + currentHigh);
            scoreLabel.setTextFill(Color.DARKBLUE);
        }
        scoreLabel.setFont(FontManager.textFont);

        Button closeButton = new Button("Close");
        closeButton.setFont(FontManager.textFont);
        closeButton.setOnAction(e -> popupStage.close());

        VBox layout = new VBox(15, winLabel, missesLabel, scoreLabel, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene popupScene = new Scene(layout, 300, 200);
        popupStage.setScene(popupScene);
        popupStage.setResizable(false);
        popupStage.show();

    }

}
