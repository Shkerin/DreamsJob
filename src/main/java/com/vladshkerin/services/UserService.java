package com.vladshkerin.services;

import com.vladshkerin.models.User;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TODO: comment
 *
 * @author Vladimir Shkerin
 * @since 13.03.2016
 */
public class UserService {

    private static final UserService instance = new UserService();

    private final List<User> users = new CopyOnWriteArrayList<>();

    private UserService() {
        GregorianCalendar cal1 = new GregorianCalendar(1985, 1, 2);
        GregorianCalendar cal2 = new GregorianCalendar(1990, 5, 6);
        String[] children1 = new String[]{"Ivan", "Oleg"};
        String[] children2 = new String[]{"Maria", "Eva"};

        users.add(new User("1", "Petr", 176.6f, cal1, children1));
        users.add(new User("2", "Make", 150f, cal2, children2));
        users.add(new User("3", "Olga"));
        users.add(new User("4", "Vlad"));
        users.add(new User("5", "Step"));
    }

    public static UserService getInstance() {
        return instance;
    }

    public List<User> getAll() {
        return this.users;
    }

    public void add(final User user) {
        this.users.add(user);
    }
}
