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
 * The servlet class for add user.
 *
 * @author Vladimir Shkerin
 * @since 12.04.2016
 */
public class UserAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> userMap = new HashMap<>();

        String name = req.getParameter("name");
        String growth = req.getParameter("growth");
        String birthDay = req.getParameter("birthDay");
        String email = req.getParameter("email");
        String children = req.getParameter("children");

        userMap.put("name", name != null ? name.trim() : "");
        userMap.put("growth", growth != null ? growth.trim() : "");
        userMap.put("birthDay", birthDay != null ? birthDay.trim() : "");
        userMap.put("email", email != null ? email.trim() : "");

        HttpSession session = req.getSession();
        String errorValues = UserService.getInstance().validateForm(userMap);
        if (errorValues.isEmpty()) {
            User user = new User(userMap.get("name"));
            user.setGrowth(userMap.get("growth"));
            user.setBirthDay(userMap.get("birthDay"));
            user.setEmail(userMap.get("email"));
            user.setChildren(children != null ? children.trim() : "");
            UserService.getInstance().add(user);

            setSessionAttribute("message", "The user \"" + name + "\" is added.", session);
        } else {
            String message = "Incorrect input values: " + errorValues + " !";
            setSessionAttribute("message", message, session);
        }
//        resp.sendRedirect(String.format("%s/views/UserAdd.jsp", req.getContextPath()));
        String sURL = String.format("%s/views/UserAdd.jsp", req.getContextPath());
        req.getRequestDispatcher(sURL).forward(req, resp);
    }

    private void setSessionAttribute(String name, Object value, HttpSession session) {
        synchronized (session) {
            session.setAttribute(name, value);
        }
    }
}
