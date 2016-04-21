package com.vladshkerin.servlets;

import com.vladshkerin.models.User;
import com.vladshkerin.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO: comment
 *
 * @author Vladimir Shkerin
 * @since 12.04.2016
 */
public class UserAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            String name = req.getParameter("name");
            String growth = req.getParameter("growth");
            String birthDay = req.getParameter("birthDay");
            String email = req.getParameter("email");
            String[] children = req.getParameterValues("children");

            User user = new User(id.trim(), name.trim());
            user.setGrowth(growth.trim());
            user.setBirthDay(birthDay.trim());
            user.setEmail(email);
            user.setChildren(children);
            UserService.getInstance().add(user);
        }
        resp.sendRedirect(String.format("%s/views/UserAdd.jsp", req.getContextPath()));
    }
}
