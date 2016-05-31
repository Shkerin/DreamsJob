package com.vladshkerin.servlets;

import com.vladshkerin.services.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The servlet class cancels the cutting of the tree node.
 *
 * @author Vladimir Shkerin
 * @since 25.05.2016
 */
public class ItemCancelCutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ApplicationService.getInstance().removeSessionAttribute("tree", session);

        req.getRequestDispatcher("navigation?page=items").forward(req, resp);
    }
}
