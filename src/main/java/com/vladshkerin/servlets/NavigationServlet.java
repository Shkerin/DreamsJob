package com.vladshkerin.servlets;

import com.vladshkerin.services.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The servlet class to navigation user.
 *
 * @author Vladimir Shkerin
 * @since 30.05.2016
 */
public class NavigationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Object object = ApplicationService.getInstance().getSessionAttribute("CURRENT_USER", session);
        if (object != null) {

            object = req.getParameter("page");
            if (object != null) {

                String link;
                String page = (String) object;
                switch (page) {
                    case "items":
                        link = "/WEB-INF/views/ItemView.jsp";
                        break;
                    case "item_add":
                        link = "/WEB-INF/views/ItemAdd.jsp";
                        break;
                    case "item_edit":
                        link = "/WEB-INF/views/ItemEdit.jsp";
                        break;
                    case "users":
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
                        req.getSession().setAttribute("error_url", error_url);
                        link = "error.jsp";
                }
                req.getRequestDispatcher(link).forward(req, resp);
            }

        } else {
            resp.sendRedirect("index.jsp");
        }
    }
}
