package com.vladshkerin.models;

import com.vladshkerin.enums.Operation;
import com.vladshkerin.services.ItemService;
import com.vladshkerin.services.UserService;

/**
 * The class to run a single operation.
 *
 * @author Vladimir Shkerin
 * @since 01.06.2016
 */
public class TaskWorker implements Runnable {

    private Operation operation;

    public TaskWorker(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void run() {
        if (Operation.LOAD_USERS.equals(operation)) {
            UserService.getInstance().loadFile();
        } else if (Operation.LOAD_ITEMS.equals(operation)) {
            ItemService.getInstance().loadFile();
        } else if (Operation.SAVE_USERS.equals(operation)) {
            UserService.getInstance().saveFile();
        } else if (Operation.SAVE_ITEMS.equals(operation)) {
            ItemService.getInstance().saveFile();
        }
    }
}