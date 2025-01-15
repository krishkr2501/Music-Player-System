<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Basic Music Player</title>
</head>
<body>
    <h1>Basic Music Player</h1>
    <form action="musicplayer" method="get">
        <button type ="submit" name="action" value="play">Play</button>
        <button type="submit" name="action" value="stop">Stop</button>
        <button type="submit" name="action" value="next">Next</button>
        <button type="submit" name="action" value="previous">Previous</button>
    </form>

    <h2>Add a Song to Playlist</h2>
    <form action="musicplayer" method="post">
        <input type="text" name="songPath" placeholder="Enter song path" required>
        <button type="submit" name="action" value="addSong">Add Song</button>
    </form>

    <h2>Playlist</h2>
    <ul>
        <c:forEach var="song" items="${playlist}">
            <li>${song}</li>
        </c:forEach>
    </ul>
</body>
</html>