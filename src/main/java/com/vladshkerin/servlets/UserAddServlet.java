package com.vladshkerin.servlets;

import com.vladshkerin.exception.NotFoundUser;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("id");
        if (!param.isEmpty()) {
            fillFilds(param, req);
        }
        resp.sendRedirect(String.format("%s/views/UserAdd.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        if (id != null && name != null) {
            String growth = req.getParameter("growth");
            String birthDay = req.getParameter("birthDay");
            String[] children = req.getParameterValues("children");

            User user = new User(id.trim(), name.trim());
            user.setGrowth(growth.trim());
            user.setBirthDay(birthDay.trim());
            user.setChildren(children);
            UserService.getInstance().add(user);
        }
        resp.sendRedirect(String.format("%s/views/UserAdd.jsp", req.getContextPath()));
    }

    private void fillFilds(String id, HttpServletRequest req) {
        try {
            User user = UserService.getInstance().get(id);
            req.setAttribute("id", id);
            req.setAttribute("name", user.getName());
            req.setAttribute("growth", user.getGrowthStr());
            req.setAttribute("birthDay", user.getBirthDayStr());
            req.setAttribute("children", user.getChildrenStr());
        } catch (NotFoundUser e) {
            System.out.println(e.getMessage());
        }
    }
}
