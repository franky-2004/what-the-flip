package org.fventura.whattheflip;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class AudioPlayer {
    MediaPlayer mediaPlayer = null; // This is for SFX
    MediaPlayer bgMusicPlayer = null; // This is for background music
    Media media = null;
    private boolean isMuted = false;
    private String currentTrack = ""; // Track currently playing audio file

    // Toggle mute on/off
    public void muteAudio() {
        // Flips the current mute state (if muted, change to unmuted, and vise versa)
        isMuted = !isMuted;
        // This only runs if media is playing
        if (mediaPlayer != null) {
            // Mutes the current audio
            mediaPlayer.setMute(isMuted);
        }

        if (bgMusicPlayer != null) {
            bgMusicPlayer.setMute(isMuted);
        }
    }

    public void playIntroScreenAudio() {
        String filePath = "audio/introScreenAudio.mp3";
        if (filePath.equals(currentTrack) && bgMusicPlayer != null && bgMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            return;
        }
        stopCurrentAudio();
        File file = new File(filePath);
        media = new Media(file.toURI().toString());
        bgMusicPlayer = new MediaPlayer(media);
        bgMusicPlayer.setMute(isMuted); // Mutes or unmutes the audio depending on if the value of isMuted is true or false
        bgMusicPlayer.play();

        currentTrack = filePath;
    }

    public void playAllCardsMatchedAudio() {
        File file = new File("audio/allCardsMatchedAudio.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setMute(isMuted);
        mediaPlayer.play();
    }

    public void playCorrectMatchAudio() {
        File file = new File("audio/correctMatchAudio.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setMute(isMuted);
        mediaPlayer.play();
    }

    public void playFailMatchAudio() {
        File file = new File("audio/failMatchAudio.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setMute(isMuted);
        mediaPlayer.play();
    }

    public void playInGameAudio() {
        String filePath = "audio/inGameAudio.mp3";

        // If already playing the same track, don't restart music
        if (filePath.equals(currentTrack) && bgMusicPlayer != null && bgMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            return;
        }

        stopCurrentAudio();

        File file = new File(filePath);
        media = new Media(file.toURI().toString());
        bgMusicPlayer = new MediaPlayer(media);
        bgMusicPlayer.setMute(isMuted);
        bgMusicPlayer.play();
        currentTrack = filePath; // Updates the currentTrack
    }

    public void playOpenMenuAudio() {
        File file = new File("audio/openMenuAudio.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setMute(isMuted);
        mediaPlayer.play();
    }

    public void playButtonAudio() {
        File file = new File("audio/playButtonAudio.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setMute(isMuted);
        mediaPlayer.play();
    }

    // BUG FIX: Preventing Game music from playing in the intro scene
    public void stopCurrentAudio() {
        if (bgMusicPlayer != null) {
            bgMusicPlayer.stop();
            bgMusicPlayer = null;
            currentTrack = "";
        }
    }

    // For settings pane - check if sound is muted
    public boolean isMuted() {
        return isMuted;
    }
}

