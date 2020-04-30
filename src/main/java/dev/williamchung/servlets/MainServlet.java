package dev.williamchung.servlets;

import dev.williamchung.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();
        System.out.println("doGet was hit at" + action);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/allForums.jsp");
        dispatcher.forward(request, response);
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + password);
        if (userService.authenticateUser(username, password)) {
            response.sendRedirect("/forums");
        } else {
            response.sendRedirect("/");
            //Need to let user know that login was invalid, but that will be done through JSP,
            //but i need to dig old files up and figure out how again.
        }
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        //Need to write the save() method in userRepository before we can implement this
    }
}
