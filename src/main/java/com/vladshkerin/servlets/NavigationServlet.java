package com.vladshkerin.servlets;

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
import java.util.List;

/**
 * The servlet class to navigation user.
 *
 * @author Vladimir Shkerin
 * @since 30.05.2016
 */
public class NavigationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        User current_user = null;
        Object obj = ApplicationService.getInstance().getSessionAttribute("CURRENT_USER", session);
        if (obj != null && (obj instanceof User)) {
            current_user = (User) obj;
            String str = current_user.getRole().isRoleUser() ? "readonly" : "";
            ApplicationService.getInstance().setSessionAttribute("readonly", str, session);
        }

        obj = req.getParameter("page");
        if (obj != null) {

            String link;
            String page = (String) obj;
            switch (page) {
                case "main":
                    link = "WEB-INF/views/Main.jsp";
                    break;
                case "login":
                    link = "WEB-INF/views/Login.jsp";
                    break;
                case "items":
                    List<Item> items = ItemService.getInstance().getAllValidation();
                    ApplicationService.getInstance().setSessionAttribute("items", items, session);
                    //TODO question 03.06.2016
//                        req.setAttribute("tree_items", ItemService.getInstance().getTreeItems(0L));
                    link = "/WEB-INF/views/ItemView.jsp";
                    break;
                case "item_add":
                    ApplicationService.getInstance().setSessionAttribute("user", current_user, session);
                    link = "/WEB-INF/views/ItemAdd.jsp";
                    break;
                case "item_edit":
                    link = "/WEB-INF/views/ItemEdit.jsp";
                    break;
                case "users":
                    List<User> users = UserService.getInstance().getAllValidation();
                    ApplicationService.getInstance().setSessionAttribute("users", users, session);
                    link = "/WEB-INF/views/UserView.jsp";
                    break;
                case "user_add":
                    link = "/WEB-INF/views/UserAdd.jsp";
                    break;
                case "user_edit":
                    link = "/WEB-INF/views/UserEdit.jsp";
                    break;
                default:
                    String error_url = req.getRequestURL().toString() + "?page=" + page;
                    ApplicationService.getInstance().setSessionAttribute("error_url", error_url, session);
                    link = "/WEB-INF/view/errors/error_404.jsp";
            }
            req.getRequestDispatcher(link).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
