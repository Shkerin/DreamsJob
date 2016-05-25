package com.vladshkerin.servlets;

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
        synchronized (session) {
            session.removeAttribute("tree");
        }

        String sURL = String.format("%s/views/ItemView.jsp", req.getContextPath());
        req.getRequestDispatcher(sURL).forward(req, resp);
    }
}
