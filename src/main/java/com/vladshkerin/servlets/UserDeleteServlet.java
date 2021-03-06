package com.vladshkerin.servlets;

import com.vladshkerin.exceptions.NotFoundUser;
import com.vladshkerin.services.ApplicationService;
import com.vladshkerin.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = req.getSession();

        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            try {
                UserService.getInstance().delete(Long.valueOf(id));
                UserService.getInstance().saveFile();
            } catch (NotFoundUser ex) {
                String message = ex.getMessage();
                ApplicationService.getInstance().setSessionAttribute("message", message, session);
            }
        }
        req.getRequestDispatcher("navigation?page=users").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
