package com.vladshkerin.servlets;

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
 * The servlet class for add user.
 *
 * @author Vladimir Shkerin
 * @since 12.04.2016
 */
public class UserAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = req.getParameter("name");
        String growth = req.getParameter("growth");
        String birthDay = req.getParameter("birthDay");
        String email = req.getParameter("email");
        String children = req.getParameter("children");

        Map<String, String> map = new HashMap<>();
        map.put("name", name != null ? name.trim() : "");
        map.put("growth", growth != null ? growth.trim() : "");
        map.put("birthDay", birthDay != null ? birthDay.trim() : "");
        map.put("email", email != null ? email.trim() : "");

        String errorValues = UserService.getInstance().validateForm(map);
        if (errorValues.isEmpty()) {

            User user = new User(map.get("name"));
            user.setGrowth(map.get("growth"));
            user.setBirthDay(map.get("birthDay"));
            user.setEmail(map.get("email"));
            user.setChildren(children != null ? children.trim() : "");
            UserService.getInstance().add(user);

            String str = "The user \"" + name + "\" is added.";
            ApplicationService.getInstance().setSessionAttribute("message", str, session);

        } else {
            String message = "Incorrect input values: " + errorValues + " !";
            ApplicationService.getInstance().setSessionAttribute("message", message, session);
        }

        req.getRequestDispatcher("navigation?page=user_add").forward(req, resp);
    }
}
