package org.fventura.whattheflip;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * @author Sean Manser
 * @studentID 0520988
 * @date June 27th 2025
 *
 * This class creates and audio player and has various methods for interactions such as matching cards and opening menus
 */


public class AudioPlayer {
    MediaPlayer mediaPlayer = null; // MediaPlayer for sound effects
    MediaPlayer bgMusicPlayer = null; // MediaPlayer for background music
    Media media = null;
    private boolean isMuted = false; // Stores the mute state
    private String currentTrack = ""; // Keeps track of the currently playing background music

    // Toggles the mute state for both music and sound effects
    public void muteAudio() {
        // Flips the current mute state (if muted, change to unmuted, and vise versa)
        isMuted = !isMuted;

        // Mute SFX
        if (mediaPlayer != null) {
            // Mutes the current audio
            mediaPlayer.setMute(isMuted);
        }

        // Mute background music
        if (bgMusicPlayer != null) {
            bgMusicPlayer.setMute(isMuted);
        }
    }

    // Plays music for the intro screen (only starts if it is not already playing)
    public void playIntroScreenAudio() {
        String filePath = "audio/introScreenAudio.mp3";

        // Avoid restarting the same track
        if (filePath.equals(currentTrack) && bgMusicPlayer != null && bgMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            return;
        }

        stopCurrentAudio(); // Stops any music that's already playing

        File file = new File(filePath);
        media = new Media(file.toURI().toString());
        bgMusicPlayer = new MediaPlayer(media);
        bgMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Music loops forever
        bgMusicPlayer.setMute(isMuted); // Mutes or unmutes the audio depending on if the value of isMuted is true or false
        bgMusicPlayer.play();

        currentTrack = filePath;
    }

    // Plays sound when all cards are matched
    public void playAllCardsMatchedAudio() {
        File file = new File("audio/allCardsMatchedAudio.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setMute(isMuted);
        mediaPlayer.play();
    }

    // Plays sound for a correct card match
    public void playCorrectMatchAudio() {
        File file = new File("audio/correctMatchAudio.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setMute(isMuted);
        mediaPlayer.play();
    }

    // Plays sound for a failed match
    public void playFailMatchAudio() {
        File file = new File("audio/failMatchAudio.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setMute(isMuted);
        mediaPlayer.play();
    }

    // Plays music during the game
    public void playInGameAudio() {
        String filePath = "audio/inGameAudio.mp3";

        // Avoid restarting the same track
        if (filePath.equals(currentTrack) && bgMusicPlayer != null && bgMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            return;
        }

        stopCurrentAudio(); // Stops any previous music

        File file = new File(filePath);
        media = new Media(file.toURI().toString());
        bgMusicPlayer = new MediaPlayer(media);
        bgMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Music loops forever
        bgMusicPlayer.setMute(isMuted);
        bgMusicPlayer.play();
        currentTrack = filePath; // Updates the currentTrack
    }

    // Plays sound when opening a menu
    public void playOpenMenuAudio() {
        File file = new File("audio/openMenuAudio.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setMute(isMuted);
        mediaPlayer.play();
    }

    // Plays sound when clicking a button
    public void playButtonAudio() {
        File file = new File("audio/playButtonAudio.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setMute(isMuted);
        mediaPlayer.play();
    }

    // Stops the current background music and resets the track info
    public void stopCurrentAudio() {
        if (bgMusicPlayer != null) {
            bgMusicPlayer.stop();
            bgMusicPlayer = null;
            currentTrack = "";
        }
    }

    // Returns the current mute state (used in settings)
    public boolean isMuted() {
        return isMuted;
    }
}

