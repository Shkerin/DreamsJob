package com.vladshkerin.servlets;

import com.vladshkerin.models.UserAdvance;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TODO: comment
 *
 * @author Vladimir Shkerin
 * @since 13.03.2016
 */
public class EchoServlet extends HttpServlet {

    private final List<UserAdvance> syncUserList = new CopyOnWriteArrayList<>();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder();
        sb.append("<h3>List users name: </h3>");
        synchronized (syncUserList) {
            for (UserAdvance userAdvance : syncUserList)
                sb.append(userAdvance).append("<br>");
        }

        resp.getWriter().write(sb.toString());
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Float growth = Float.parseFloat(req.getParameter("growth"));
        String[] children = req.getParameterValues("children");
        Calendar birthDay = Calendar.getInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date date = format.parse(req.getParameter("birthDay"));
            birthDay.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        syncUserList.add(new UserAdvance(id, name, growth, birthDay, children));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        int index = findIdById(id);
        if (index > -1)
            syncUserList.set(index, new UserAdvance(id, name));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int index = findIdById(id);
        if (index > -1)
            syncUserList.remove(index);
    }

    /**
     * Search by field "id" in the collection UserAdvance.
     *
     * @param id the value of the unique field "id" in collection
     * @return -1 if element do not find,
     * index element in the collection
     */
    private int findIdById(String id) {
        int index = -1;
        synchronized (syncUserList) {
            for (UserAdvance userAdvance : syncUserList) {
                if (userAdvance.getId().equals(id)) {
                    index = syncUserList.indexOf(userAdvance);
                    break;
                }
            }
        }
        return index;
    }
}
