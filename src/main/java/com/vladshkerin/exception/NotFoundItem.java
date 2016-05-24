package com.vladshkerin.exception;

/**
 * The exception class for lack of item.
 *
 * @author Vladimir Shkerin
 * @since 24.05.2016
 */
public class NotFoundItem extends Exception {

    public NotFoundItem(String message) {
        super(message);
    }

    public NotFoundItem(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundItem(Throwable cause) {
        super(cause);
    }
}
