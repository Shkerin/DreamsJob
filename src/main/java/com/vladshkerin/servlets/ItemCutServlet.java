package com.vladshkerin.servlets;

import com.vladshkerin.models.User;
import com.vladshkerin.services.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The servlet class for cutting out items of wood.
 *
 * @author Vladimir Shkerin
 * @since 23.05.2016
 */
public class ItemCutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Object obj = ApplicationService.getInstance().getSessionAttribute("CURRENT_USER", session);
        if (obj == null || !(obj instanceof User)) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

        List<String> listItem = new ArrayList<>();
        for (Map.Entry<String, String[]> map : req.getParameterMap().entrySet()) {
            for (String s : map.getValue())
                listItem.add(s);
        }

        if (listItem.size() > 0) {
            ApplicationService.getInstance().setSessionAttribute("sheets_tree_items", listItem, session);
        }

        req.getRequestDispatcher("navigation?page=items").forward(req, resp);
    }
}
