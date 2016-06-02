package com.vladshkerin.exception;

/**
 * The exception class for lack of filter.
 *
 * @author Vladimir Shkerin
 * @since 02.06.2016
 */
public class NotFoundFilter extends Exception {

    public NotFoundFilter(String message) {
        super(message);
    }

    public NotFoundFilter(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundFilter(Throwable cause) {
        super(cause);
    }
}
