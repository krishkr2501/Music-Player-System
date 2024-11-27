# Music-Player-System
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasicMusicPlayer {
    private Clip clip;
    private List<String> playlist;
    private int currentSongIndex;

    public BasicMusicPlayer() {
        playlist = new ArrayList<>();
        currentSongIndex = 0;
    }

    public void loadMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
        }
    }

    public void addToPlaylist(String filePath) {
        playlist.add(filePath);
    }

    public void playNext() {
        if (currentSongIndex < playlist.size()) {
            stop();
            close();
            loadMusic(playlist.get(currentSongIndex));
            play();
            currentSongIndex++;
        } else {
            System.out.println("End of playlist.");
        }
    }

    public void playPrevious() {
        if (currentSongIndex > 0) {
            currentSongIndex--;
            stop();
            close();
            loadMusic(playlist.get(currentSongIndex));
            play();
        } else {
            System.out.println("Already at the beginning of the playlist.");
        }
    }

    public void displayPlaylist() {
        System.out.println("Current Playlist:");
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }
}
