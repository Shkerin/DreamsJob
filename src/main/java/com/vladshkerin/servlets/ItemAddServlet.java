package com.vladshkerin.servlets;

import com.vladshkerin.exceptions.NotFoundUser;
import com.vladshkerin.models.Item;
import com.vladshkerin.models.User;
import com.vladshkerin.services.ApplicationService;
import com.vladshkerin.services.ItemService;
import com.vladshkerin.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
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

        Map<String, String> map = new LinkedHashMap<>();
        map.put("parentId", parentId != null ? parentId.trim() : "");
        map.put("name", name != null ? name.trim() : "");
        map.put("description", desc != null ? desc.trim() : "");

        String errorValues = ItemService.getInstance().validateForm(map);
        if (errorValues.isEmpty()) {
            Item item;
            try {
                String user = req.getParameter("user");
                item = new Item(UserService.getInstance().get(user));
            } catch (NotFoundUser notFoundUser) {
                Object obj = ApplicationService.getInstance().getSessionAttribute("CURRENT_USER", session);
                if (obj != null && obj instanceof User)
                    item = new Item((User) obj);
                else
                    item = new Item();
            }
            item.setParentId(Long.valueOf(map.get("parentId")));
            item.setName(map.get("name"));
            item.setDesc(map.get("description"));
            ItemService.getInstance().add(item);

            ItemService.getInstance().saveFile();

            String str = "The item \"" + name + "\" is added.";
            ApplicationService.getInstance().setSessionAttribute("message", str, session);
        } else {
            String message = "Incorrect input values: " + errorValues + " !";
            ApplicationService.getInstance().setSessionAttribute("message", message, session);
        }

        req.getRequestDispatcher("navigation?page=item_add").forward(req, resp);
    }
}
