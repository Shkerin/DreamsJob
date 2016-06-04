package com.vladshkerin.servlets;

import com.vladshkerin.exceptions.NotFoundUser;
import com.vladshkerin.models.Filter;
import com.vladshkerin.models.User;
import com.vladshkerin.services.ApplicationService;
import com.vladshkerin.services.FilterService;
import com.vladshkerin.services.ItemService;
import com.vladshkerin.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The servlet class to login user.
 *
 * @author Vladimir Shkerin
 * @since 13.04.2016
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ApplicationService.getInstance().initializationSession(session);

        String login = "";
        String password = "";
        String message = "";

        Object obj = req.getParameter("login");
        if (obj != null)
            login = (String) obj;

        obj = req.getParameter("password");
        if (obj != null)
            password = (String) obj;

        String url = "index.jsp";
        if (!login.isEmpty()) {
            loadData();
            try {
                User user = UserService.getInstance().get(login, password);
                ApplicationService.getInstance().setSessionAttribute("CURRENT_USER", user, session);
                FilterService.getInstance().add(new Filter(user));
                url = "navigation?page=items";
            } catch (NotFoundUser ex) {
                message = "User name or password entered is incorrect!";
            }
        } else {
            message = "Enter the user name and password!";
        }

        ApplicationService.getInstance().setSessionAttribute("message", message, session);

        req.getRequestDispatcher(url).forward(req, resp);
    }

    private void loadData() {
//        Operation[] operations = new Operation[]{Operation.LOAD_USERS, Operation.LOAD_ITEMS};
//        ApplicationService.getInstance().executeTask(operations);

        UserService.getInstance().loadFile();
        ItemService.getInstance().loadFile();
    }
}
