import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasicMusicPlayer {
    private Clip clip; // Clip object to handle audio playback
    private List<String> playlist; // List to store the paths of songs in the playlist
    private int currentSongIndex; // Index to track the currently playing song

    // Constructor to initialize the music player
    public BasicMusicPlayer() {
        playlist = new ArrayList<>(); // Initialize the playlist
        currentSongIndex = 0; // Start with the first song
    }

    // Load a music file into the player
    public void loadMusic(String filePath) {
        try {
            File audioFile = new File(filePath); // Create a File object for the audio file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile); // Get audio input stream
            clip = AudioSystem.getClip(); // Get a clip resource
            clip.open(audioInputStream); // Open the audio input stream in the clip
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    // Start playing the loaded audio clip
    public void play() {
        if (clip != null) {
            clip.start(); // Start playback
        }
    }

    // Stop the currently playing audio clip
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Stop playback if it's currently running
        }
    }

    // Loop the currently loaded audio clip continuously
    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the clip indefinitely
        }
    }

    // Close the audio clip and release resources
    public void close() {
        if (clip != null) {
            clip.close(); // Close the clip
        }
    }

    // Add a song file path to the playlist
    public void addToPlaylist(String filePath) {
        playlist.add(filePath); // Add the file path to the playlist
    }

    // Play the next song in the playlist
    public void playNext() {
        if (currentSongIndex < playlist.size()) {
            stop(); // Stop the current song
            close(); // Close the current clip
            loadMusic(playlist.get(currentSongIndex)); // Load the next song
            play(); // Start playing the next song
            currentSongIndex++; // Move to the next song index
        } else {
            System.out.println("End of playlist."); // Inform user if at the end of the playlist
        }
    }

    // Play the previous song in the playlist
    public void playPrevious() {
        if (currentSongIndex > 0) {
            currentSongIndex--; // Move to the previous song index
            stop(); // Stop the current song
            close(); // Close the current clip
            loadMusic(playlist.get(currentSongIndex)); // Load the previous song
            play(); // Start playing the previous song
        } else {
            System.out.println("Already at the beginning of the playlist."); // Inform user if at the beginning
        }
    }

    // Display the current playlist to the user
    public void displayPlaylist() {
        System.out.println("Current Playlist:");
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i)); // Print each song in the playlist
        }
    }
}
