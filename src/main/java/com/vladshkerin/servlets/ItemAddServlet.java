package com.vladshkerin.servlets;

import com.vladshkerin.models.Item;
import com.vladshkerin.models.User;
import com.vladshkerin.services.ApplicationService;
import com.vladshkerin.services.ItemService;

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
        HttpSession session = req.getSession();

        String parentId = req.getParameter("parentId");
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");

        Map<String, String> map = new HashMap<>();
        map.put("parentId", parentId != null ? parentId.trim() : "");
        map.put("name", name != null ? name.trim() : "");

        String errorValues = ItemService.getInstance().validateForm(map);
        if (errorValues.isEmpty()) {

            Object obj = ApplicationService.getInstance().getSessionAttribute("CURRENT_USER", session);
            if (obj != null && obj instanceof User) {
                Item item = new Item((User) obj);
                item.setParentId(Long.valueOf(map.get("parentId")));
                item.setName(map.get("name"));
                item.setDesc(desc != null ? desc.trim() : "");
                ItemService.getInstance().add(item);

                String str = "The item \"" + name + "\" is added.";
                ApplicationService.getInstance().setSessionAttribute("message", str, session);
            } else {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }

        } else {
            String message = "Incorrect input values: " + errorValues + " !";
            ApplicationService.getInstance().setSessionAttribute("message", message, session);
        }

        req.getRequestDispatcher("navigation?page=item_add").forward(req, resp);
    }
}
