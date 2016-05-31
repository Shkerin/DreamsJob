package com.vladshkerin.servlets;

import com.vladshkerin.exception.NotFoundUser;
import com.vladshkerin.models.User;
import com.vladshkerin.services.ApplicationService;
import com.vladshkerin.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The servlet class to login user.
 *
 * @author Vladimir Shkerin
 * @since 13.04.2016
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(Integer.MAX_VALUE);

        String login = "";
        String password = "";
        String message = "";

        Object object = req.getParameter("login");
        if (object != null)
            login = (String) object;

        object = req.getParameter("password");
        if (object != null)
            password = (String) object;

        if (!login.isEmpty()) {
            try {
                User user = UserService.getInstance().get(login, password);
                ApplicationService.getInstance().setSessionAttribute("user", user, session);
                req.getRequestDispatcher("navigation?page=items").forward(req, resp);
            } catch (NotFoundUser ex) {
                message = "User name or password entered is incorrect!";
            }
        } else {
            message = "Enter the user name and password!";
        }

        ApplicationService.getInstance().setSessionAttribute("message", message, session);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
