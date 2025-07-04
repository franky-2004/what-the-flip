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
import javafx.scene.text.Font;
import javafx.util.Duration;
import static org.fventura.whattheflip.WhatTheFlip.audioPlayer;
import org.fventura.whattheflip.Card;
import org.fventura.whattheflip.GameLogic;
import org.fventura.whattheflip.HighScoreManager;
import org.fventura.whattheflip.WhatTheFlip;
import org.fventura.whattheflip.scenes.IntroScene;

import static org.fventura.whattheflip.WhatTheFlip.mainStage;

import java.util.ArrayList;
import java.util.List;

public class GamePane extends BorderPane {
    private GridPane gridPane;
    private GameLogic logic;
    private boolean isMuted = false;

    private List<StackPane> flippedPanes = new ArrayList<>();
    private List<ImageView> frontViews = new ArrayList<>();
    private List<ImageView> backViews = new ArrayList<>();
    private Label missLabel = new Label("Misses: 0");
    private Label highScoreLabel = new Label();

    public GamePane() {
        audioPlayer.stopCurrentAudio();
        WhatTheFlip.audioPlayer.playInGameAudio();

        logic = new GameLogic();

        gridPane = new GridPane();
        this.setCenter(gridPane);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        missLabel.setFont(new Font(20));

        int savedHighScore = HighScoreManager.getHighScore();
        if (savedHighScore != -1) {
            highScoreLabel.setText("Highscore: " + savedHighScore);
        } else {
            // If there is no high score
            highScoreLabel.setText("Highscore: N/A");
        }
        highScoreLabel.setFont(new Font(20));

        HBox scoreBox = new HBox(30, missLabel, highScoreLabel);
        scoreBox.setAlignment(Pos.CENTER);

        Button exitButton = new Button("Exit");
        Button resetButton = new Button("Reset");
        Button muteButton = new Button("Mute");

        exitButton.setOnAction(e -> {
            audioPlayer.stopCurrentAudio();
            mainStage.setScene(new IntroScene());
        });

        resetButton.setOnAction(e ->  {
            mainStage.setScene(new Scene(new GamePane(), 1024, 768));
        });

        muteButton.setOnAction(e -> {
            WhatTheFlip.audioPlayer.muteAudio();

            if(muteButton.getText().equals("Mute")) {
                muteButton.setText("Unmute");
            } else {
                muteButton.setText("Mute");
            }

        });

        VBox buttonColumn = new VBox(15, exitButton, resetButton, muteButton); // Add mute button here later
        buttonColumn.setAlignment(Pos.TOP_RIGHT);
        buttonColumn.setPrefWidth(80);
        buttonColumn.setTranslateY(10);

        VBox cardContainer = new VBox(gridPane);
        cardContainer.setAlignment(Pos.CENTER);
        cardContainer.setPrefWidth(700);

        HBox layout = new HBox(20, cardContainer, buttonColumn);
        layout.setAlignment(Pos.TOP_CENTER);

        VBox mainLayout = new VBox(20, scoreBox, layout);
        mainLayout.setAlignment(Pos.CENTER);

        this.setCenter(mainLayout);

        placeCards();
    }

    private void placeCards() {
        int col = 0;
        int row = 0;

        for (Card card : logic.getDeck()) {
            ImageView backView = new ImageView(new Image(getClass().getResourceAsStream("/cards/cardback.png")));
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

    private void handleCardFlip(Card card, ImageView frontView, ImageView backView, StackPane cardPane) {
        if (!logic.canFlip(card)) return;

        fadeFlip(backView, frontView);
        logic.flip(card);
        flippedPanes.add(cardPane);

        if (logic.isReadyToCheck()) {
            checkMatch();
        }
    }

    private void checkMatch() {
        List<Card> flipped = logic.getFlippedCards();
        Card card1 = flipped.get(0);
        Card card2 = flipped.get(1);

        int index1 = logic.getDeck().indexOf(card1);
        int index2 = logic.getDeck().indexOf(card2);

        if (logic.checkMatch()) {
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

    private void showWinScreen() {
        this.getChildren().clear();
        missLabel.setVisible(false);

        // play audio when all cards matched

        WhatTheFlip.audioPlayer.playAllCardsMatchedAudio();

        Label winLabel = new Label("You won!");
        winLabel.setFont(new Font(32));
        winLabel.setTextFill(Color.GREEN);

        int misses = logic.getMisses();
        Label totalMissesLabel = new Label("Total Misses: " + logic.getMisses());
        totalMissesLabel.setFont(new Font(24));
        totalMissesLabel.setTextFill(Color.RED);

        // Check and update high score
        int savedHighScore = HighScoreManager.getHighScore();
        if (savedHighScore == -1) {
            HighScoreManager.trySaveHighScore(misses);
            highScoreLabel.setText("Highscore: " + misses);
        } else {
            if (misses < savedHighScore) {
                HighScoreManager.trySaveHighScore(misses);
                highScoreLabel.setText("Highscore: " + misses);
            } else
                highScoreLabel.setText("Highscore: " + savedHighScore);
        }

        highScoreLabel.setFont(new Font(20));
        highScoreLabel.setTextFill(Color.DARKBLUE);

        Button backButton = new Button("Back to Home");
        audioPlayer.stopCurrentAudio();
        backButton.setFont(new Font(16));
        backButton.setOnAction(e -> {
            mainStage.setScene(new IntroScene());
        });

        VBox winLayout = new VBox(20, winLabel, totalMissesLabel, highScoreLabel, backButton);
        winLayout.setAlignment(Pos.CENTER);

        this.setCenter(winLayout);
    }
}
