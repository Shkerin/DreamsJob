package com.vladshkerin.servlets;

import com.vladshkerin.services.ItemService;
import com.vladshkerin.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet class to logout user.
 *
 * @author Vladimir Shkerin
 * @since 13.04.2016
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getRequestDispatcher("index.jsp").forward(req, resp);

        saveData();
    }

    private void saveData() {
//        ApplicationService.getInstance().executeTask(Operation.SAVE_ITEMS);

        UserService.getInstance().saveFile();
        ItemService.getInstance().saveFile();
    }
}
