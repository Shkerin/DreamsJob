package com.vladshkerin.servlets;

import com.vladshkerin.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO: comment
 *
 * @author Vladimir Shkerin
 * @since 26.04.2016
 */
public class UserDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identify = req.getParameter("id");
        if (!identify.isEmpty()) {
            UserService.getInstance().delete(identify);
        }
        resp.sendRedirect(String.format("%s/views/UserView.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}