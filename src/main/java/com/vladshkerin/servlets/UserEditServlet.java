package com.vladshkerin.servlets;

import com.vladshkerin.exception.NotFoundUser;
import com.vladshkerin.models.User;
import com.vladshkerin.services.ApplicationService;
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
        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            setSessionAttributeUser(Long.valueOf(id), session);
        }
        req.getRequestDispatcher("navigation?page=user_edit").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> map = new HashMap<>();

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String growth = req.getParameter("growth");
        String birthDay = req.getParameter("birthDay");
        String email = req.getParameter("email");
        String children = req.getParameter("children");

        map.put("id", id != null ? id.trim() : "");
        map.put("name", name != null ? name.trim() : "");
        map.put("growth", growth != null ? growth.trim() : "");
        map.put("birthDay", birthDay != null ? birthDay.trim() : "");
        map.put("email", email != null ? email.trim() : "");

        HttpSession session = req.getSession();
        String errorValues = UserService.getInstance().validateForm(map);
        if (errorValues.isEmpty()) {
            try {
                User user = UserService.getInstance().get(Long.valueOf(map.get("id")));
                user.setName(map.get("name"));
                user.setGrowth(map.get("growth"));
                user.setBirthDay(map.get("birthDay"));
                user.setEmail(map.get("email"));
                user.setChildren(children != null ? children.trim() : "");

                ApplicationService.getInstance().setSessionAttribute(map, session);
                ApplicationService.getInstance().setSessionAttribute("children", children, session);
                ApplicationService.getInstance().setSessionAttribute("message", "The changes are saved.", session);
            } catch (NotFoundUser ex) {
                //TODO add out to log
                System.out.println(ex.getMessage());
            }
        } else {
            String message = "Incorrect input values: " + errorValues + " !";
            ApplicationService.getInstance().setSessionAttribute("message", message, session);
        }

        req.getRequestDispatcher("navigation?page=user_edit").forward(req, resp);
    }

    private void setSessionAttributeUser(long id, HttpSession session) {
        try {
            User user = UserService.getInstance().get(id);
            setSessionAttributeUser(user, session);
        } catch (NotFoundUser ex) {
            //TODO add out to log
            System.out.println(ex.getMessage());
        }
    }

    private void setSessionAttributeUser(User user, HttpSession session) {
        synchronized (session) {
            session.setAttribute("id", String.valueOf(user.getId()));
            session.setAttribute("name", user.getName());
            session.setAttribute("growth", user.getGrowthStr());
            session.setAttribute("birthDay", user.getBirthDayStr("yyyy-MM-dd"));
            session.setAttribute("email", user.getEmail());
            session.setAttribute("children", user.getChildrenStr());
        }
    }
}
