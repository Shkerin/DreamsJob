package com.vladshkerin.services;

import com.vladshkerin.enums.UserRole;
import com.vladshkerin.exception.NotFoundUser;
import com.vladshkerin.models.Role;
import com.vladshkerin.models.User;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class for control users.
 *
 * @author Vladimir Shkerin
 * @since 13.03.2016
 */
public class UserService {

    private static final UserService instance = new UserService();

    private final List<User> users = new CopyOnWriteArrayList<>();

    private UserService() {
        Role roleAdmin = new Role(UserRole.ADMIN);
        Role roleUser = new Role(UserRole.USER);
        GregorianCalendar calendar1 = new GregorianCalendar(1970, 3, 4);
        GregorianCalendar calendar2 = new GregorianCalendar(1985, 5, 6);
        GregorianCalendar calendar3 = new GregorianCalendar(1995, 7, 8);
        String[] children1 = new String[]{"Ivan"};
        String[] children2 = new String[]{"Sviatoslav", "Eva"};
        String[] children3 = new String[]{"Maria", "Sergio", "Irina"};

        users.add(new User("admin", roleAdmin, 145f, calendar1, "admin@admin.ru", children1));
        users.add(new User("user", roleUser, 145f, calendar1, "vlad@admin.ru", children1));
        users.add(new User("Elena", roleUser, 150f, calendar2, "elena@mail.ru", children2));
        users.add(new User("Sviatoslav", roleUser, 160f, calendar3, "sviat@ya.ru", children3));
        users.add(new User("Nikita"));
        users.add(new User("Olga"));
    }

    public static UserService getInstance() {
        return instance;
    }

    public List<User> getAll() {
        return users;
    }

    public void add(final User user) {
        users.add(user);
    }

    public User get(long id) throws NotFoundUser {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new NotFoundUser("User not fount to \"id\"" + id);
    }

    public User getToName(String name) throws NotFoundUser {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new NotFoundUser("User not fount to \"name\"" + name);
    }

    public void delete(long id) {
        try {
            users.remove(this.get(id));
        } catch (NotFoundUser e) {
            //TODO add out to log
            System.out.println(e.getMessage());
        }
    }

    public String validateForm(Map<String, String> userPropertiesMap) {
        StringBuilder errorValues = new StringBuilder();
        for (Map.Entry<String, String> user : userPropertiesMap.entrySet()) {

            String key = user.getKey();
            key = key.substring(0, 1).toUpperCase() + key.substring(1, key.length());

            if (user.getValue().isEmpty()) {
                errorValues.append(key).append(", ");
            } else if (key.equals("growth")) {
                if (Integer.parseInt(user.getValue()) < 1 ||
                        Integer.parseInt(user.getValue()) > 200)
                    errorValues.append(key).append(", ");
            }

        }
        if (!errorValues.toString().isEmpty()) {
            errorValues.replace(errorValues.length() - 2, errorValues.length(), "");
        }
        return errorValues.toString();
    }
}
