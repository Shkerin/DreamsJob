package com.vladshkerin.servlets;

import com.vladshkerin.exception.NotFoundItem;
import com.vladshkerin.exception.NotFoundUser;
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
import java.util.HashMap;
import java.util.Map;

/**
 * The servlet class for edit item.
 *
 * @author Vladimir Shkerin
 * @since 01.06.2016
 */
public class ItemEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            setSessionAttributeItem(Long.valueOf(id), session);
        }
        req.getRequestDispatcher("navigation?page=item_edit").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String id = req.getParameter("id");
        String parentId = req.getParameter("parentId");
        String user = req.getParameter("user");
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String date = req.getParameter("date");

        Map<String, String> map = new HashMap<>();
        map.put("id", id != null ? id.trim() : "");
        map.put("parentId", parentId != null ? parentId.trim() : "");
        map.put("user", user != null ? user.trim() : "");
        map.put("name", name != null ? name.trim() : "");
        map.put("desc", desc != null ? desc.trim() : "");
        map.put("date", date != null ? date.trim() : "");

        String errorValues = ItemService.getInstance().validateForm(map);
        if (errorValues.isEmpty()) {
            try {
                Item item = ItemService.getInstance().get(Long.valueOf(map.get("id")));
                item.setParentId(Long.valueOf(map.get("parentId")));
                try {
                    item.setUser(UserService.getInstance().get(map.get("user")));
                } catch (NotFoundUser notFoundUser) {
                    item.setUser(new User());
                }
                item.setName(map.get("name"));
                item.setDesc(map.get("desc"));
                item.setData(map.get("date"));

                ApplicationService.getInstance().setSessionAttribute(map, session);
                ApplicationService.getInstance().setSessionAttribute("message", "The changes are saved.", session);
            } catch (NotFoundItem ex) {
                //TODO add out to log
                System.out.println(ex.getMessage());
            }
        } else {
            String message = "Incorrect input values: " + errorValues + " !";
            ApplicationService.getInstance().setSessionAttribute("message", message, session);
        }

        req.getRequestDispatcher("navigation?page=item_edit").forward(req, resp);
    }

    private void setSessionAttributeItem(long id, HttpSession session) {
        try {
            Item item = ItemService.getInstance().get(id);
            setSessionAttributeItem(item, session);
        } catch (NotFoundItem ex) {
            //TODO add out to log
            System.out.println(ex.getMessage());
        }
    }

    private void setSessionAttributeItem(Item item, HttpSession session) {
        synchronized (session) {
            session.setAttribute("id", String.valueOf(item.getId()));
            session.setAttribute("parentId", String.valueOf(item.getParentId()));
            session.setAttribute("user", item.getUser().getName());
            session.setAttribute("name", item.getName());
            session.setAttribute("desc", item.getDesc());
            session.setAttribute("date", item.getDateStr("yyyy-MM-dd-HH-mm"));
        }
    }
}
