package com.vladshkerin.servlets;

import com.vladshkerin.exceptions.NotFoundUser;
import com.vladshkerin.models.User;
import com.vladshkerin.services.ApplicationService;
import com.vladshkerin.services.FilterService;
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
 * The servlet class for edit user.
 *
 * @author Vladimir Shkerin
 * @since 13.04.2016
 */
public class UserEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Object obj = ApplicationService.getInstance().getSessionAttribute("CURRENT_USER", session);
        if (obj == null || !(obj instanceof User)) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

        boolean isError = false;
        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
//            setSessionAttributeUser(Long.valueOf(id), session);
            try {
                User user = UserService.getInstance().get(Long.valueOf(id));
                if (FilterService.getInstance().validationUser(user)) {
                    ApplicationService.getInstance().setSessionAttribute("user", user, session);
                } else {
                    isError = true;
                }
            } catch (NotFoundUser ex) {
                isError = true;
            }
        }

        if (!isError) {
            req.getRequestDispatcher("navigation?page=user_edit").forward(req, resp);
        } else {
            String error_url = req.getRequestURL().toString() + "?id=" + id;
            ApplicationService.getInstance().setSessionAttribute("error_url", error_url, session);
            req.getRequestDispatcher("/WEB-INF/errors/error_404.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Object obj = ApplicationService.getInstance().getSessionAttribute("CURRENT_USER", session);
        if (obj == null || !(obj instanceof User)) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String growth = req.getParameter("growth");
        String birthDay = req.getParameter("birthDay");
        String email = req.getParameter("email");
        String children = req.getParameter("children");

        Map<String, String> map = new HashMap<>();
        map.put("id", id != null ? id.trim() : "");
        map.put("name", name != null ? name.trim() : "");
        map.put("growth", growth != null ? growth.trim() : "");
        map.put("birthDay", birthDay != null ? birthDay.trim() : "");
        map.put("email", email != null ? email.trim() : "");

        String errorValues = UserService.getInstance().validateForm(map);
        if (errorValues.isEmpty()) {
            map.put("children", children != null ? children.trim() : "");
            try {
                User user = UserService.getInstance().get(Long.valueOf(map.get("id")));
                user.setName(map.get("name"));
                user.setGrowth(map.get("growth"));
                user.setBirthDay(map.get("birthDay"));
                user.setEmail(map.get("email"));
                user.setChildren(map.get("children"));

                UserService.getInstance().saveFile();

                ApplicationService.getInstance().setSessionAttribute("user", user, session);
                ApplicationService.getInstance().setSessionAttribute("message", "The changes are saved.", session);
            } catch (NotFoundUser ex) {
                String message = ex.getMessage();
                ApplicationService.getInstance().setSessionAttribute("message", message, session);
            }
        } else {
            String message = "Incorrect input values: " + errorValues + " !";
            ApplicationService.getInstance().setSessionAttribute("message", message, session);
        }

        req.getRequestDispatcher("navigation?page=user_edit").forward(req, resp);
    }
}
