package com.vladshkerin.servlets;

import com.vladshkerin.homework.SecurityContextHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO: comment
 *
 * @author Vladimir Shkerin
 * @since 16.03.2016
 */
public class CounterServlet extends HttpServlet {

    private volatile int count = 0;
    private final AtomicInteger atomicInteger = new AtomicInteger(count);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        method_1(req, resp);
        method_2(req, resp);
        method_3(req, resp);
        method_4(req, resp);
        method_5(req, resp);
    }

    /**
     * Method 1 - ReentrantLock
     */
    private void method_1(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Lock countLock = new ReentrantLock();
        countLock.lock();
        try {
            count++;
            resp.getWriter().write(count);
        } finally {
            countLock.unlock();
        }
    }

    /**
     * Method 2 - synchronized method
     */
    private synchronized void method_2(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        count++;
        resp.getWriter().write(count);
    }

    /**
     * Method 3 - synchronized block
     */
    private void method_3(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        synchronized (this) {
            count++;
            resp.getWriter().write(count);
        }
    }

    /**
     * Method 4 - AtomicInteger
     */
    private void method_4(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        atomicInteger.getAndIncrement();
    }

    /**
     * Method 5 - ThreadLocal
     */
    private void method_5(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SecurityContextHolder.setCount(count);
        SecurityContextHolder.increment();
    }
}
