package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/musicplayer")
public class MusicPlayerServlet extends HttpServlet {
    private BasicMusicPlayer musicPlayer;

    @Override
    public void init() throws ServletException {
        musicPlayer = new BasicMusicPlayer(); // Initialize the music player
        // Optionally, load some default songs into the playlist
        musicPlayer.addToPlaylist("path/to/song1.wav");
        musicPlayer.addToPlaylist("path/to/song2.wav");
        // Add more songs as needed
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "play":
                musicPlayer.play(); // Play the current song
                break;
            case "stop":
                musicPlayer.stop(); // Stop the current song
                break;
            case "next":
                musicPlayer.playNext(); // Play the next song
                break;
            case "previous":
                musicPlayer.playPrevious(); // Play the previous song
                break;
            case "display":
                List<String> playlist = musicPlayer.getPlaylist(); // Get the current playlist
                request.setAttribute("playlist", playlist); // Set the playlist as a request attribute
                request.getRequestDispatcher("/index.jsp").forward(request, response); // Forward to JSP
                return;
            default:
                break;
        }

        response.sendRedirect("index.jsp"); // Redirect back to the main page
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "addSong":
                String songPath = request.getParameter("songPath"); // Get the song path from the request
                if (songPath != null && !songPath.isEmpty()) {
                    musicPlayer.addToPlaylist(songPath); // Add the song to the playlist
                }
                break;
            default:
                break;
        }

        response.sendRedirect("index.jsp"); // Redirect back to the main page
    }

    @Override
    public void destroy() {
        musicPlayer.close(); // Close the music player when the servlet is destroyed
    }
}