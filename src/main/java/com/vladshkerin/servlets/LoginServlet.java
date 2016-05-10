package com.vladshkerin.servlets;

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

//    private static HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(Integer.MAX_VALUE);
        synchronized (session) {
            session.setAttribute("login", login);
            session.setAttribute("password", password);
        }
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
