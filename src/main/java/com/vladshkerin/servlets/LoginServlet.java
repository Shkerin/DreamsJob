package com.vladshkerin.servlets;

import com.vladshkerin.enums.UserRole;
import com.vladshkerin.exception.NotFoundUser;
import com.vladshkerin.models.Role;
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
        String login = req.getParameter("login");
        if (login == null) {
            login = "";
        }
        String password = req.getParameter("password");
        if (password == null) {
            password = "";
        }

        Role role;
        try {
            role = UserService.getInstance().getToName(login).getRole();
        } catch (NotFoundUser ex) {
            login = "";
            password = "";
            role = new Role(UserRole.USER);
        }

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(Integer.MAX_VALUE);
        synchronized (session) {

            session.setAttribute("login", login);
            session.setAttribute("password", password);
            session.setAttribute("role", role);
        }

//        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
        String sURL = String.format("%s/index.jsp", req.getContextPath());
        req.getRequestDispatcher(sURL).forward(req, resp);
    }
}
