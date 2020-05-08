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
import org.apache.log4j.Logger;

/**
 * This is the main servlet which uses a FrontController design of sorts.
 * This servlet will handle all the requests with url starting "/", meaning every route, realistically.
 * Each doVerb method (Get, Post, Delete implemented) reads the beginning of a string
 * and then uses a series of if/else and switch/case logic to invoke the correct handling method.
 * This routing logic could be simplified with RegEx in later versions.
 */
@WebServlet("/")
public class MainServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final ForumService forumService = new ForumService();
    private final ThreadService threadService = new ThreadService();
    private final CommentService commentService = new CommentService();
    private static Logger LOG = Logger.getLogger(MainServlet.class);

    /**
     * The doGet override implementation matches routes coming in with a GET request.
     * If the url route doesn't match any of the if/else and switch/case trees, the default route invoked.
     *
     * Routes like "/forum/(forumId)" and "/thread/(threadId)" are caught first,
     * and then the switch/case catches the "/forums" and "/logout".
     * All else routes to "/forums".
     * @param request
     * @param response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String action = request.getServletPath();
        if (action.startsWith("/forum/")){
            try {
                LOG.info("Showing one forum page ...");
                showOneForum(request, response);
            } catch (Exception exception) {
                LOG.warn("Error in doGet showoneForum: "+exception.getMessage());
                exception.printStackTrace();
            }
        } else if (action.startsWith("/thread/")){
            try {
                LOG.info("Showing Thread page ...");
                showThread(request, response);
            } catch (Exception exception) {
                LOG.warn("Error in doGet thread: "+exception.getMessage());
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
                LOG.warn("Error in all forums switch: "+exception.getMessage());
                exception.printStackTrace();
            }
        }
    }

    /**
     * The doPost override implementation matches routes coming in with a POST request.
     * If the url route doesn't match any of the if/else and switch/case trees, the default route invoked.
     *
     * "/login", "/register", "/thread", and "/comment" routes each invoke their own processing methods.
     * All else redirects to the default GET route.
     * @param request
     * @param response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String action = request.getServletPath();
        try {
            System.out.println(action);
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
            LOG.warn("Error in do post switch: "+exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * The doDelete method is to serve the thread deleting functionality.
     * All other DELETE requests are redirected away to the default page.
     * @param request
     * @param response
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String action = request.getServletPath();
        try {
            if (action.startsWith("/thread/delete/")) {
                deleteThread(request, response);
                return;
            } else {
                showAllForums(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            LOG.warn("Error in do delete: "+exception.getMessage());
        }
    }

    /**
     * showLoginReg method loads the landing page, which is the LoginReg page.
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    private void showLoginReg(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * showAllForums method is the de facto home page, as routes not found will redirect here.
     * Starting with this method and below, session is checked to see if an User is logged in.
     * If no user is found in session, servlet will redirect to the LoginReg page.
     * We set request Attribute to pass data to the JSP file.
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    private void showAllForums(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            LOG.info("Showing all forums page ...");
            response.sendRedirect("/");
        } else {
            List<Forum> forums = forumService.getAllForums();
            request.setAttribute("forums", forums);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/allForums.jsp");
            System.out.println(dispatcher.toString());
            dispatcher.forward(request, response);
        }
    }

    /**
     * showOneForum renders the view for one Forum and its Threads.
     * If no user is found in session, servlet will redirect to the LoginReg page.
     * We set request Attribute to pass data to the JSP file.
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    private void showOneForum(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            LOG.info("Showing one forum...");
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

    /**
     * showThread renders the page for one Thread and its comments.
     * If no user is found in session, servlet will redirect to the LoginReg page.
     * We set request Attribute to pass data to the JSP file.
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    private void showThread(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            LOG.info("Showing thread...");
            response.sendRedirect("/");
        } else {
            String action = request.getServletPath();
            String threadId = (String) action.subSequence(8, action.length());
            Thread thread = threadService.getThreadById(threadId);
            request.setAttribute("thread", thread);
            List<Comment> comments = commentService.getCommentsByThread(thread.getId());
            request.setAttribute("comments", comments);
            Forum forum = forumService.getForumById(thread.getForumId().toString());
            request.setAttribute("forum", forum);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/oneThread.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * doLogin method handles the POST request for logging in.
     * authenticateUser method is invoked from the service to log in the user.
     * The user is then added to session.
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.authenticateUser(username, password)) {
            User user = userService.getUserByUsername(username);
            session.setAttribute("user", user);
            response.sendRedirect("forums");
            LOG.info("user logged in...");
        } else {
            response.sendRedirect("/");
        }
    }

    /**
     * doRegister method handles the POST request for Registering a new user.
     * usernameAvailable is invoked from the service to check if a username can be used.
     * User is then added to database and session.
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.usernameAvailable(username)) {
            User user = userService.registerUser(username, password);
            session.setAttribute("user", user);
            response.sendRedirect("/forums");
            LOG.info("user registered...");
        } else {
            response.sendRedirect("/");
            //Need to let user know that registration failed through JSP error page
        }
    }

    /**
     * doLogout method deletes the session and redirects to the login page.
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("/");
        LOG.info("user logged out...");
    }

    /**
     * postComment method handles the POST request to add a new Comment to a Thread.
     * form submission data is pulled from the request and passed via service to the database.
     * request is then redirected to render the Thread the Comment was added to.
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
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

    /**
     * postThread method handles the POST request to add a anew Thread to a Forum.
     * form submission data is pulled from the request and passed via service to the database.
     * request is then redirected to render the Thread that was created.
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
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

    /**
     * deleteThread method handles the DELETE request to remove a Thread from the database.
     * passes the Thread id and the User in session to the service to handle.
     * The redirect isn't given by the servlet, just the Status change.
     * The JS script that made this AJAX request will handle the redirect in browser.
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    private void deleteThread(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("/");
        } else {
            User user = (User) session.getAttribute("user");
            String action = request.getServletPath();
            String threadId = (String) action.subSequence(15, action.length());
            threadService.deleteThreadById(threadId, user);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
