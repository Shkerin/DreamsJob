package com.vladshkerin.services;

import com.vladshkerin.enums.Operation;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * The class for control application.
 *
 * @author Vladimir Shkerin
 * @since 31.05.2016
 */
public class ApplicationService {

    private static final ApplicationService INSTANCE = new ApplicationService();

    public static ApplicationService getInstance() {
        return INSTANCE;
    }

    public void executeTask(Operation[] operations) {
        TaskPool taskPool = new TaskPool(operations);
        Thread t = new Thread(taskPool);
        t.start();
    }

    public void executeTask(Operation operation) {
        Thread t = new Thread(new TaskWorker(operation));
        t.start();
    }

    public Object getSessionAttribute(String name, HttpSession session) {
        synchronized (session) {
            return session.getAttribute(name);
        }
    }

    public void setSessionAttribute(String name, Object value, HttpSession session) {
        synchronized (session) {
            session.setAttribute(name, value);
        }
    }

    public void setSessionAttribute(Map<String, String> map, HttpSession session) {
        synchronized (session) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                session.setAttribute(entry.getKey(), entry.getValue());
            }
        }
    }

    public void removeSessionAttribute(String name, HttpSession session) {
        synchronized (session) {
            session.removeAttribute(name);
        }
    }
}
