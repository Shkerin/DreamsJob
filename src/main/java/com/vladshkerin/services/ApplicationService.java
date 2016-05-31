package com.vladshkerin.services;

import javax.servlet.http.HttpSession;

/**
 * The class for control application.
 *
 * @author vlad
 * @since 31.05.2016
 */
public class ApplicationService {

    private static final ApplicationService INSTANCE = new ApplicationService();

    public static ApplicationService getInstance() {
        return INSTANCE;
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

    public void removeSessionAttribute(String name, HttpSession session) {
        synchronized (session) {
            session.removeAttribute(name);
        }
    }
}
