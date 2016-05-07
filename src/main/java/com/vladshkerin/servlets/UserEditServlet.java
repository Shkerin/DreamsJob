package com.vladshkerin.servlets;

import com.vladshkerin.exception.NotFoundUser;
import com.vladshkerin.models.User;
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
 * TODO: comment
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
            setSessionAttributeUser(id, session);
        }
        resp.sendRedirect(String.format("%s/views/UserEdit.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> userPropertiesMap = new HashMap<>();
        userPropertiesMap.put("id", req.getParameter("id").trim());
        userPropertiesMap.put("name", req.getParameter("name").trim());
        userPropertiesMap.put("growth", req.getParameter("growth").trim());
        userPropertiesMap.put("birthDay", req.getParameter("birthDay").trim());
        userPropertiesMap.put("email", req.getParameter("email").trim());

        HttpSession session = req.getSession();
        String errorValues = UserService.getInstance().validateForm(userPropertiesMap);
        if (errorValues.isEmpty()) {
            try {
                User user = UserService.getInstance().get(userPropertiesMap.get("id"));
                user.setName(userPropertiesMap.get("name"));
                user.setGrowth(userPropertiesMap.get("growth"));
                user.setBirthDay(userPropertiesMap.get("birthDay"));
                user.setEmail(userPropertiesMap.get("email"));
                user.setChildren(req.getParameter("children").trim());

                setSessionAttributeUser(user, session);
                setSessionAttribute("message", "The changes are saved.", session);
            } catch (NotFoundUser ex) {
                //TODO add out to log
                System.out.println(ex.getMessage());
            }
        } else {
            String message = "Incorrect input values: " + errorValues + " !";
            setSessionAttribute("message", message, session);
            // alert ????
        }
        resp.sendRedirect(String.format("%s/views/UserEdit.jsp", req.getContextPath()));
    }

    private void setSessionAttribute(String name, Object value, HttpSession session) {
        synchronized (session) {
            session.setAttribute(name, value);
        }
    }

    private void setSessionAttributeUser(String id, HttpSession session) {
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
            session.setAttribute("id", user.getId());
            session.setAttribute("name",
                    !user.getName().isEmpty() ? user.getName() : "");
            session.setAttribute("growth",
                    user.getGrowth() > 0f ? user.getGrowthStr() : "");
            session.setAttribute("birthDay",
                    !user.getBirthDayStr("yyyy-MM-dd").isEmpty() ? user.getBirthDayStr("yyyy-MM-dd") : "");
            session.setAttribute("email",
                    !user.getEmail().isEmpty() ? user.getEmail() : "");
            session.setAttribute("children",
                    !user.getChildrenStr().isEmpty() ? user.getChildrenStr() : "");
        }
    }
}
