package com.vladshkerin.servlets;

import com.vladshkerin.models.User;
import com.vladshkerin.services.ApplicationService;
import com.vladshkerin.services.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The servlet class for delete item.
 *
 * @author Vladimir Shkerin
 * @since 01.06.2016
 */
public class ItemDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Object obj = ApplicationService.getInstance().getSessionAttribute("CURRENT_USER", session);
        if (obj == null || !(obj instanceof User)) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            ItemService.getInstance().delete(Long.valueOf(id));
            ItemService.getInstance().saveFile();
        }
        req.getRequestDispatcher("navigation?page=items").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
