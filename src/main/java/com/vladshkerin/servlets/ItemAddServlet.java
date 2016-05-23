package com.vladshkerin.servlets;

import com.vladshkerin.models.Item;
import com.vladshkerin.models.User;
import com.vladshkerin.services.ItemService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The servlet class to add item.
 *
 * @author Vladimir Shkerin
 * @since 09.05.2016
 */
public class ItemAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> itemMap = new HashMap<>();

        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        itemMap.put("name", name != null ? name.trim() : "");
        itemMap.put("desc", desc != null ? desc.trim() : "");

        HttpSession session = req.getSession();
        String errorValues = ItemService.getInstance().validateForm(itemMap);
        if (errorValues.isEmpty()) {
            //TODO set current user and parent id
            Item item = new Item(new User("anonymous"));
            item.setName(itemMap.get("name"));
            item.setDesc(itemMap.get("desc"));
            ItemService.getInstance().add(item);

            setSessionAttribute("message", "The item \"" + name + "\" is added.", session);
        } else {
            String message = "Incorrect input values: " + errorValues + " !";
            setSessionAttribute("message", message, session);
        }
//        resp.sendRedirect(String.format("%s/views/ItemAdd.jsp", req.getContextPath()));
        RequestDispatcher rd = req.getRequestDispatcher("/views/ItemAdd.jsp");
        rd.forward(req, resp);
    }

    private void setSessionAttribute(String name, Object value, HttpSession session) {
        synchronized (session) {
            session.setAttribute(name, value);
        }
    }
}
