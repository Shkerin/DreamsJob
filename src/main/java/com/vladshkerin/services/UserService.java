package com.vladshkerin.services;

import com.vladshkerin.enums.UserRole;
import com.vladshkerin.exception.NotFoundUser;
import com.vladshkerin.models.Role;
import com.vladshkerin.models.User;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The class to control users.
 *
 * @author Vladimir Shkerin
 * @since 13.03.2016
 */
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final List<User> users = new CopyOnWriteArrayList<>();

    private UserService() {
//        Role roleAdmin = new Role(UserRole.ADMIN);
//        Role roleUser = new Role(UserRole.USER);
//        GregorianCalendar calendar1 = new GregorianCalendar(1970, 3, 4);
//        GregorianCalendar calendar2 = new GregorianCalendar(1985, 5, 6);
//        GregorianCalendar calendar3 = new GregorianCalendar(1995, 7, 8);
//        String[] children1 = new String[]{"Ivan"};
//        String[] children2 = new String[]{"Sviatoslav", "Eva"};
//        String[] children3 = new String[]{"Maria", "Sergio", "Irina"};
//
//        users.add(new User("admin", "admin", roleAdmin, 145f, calendar1, "admin@admin.ru", children1));
//        users.add(new User("user", "user", roleUser, 145f, calendar1, "vlad@admin.ru", children1));
//        users.add(new User("Elena", "", roleUser, 150f, calendar2, "elena@mail.ru", children2));
//        users.add(new User("Sviatoslav", "", roleUser, 160f, calendar3, "sviat@ya.ru", children3));
//        users.add(new User("Nikita"));
//        users.add(new User("Olga"));
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    public List<User> getAll() {
        return users;
    }

    public User get(long id) throws NotFoundUser {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new NotFoundUser("User not fount by id: " + id);
    }

    public User get(String name) throws NotFoundUser {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new NotFoundUser("User not fount by name: " + name);
    }

    public User get(String name, String password) throws NotFoundUser {
        for (User user : users) {
            if (name.equals(user.getName())) {
                if (password == null || password.equals(user.getPassword())) {
                    return user;
                }
            }
        }
        throw new NotFoundUser("User not fount by name: " + name);
    }

    public void add(final User user) {
        users.add(user);
    }

    public void delete(long id) {
        try {
            users.remove(get(id));
        } catch (NotFoundUser e) {
            //TODO add out to log
            System.out.println(e.getMessage());
        }
    }

    public void clear() {
        users.clear();
    }

    public String validateForm(Map<String, String> userPropertiesMap) {
        StringBuilder errorValues = new StringBuilder();
        for (Map.Entry<String, String> user : userPropertiesMap.entrySet()) {

            String key = user.getKey();
            key = key.substring(0, 1).toUpperCase() + key.substring(1, key.length());

            if (user.getValue().isEmpty()) {
                errorValues.append(key).append(", ");
            } else if ("growth".equals(key)) {
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

    public void saveFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/tmp/users.txt", false));
            for (User user : getAll()) {
                if (user.getName().isEmpty()) {
                    continue;
                }
                String id = String.valueOf(user.getId());
                String name = user.getName();
                String password = user.getPassword();
                String role = String.valueOf(user.getRole().getUserRole());
                String growth = user.getGrowthStr();
                String birthDay = user.getBirthDayStr("yyyy-MM-dd");
                String email = user.getEmail();
                String children = user.getChildrenStr();

                writer.write(id + "/");
                writer.write(name + "/");
                writer.write(password.isEmpty() ? " /" : password + "/");
                writer.write(role.isEmpty() ? " /" : role + "/");
                writer.write(growth.isEmpty() ? " /" : growth + "/");
                writer.write(birthDay.isEmpty() ? " /" : birthDay + "/");
                writer.write(email.isEmpty() ? " /" : email + "/");
                writer.write(children.isEmpty() ? " \n" : children + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            //TODO out to log
            ex.printStackTrace();
        }
    }

    public void loadFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/tmp/users.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                makeUser(line);
            }
        } catch (IOException ex) {
            //TODO out to log
            ex.printStackTrace();
        }
    }

    private void makeUser(String lineToParse) {
        String[] result = lineToParse.split("/");

        User user;
        try {
            user = get(Long.valueOf(result[0]));
        } catch (NotFoundUser ex) {
            user = new User();
            users.add(user);
        }
        user.setName(result[1].trim());
        user.setPassword(!result[2].trim().isEmpty() ? result[2] : "");
        user.setRole(new Role("admin".equals(result[3]) ? UserRole.ADMIN : UserRole.USER));
        user.setGrowth(Float.valueOf(result[4].trim()));
        user.setBirthDay(result[5].trim(), "yyyy-MM-dd");
        user.setEmail(result[6].trim());
        user.setChildren(result[7].trim());
    }
}
