package com.vladshkerin.services;

import com.vladshkerin.models.UserAdvance;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TODO: comment
 *
 * @author Vladimir Shkerin
 * @since 13.03.2016
 */
public class UserAdvanceService {

    private static final UserAdvanceService instance = new UserAdvanceService();

    private final List<UserAdvance> users = new CopyOnWriteArrayList<>();

    private UserAdvanceService() {
        GregorianCalendar cal1 = new GregorianCalendar(1985, 1, 2);
        GregorianCalendar cal2 = new GregorianCalendar(1990, 5, 6);
        String[] children1 = new String[]{"Ivan", "Oleg"};
        String[] children2 = new String[]{"Maria", "Eva"};

        users.add(new UserAdvance("1", "Petr", 176.6f, cal1, children1));
        users.add(new UserAdvance("2", "Make", 150f, cal2, children2));
        users.add(new UserAdvance("3", "Olga"));
        users.add(new UserAdvance("4", "Vlad"));
        users.add(new UserAdvance("5", "Step"));
    }

    public static UserAdvanceService getInstance() {
        return instance;
    }

    public List<UserAdvance> getAll() {
        return this.users;
    }

    public void add(final UserAdvance user) {
        this.users.add(user);
    }
}
