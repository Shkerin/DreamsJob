package com.vladshkerin.models;

/**
 * The class to filter the display of items.
 *
 * @author Vladimir Shkerin
 * @since 02.06.2016
 */
public class Filter {

    private User user;

    public Filter(User user) {
        this.user = user;
    }

    public Filter() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
