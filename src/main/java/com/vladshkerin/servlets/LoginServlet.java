package com.vladshkerin.servlets;

import com.vladshkerin.enums.RoleUser;
import com.vladshkerin.exception.NotFoundUser;
import com.vladshkerin.services.UserService;

import javax.servlet.RequestDispatcher;
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
        String password = req.getParameter("password");

        String role;
        try {
            role = UserService.getInstance().getToName(login).getRole().getName();
        } catch (NotFoundUser ex) {
            role = RoleUser.USER.toString();
        }

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(Integer.MAX_VALUE);
        synchronized (session) {
            session.setAttribute("login", login);
            session.setAttribute("password", password);
            session.setAttribute("role", role);
        }
//        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
        RequestDispatcher rd = req.getRequestDispatcher(String.format("%s/index.jsp", req.getContextPath()));
        rd.forward(req, resp);
    }
}
