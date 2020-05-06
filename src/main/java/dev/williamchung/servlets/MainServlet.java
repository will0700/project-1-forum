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

@WebServlet(
        urlPatterns = {"/"},
    name="thisisthemainservletname")
public class MainServlet extends HttpServlet {
    private UserService userService = new UserService();
    private ForumService forumService = new ForumService();
    private ThreadService threadService = new ThreadService();
    private CommentService commentService = new CommentService();

    @Override
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
        } else {
            try {
                switch (action) {
                    case "/forums":
                        showAllForums(request, response);
                        break;
                    case "/logout":
                        doLogout(request, response);
                        break;
                    default:
                        showLoginReg(request, response);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String action = request.getServletPath();
        try {
            switch(action) {
                case "/login":
                    doLogin(request, response);
                    break;
                case "/register":
                    doRegister(request, response);
                    break;
                case "/comment":
                    postComment(request, response);
                    break;
                case "/thread":
                    postThread(request, response);
                    break;
                default:
                    showLoginReg(request, response);
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
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            response.sendRedirect("/");
        } else {
            List<Forum> forums = forumService.getAllForums();
            request.setAttribute("forums", forums);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/allForums.jsp");
            System.out.println(dispatcher.toString());
            dispatcher.forward(request, response);
        }
    }

    private void showOneForum(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("/");
        } else {
            String action = request.getServletPath();
            String forumId = (String) action.subSequence(7, action.length());
            Forum forum = forumService.getForumById(forumId);
            request.setAttribute("forum", forum);
            List<Thread> threads = threadService.getThreadsByForum(forum.getId());
            request.setAttribute("threads", threads);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/oneForum.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void showThread(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("/");
        } else {
            String action = request.getServletPath();
            String threadId = (String) action.subSequence(8, action.length());
            Thread thread = threadService.getThreadById(threadId);
            request.setAttribute("thread", thread);
            List<Comment> comments = commentService.getCommentsByThread(thread.getId());
            request.setAttribute("comments", comments);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/oneThread.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.authenticateUser(username, password)) {
            User user = userService.getUserByUsername(username);
            session.setAttribute("user", user);
            response.sendRedirect("/forums");
        } else {
            response.sendRedirect("/");
            //Need to let user know that login was invalid through JSP error page
        }
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.usernameAvailable(username)) {
            User user = userService.registerUser(username, password);
            session.setAttribute("user", user);
            response.sendRedirect("/forums");
        } else {
            response.sendRedirect("/");
            //Need to let user know that registration failed through JSP error page
        }
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("/");
    }

    private void postComment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("/");
        } else {
            User user = (User) session.getAttribute("user");
            String commentContent = request.getParameter("comment");
            String threadId = request.getParameter("threadId");
            commentService.postComment(commentContent, user, threadId);
            response.sendRedirect("/thread/" + threadId);
        }
    }

    private void postThread(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("/");
        } else {
            User user = (User) session.getAttribute("user");
            String threadTitle = request.getParameter("threadTitle");
            String threadContent = request.getParameter("threadContent");
            String forumId = request.getParameter("forumId");
            Thread thread = threadService.postThread(threadTitle, threadContent, user.getId(),forumId);
            response.sendRedirect("/thread/" + thread.getId().toString());
        }
    }
}
