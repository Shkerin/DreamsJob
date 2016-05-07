package com.vladshkerin.servlets;

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
 * @since 12.04.2016
 */
public class UserAddServlet extends HttpServlet {

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
            String name = userPropertiesMap.get("name");
            User user = new User(name);
            user.setGrowth(userPropertiesMap.get("growth"));
            user.setBirthDay(userPropertiesMap.get("birthDay"));
            user.setEmail(userPropertiesMap.get("email"));
            user.setChildren(req.getParameter("children").trim());
            UserService.getInstance().add(user);

            setSessionAttribute("message", "The user \"" + name + "\" is added.", session);
        } else {
            String message = "Incorrect input values: " + errorValues + " !";
            setSessionAttribute("message", message, session);
            // alert ????
        }
        resp.sendRedirect(String.format("%s/views/UserAdd.jsp", req.getContextPath()));
    }

    private void setSessionAttribute(String name, Object value, HttpSession session) {
        synchronized (session) {
            session.setAttribute(name, value);
        }
    }
}
