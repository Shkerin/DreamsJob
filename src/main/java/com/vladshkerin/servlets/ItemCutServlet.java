package com.vladshkerin.servlets;

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
        List<String> listItem = new ArrayList<>();
        for (Map.Entry<String, String[]> map : req.getParameterMap().entrySet()) {
            for (String s : map.getValue())
                listItem.add(s);
        }

        if (listItem.size() > 0) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("tree", listItem);
            }
        }

        String sURL = String.format("%s/views/ItemView.jsp", req.getContextPath());
        req.getRequestDispatcher(sURL).forward(req, resp);
    }
}
