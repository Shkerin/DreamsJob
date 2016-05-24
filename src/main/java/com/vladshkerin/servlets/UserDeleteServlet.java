package com.vladshkerin.servlets;

import com.vladshkerin.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet class for delete user.
 *
 * @author Vladimir Shkerin
 * @since 26.04.2016
 */
public class UserDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            UserService.getInstance().delete(Long.valueOf(id));
        }
//        resp.sendRedirect(String.format("%s/views/UserView.jsp", req.getContextPath()));
        String sURL = String.format("%s/views/UserView.jsp", req.getContextPath());
        req.getRequestDispatcher(sURL).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
