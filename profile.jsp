<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
    <h1>User Profile</h1>
    <c:if test="${not empty user}">
        <p>Username: ${user.username}</p>
        <p>Email: ${user.email}</p>
        <form action="user" method="get">
            <button type="submit" name="action" value="logout">Logout</button>
        </form>
    </c:if>
</body>
</html>