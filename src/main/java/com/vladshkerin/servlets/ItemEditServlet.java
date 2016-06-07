package com.vladshkerin.servlets;

import com.vladshkerin.exceptions.NotFoundItem;
import com.vladshkerin.exceptions.NotFoundUser;
import com.vladshkerin.models.Item;
import com.vladshkerin.models.User;
import com.vladshkerin.services.ApplicationService;
import com.vladshkerin.services.FilterService;
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

        boolean isError = false;
        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            try {
                Item item = ItemService.getInstance().get(Long.valueOf(id));
                if (FilterService.getInstance().validation(item)) {
                    ApplicationService.getInstance().setSessionAttribute("item", item, session);
                } else {
                    isError = true;
                }
            } catch (NotFoundItem ex) {
                isError = true;
            }
        }

        if (!isError) {
            req.getRequestDispatcher("navigation?page=item_edit").forward(req, resp);
        } else {
            String error_url = req.getRequestURL().toString() + "?id=" + id;
            ApplicationService.getInstance().setSessionAttribute("error_url", error_url, session);
            req.getRequestDispatcher("/WEB-INF/views/errors/error_404.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String id = req.getParameter("id");
        String parentId = req.getParameter("parentId");
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String date = req.getParameter("date");

        Map<String, String> map = new HashMap<>();
        map.put("id", id != null ? id.trim() : "");
        map.put("parentId", parentId != null ? parentId.trim() : "");
        map.put("name", name != null ? name.trim() : "");
        map.put("description", desc != null ? desc.trim() : "");
        map.put("date", date != null ? date.trim() : "");

        String errorValues = ItemService.getInstance().validateForm(map);
        if (errorValues.isEmpty()) {
            try {
                Item item = ItemService.getInstance().get(Long.valueOf(map.get("id")));
                try {
                    String user = req.getParameter("user");
                    item.setUser(UserService.getInstance().get(user));
                } catch (NotFoundUser notFoundUser) {
                    Object obj = ApplicationService.getInstance().getSessionAttribute("CURRENT_USER", session);
                    if (obj != null && obj instanceof User)
                        item.setUser((User) obj);
                }
                item.setParentId(Long.valueOf(map.get("parentId")));
                item.setName(map.get("name"));
                item.setDesc(map.get("description"));
                item.setData(map.get("date"), "dd.MM.yyyy HH:mm");

                ItemService.getInstance().saveFile();

                ApplicationService.getInstance().setSessionAttribute("item", item, session);
                ApplicationService.getInstance().setSessionAttribute("message", "The changes are saved.", session);
            } catch (NotFoundItem ex) {
                String message = ex.getMessage();
                ApplicationService.getInstance().setSessionAttribute("message", message, session);
            }
        } else {
            String message = "Incorrect input values: " + errorValues + " !";
            ApplicationService.getInstance().setSessionAttribute("message", message, session);
        }

        req.getRequestDispatcher("navigation?page=item_edit").forward(req, resp);
    }
}
