package com.vladshkerin.exception;

/**
 * The exception class for lack of user.
 *
 * @author Vladimir Shkerin
 * @since 13.03.2016
 */
public class NotFoundUser extends Exception {

    public NotFoundUser(String message) {
        super(message);
    }

    public NotFoundUser(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundUser(Throwable cause) {
        super(cause);
    }
}
