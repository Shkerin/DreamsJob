package com.vladshkerin.servlets;

import com.vladshkerin.exception.NotFoundUser;
import com.vladshkerin.models.User;
import com.vladshkerin.services.UserService;

import javax.servlet.RequestDispatcher;
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
//        resp.sendRedirect(String.format("%s/views/UserEdit.jsp", req.getContextPath()));
        RequestDispatcher rd = req.getRequestDispatcher("/views/UserEdit.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> userMap = new HashMap<>();

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String growth = req.getParameter("growth");
        String birthDay = req.getParameter("birthDay");
        String email = req.getParameter("email");
        String children = req.getParameter("children");

        userMap.put("id", id != null ? id.trim() : "");
        userMap.put("name", name != null ? name.trim() : "");
        userMap.put("growth", growth != null ? growth.trim() : "");
        userMap.put("birthDay", birthDay != null ? birthDay.trim() : "");
        userMap.put("email", email != null ? email.trim() : "");

        HttpSession session = req.getSession();
        String errorValues = UserService.getInstance().validateForm(userMap);
        if (errorValues.isEmpty()) {
            try {
                User user = UserService.getInstance().get(Long.valueOf(userMap.get("id")));
                user.setName(userMap.get("name"));
                user.setGrowth(userMap.get("growth"));
                user.setBirthDay(userMap.get("birthDay"));
                user.setEmail(userMap.get("email"));
                user.setChildren(children != null ? children.trim() : "");

                setSessionAttributeUser(user, session);
                setSessionAttribute("message", "The changes are saved.", session);
            } catch (NotFoundUser ex) {
                //TODO add out to log
                System.out.println(ex.getMessage());
            }
        } else {
            String message = "Incorrect input values: " + errorValues + " !";
            setSessionAttribute("message", message, session);
        }
//        resp.sendRedirect(String.format("%s/views/UserEdit.jsp", req.getContextPath()));
        RequestDispatcher rd = req.getRequestDispatcher("/views/UserEdit.jsp");
        rd.forward(req, resp);
    }

    private void setSessionAttribute(String name, Object value, HttpSession session) {
        synchronized (session) {
            session.setAttribute(name, value);
        }
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
