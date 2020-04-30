package dev.williamchung.servlets;

import dev.williamchung.services.ForumService;
import dev.williamchung.services.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ForumServlet extends HttpServlet {
    private UserService userService = new UserService();
    private ForumService forumService = new ForumService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
    }
}
