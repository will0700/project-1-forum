package dev.williamchung.servlets;

import dev.williamchung.models.Comment;
import dev.williamchung.models.Forum;
import dev.williamchung.models.User;
import dev.williamchung.models.Thread;
import dev.williamchung.services.CommentService;
import dev.williamchung.services.ForumService;
import dev.williamchung.services.ThreadService;
import dev.williamchung.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private UserService userService = new UserService();
    private ForumService forumService = new ForumService();
    private ThreadService threadService = new ThreadService();
    private CommentService commentService = new CommentService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String action = request.getServletPath();
        if (action.startsWith("/forum/")){
            try {
                showOneForum(request, response);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (action.startsWith("/thread/")){
            try {
                showThread(request, response);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        try {
            switch(action) {
                case "/forums":
                    showAllForums(request, response);
                    break;
                default:
                    showLoginReg(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String action = request.getServletPath();
        System.out.println("doPost was hit at" + action);
        try {
            switch(action) {
                case "/login":
                    doLogin(request, response);
                    break;
                case "/register":
                    doRegister(request, response);
                    break;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void showLoginReg(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void showAllForums(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Forum> forums = forumService.getAllForums();
        request.setAttribute("forums", forums);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/allForums.jsp");
        dispatcher.forward(request, response);
    }

    private void showOneForum(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String action = request.getServletPath();
        String forumId = (String) action.subSequence(7, action.length());
        Forum forum = forumService.getForumById(forumId);
        request.setAttribute("forum", forum);
        List<Thread> threads = threadService.getThreadsByForum(forum.getId());
        request.setAttribute("threads", threads);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/oneForum.jsp");
        dispatcher.forward(request, response);
    }

    private void showThread(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String action = request.getServletPath();
        String threadId = (String) action.subSequence(8, action.length());
        System.out.println(threadId);
        Thread thread = threadService.getThreadById(threadId);
        System.out.println(thread.getContent());
        request.setAttribute("thread", thread);
        List<Comment> comments = commentService.getCommentsByThread(thread.getId());
        request.setAttribute("comments", comments);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/oneThread.jsp");
        dispatcher.forward(request, response);
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.authenticateUser(username, password)) {
            User user = userService.getUserByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/forums");
        } else {
            response.sendRedirect("/");
            //Need to let user know that login was invalid through JSP error page
        }
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.usernameAvailable(username)) {
            User user = userService.registerUser(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/forums");
        } else {
            response.sendRedirect("/");
            //Need to let user know that registration failed through JSP error page
        }
    }
}
