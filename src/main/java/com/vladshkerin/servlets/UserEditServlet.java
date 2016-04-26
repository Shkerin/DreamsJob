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
            HttpSession session = req.getSession();
            setSessionAttribute(identify, session);
            synchronized (session) {
                session.setAttribute("message", "");
            }
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
            String email = req.getParameter("email");
            String[] children = req.getParameterValues("children");

            for (User user : UserService.getInstance().getAll()) {
                if (user.getId().equals(id)) {
                    user.setName(name);
                    user.setGrowth(growth);
                    user.setBirthDay(birthDay);
                    user.setEmail(email);
                    user.setChildren(children);

                    HttpSession session = req.getSession();
                    setSessionAttribute(id, session);
                    synchronized (session) {
                        session.setAttribute("message", "The changes are saved.");
                    }
                    break;
                }
            }
            resp.sendRedirect(String.format("%s/views/UserEdit.jsp", req.getContextPath()));
        }
    }

    private void setSessionAttribute(String id, HttpSession session) {
        try {
            User user = UserService.getInstance().get(id);
            synchronized (session) {
                session.setAttribute("id", id);
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
        } catch (NotFoundUser e) {
            //TODO add output to log
            System.out.println(e.getMessage());
        }
    }
}
