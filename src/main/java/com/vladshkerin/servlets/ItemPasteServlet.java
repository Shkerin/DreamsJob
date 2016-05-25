package com.vladshkerin.servlets;

import com.vladshkerin.exception.NotFoundItem;
import com.vladshkerin.models.Item;
import com.vladshkerin.services.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The servlet class for paste out items of wood.
 *
 * @author Vladimir Shkerin
 * @since 23.05.2016
 */
public class ItemPasteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parentId = req.getParameter("tree");
        if (parentId != null && !parentId.isEmpty()) {

            Object obj;
            HttpSession session = req.getSession();
            synchronized (session) {
                obj = session.getAttribute("tree");
                session.removeAttribute("tree");
            }

            List<String> listItem = new ArrayList<>();
            if (obj != null && obj instanceof List) {
                listItem = (List) obj;
            }

            for (String str : listItem) {
                try {
                    Item item = ItemService.getInstance().get(Long.valueOf(str));
                    item.setParentId(Long.valueOf(parentId));
                } catch (NotFoundItem ex) {
                    ex.printStackTrace();
                }
            }
        }

        String sURL = String.format("%s/views/ItemView.jsp", req.getContextPath());
        req.getRequestDispatcher(sURL).forward(req, resp);
    }
}
