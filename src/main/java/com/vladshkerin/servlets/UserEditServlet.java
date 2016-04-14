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

/**
 * TODO: comment
 *
 * @author Vladimir Shkerin
 * @since 13.04.2016
 */
public class UserEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identify = req.getParameter("id");
        if (!identify.isEmpty()) {
            setSessionAttribute(identify, req);
        }
        resp.sendRedirect(String.format("%s/views/UserEdit.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            String name = req.getParameter("name");
            String growth = req.getParameter("growth");
            String birthDay = req.getParameter("birthDay");
            String[] children = req.getParameterValues("children");

            for (User user : UserService.getInstance().getAll()) {
                if (user.getId().equals(id)) {
                    user.setName(name);
                    user.setGrowth(growth);
                    user.setBirthDay(birthDay);
                    user.setChildren(children);
                    break;
                }
            }
            resp.sendRedirect(String.format("%s/views/UserView.jsp", req.getContextPath()));
        }
    }

    private void setSessionAttribute(String id, HttpServletRequest req) {
        try {
            User user = UserService.getInstance().get(id);
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("id", id);
                if (!user.getName().isEmpty())
                    session.setAttribute("name", user.getName());
                if (user.getGrowth() > 0f)
                    session.setAttribute("growth", user.getGrowthStr());
                if (!user.getBirthDayStr().isEmpty())
                    session.setAttribute("birthDay", user.getBirthDayStr("yyyy-MM-dd"));

                session.setAttribute("children",
                        !user.getChildrenStr().isEmpty() ? user.getChildrenStr() : "");
            }
        } catch (NotFoundUser e) {
            //TODO add output to log
            System.out.println(e.getMessage());
        }
    }
}
