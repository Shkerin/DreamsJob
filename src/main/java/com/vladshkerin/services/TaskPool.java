package com.vladshkerin.services;

import com.vladshkerin.enums.Operation;

/**
 * The class for consistent running of list of operation.
 *
 * @author Vladimir Shkerin
 * @since 01.06.2016
 */
public class TaskPool implements Runnable {

    private final Object object = new Object();

    private Operation[] pool;

    public TaskPool(Operation[] pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        for (Operation operation : pool) {
            synchronized (object) {
                try {
                    Thread t = new Thread(new TaskWorker(operation));
                    t.start();
                    object.wait();
                } catch (InterruptedException ex) {
                    //TODO out to log
                    ex.printStackTrace();
                }
            }
        }
    }
}
