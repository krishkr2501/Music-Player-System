@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    if ("logout".equals(action)) {
        HttpSession session = request.getSession();
        session.invalidate(); // Invalidate the session
        response.sendRedirect("login.jsp"); // Redirect to login page
    } else if ("profile".equals(action)) {
        HttpSession session = request.getSession();
        User user = (User ) session.getAttribute("user");
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // Redirect to login if not logged in
        }
    } else {
        response.sendRedirect("register.jsp"); // Redirect to registration page
    }
}