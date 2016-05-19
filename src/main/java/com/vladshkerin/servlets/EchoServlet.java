package com.vladshkerin.servlets;

import com.vladshkerin.enums.UserRole;
import com.vladshkerin.models.Role;
import com.vladshkerin.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The servlet class to test the methods of a servlet.
 *
 * @author Vladimir Shkerin
 * @since 13.03.2016
 */
public class EchoServlet extends HttpServlet {

    private final List<User> syncUserList = new CopyOnWriteArrayList<>();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder();
        sb.append("<h3>List users name: </h3>");
        synchronized (syncUserList) {
            for (User user : syncUserList)
                sb.append(user).append("<br>");
        }

        resp.getWriter().write(sb.toString());
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        Float growth = Float.parseFloat(req.getParameter("growth"));
        String[] children = req.getParameterValues("children");
        Calendar birthDay = Calendar.getInstance();
        String email = req.getParameter("email");

        String roleStr = req.getParameter("role");
        Role role = "admin".equals(roleStr) ? new Role(UserRole.ADMIN) : new Role(UserRole.USER);

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date date = format.parse(req.getParameter("birthDay"));
            birthDay.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        syncUserList.add(new User(name, role, growth, birthDay, email, children));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        int index = findIdById(id);
        if (index > -1)
            syncUserList.set(index, new User(name));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        int index = findIdById(id);
        if (index > -1)
            syncUserList.remove(index);
    }

    /**
     * Search by field "id" in the collection User.
     *
     * @param id the value of the unique field "id" in collection
     * @return -1 if element do not find,
     * index element in the collection
     */
    private int findIdById(long id) {
        int index = -1;
        synchronized (syncUserList) {
            for (User user : syncUserList) {
                if (user.getId() == id) {
                    index = syncUserList.indexOf(user);
                    break;
                }
            }
        }
        return index;
    }
}
