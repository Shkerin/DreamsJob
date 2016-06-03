package com.vladshkerin.models;

import com.vladshkerin.enums.UserRole;
import com.vladshkerin.services.ItemService;
import com.vladshkerin.services.UserService;

import java.util.*;

/**
 * The class for testing models.
 *
 * @author Vladimir Shkerin
 * @since 19.05.2016
 */
public class TestObject {

    private ArrayList<String> errorList;

    public TestObject() {
        this.errorList = new ArrayList<>();
    }

    public static void main(String[] args) {
//        testRole();
//        testUser();
//        testItem();
        testSaveLoadDate();
    }

    public static void testRole() {
        TestObject testObj = new TestObject();
        ArrayList<String> list = new ArrayList();
        LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();

        System.out.println("***************************************");
        System.out.println("*                 Item                *");
        System.out.println("***************************************");

        // Test constructors and clone()
        Role role0 = null;
        Role role1 = new Role(UserRole.USER);
        Role role2 = role1;
        Role role3 = role2;
        Role role4 = new Role(UserRole.ADMIN);
        Role role5 = new Role(UserRole.ADMIN);

        // Test method toString()
        System.out.println("Test method toString():");
        list.add("role0: " + role0);
        list.add("role1: " + role1);
        list.add("role2: " + role2);
        list.add("role3: " + role3);
        list.add("role4: " + role4);
        list.add("role5: " + role5);
        testObj.testToString(list);

        // Test method equals()
        System.out.print("\nTest method equals(): ");
        map.put("TRUE: role1.equals(role1)", role1.equals(role1));
        map.put("TRUE: role1.equals(role2)", role1.equals(role2));
        map.put("TRUE: role2.equals(role1)", role2.equals(role1));
        map.put("TRUE: role2.equals(role3)", role2.equals(role3));
        map.put("TRUE: role3.equals(role1)", role3.equals(role1));
        map.put("TRUE: role4.equals(role5)", role4.equals(role5));
        map.put("FALSE: role1.equals(role0)", !role1.equals(role0));
        map.put("FALSE: role1.equals(role4)", !role1.equals(role4));
        testObj.testEquals(map);

        // Test method hashCode()
        System.out.print("\nTest method hashCode(): ");
        map.clear();
        map.put("TRUE: role1.hashCode() == role1.hashCode()", role1.hashCode() == role1.hashCode());
        map.put("TRUE: role1.hashCode() == role2.hashCode()", role1.hashCode() == role2.hashCode());
        map.put("TRUE: role2.hashCode() == role1.hashCode()", role2.hashCode() == role1.hashCode());
        map.put("TRUE: role3.hashCode() == role1.hashCode()", role3.hashCode() == role1.hashCode());
        map.put("TRUE: role4.hashCode() == role5.hashCode()", role4.hashCode() == role5.hashCode());
        map.put("FALSE: role1.hashCode() == role4.hashCode()", !(role1.hashCode() == role4.hashCode()));
        testObj.testHashCode(map);

        testObj.printError();
    }

    public static void testUser() {
        TestObject testObj = new TestObject();
        ArrayList<String> list = new ArrayList();
        LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();

        Role roleAdmin = new Role(UserRole.ADMIN);
        Role roleUser = new Role(UserRole.USER);
        Calendar cal = new GregorianCalendar(1980, 5, 10);
        String[] child = new String[]{"Sviatoslav", "Eva"};

        System.out.println("***************************************");
        System.out.println("*                 User                *");
        System.out.println("***************************************");

        // Test constructors and clone()
        User user00 = null;
        User user11 = new User("User11");
        User user21 = user11;
        User user31 = user21;
        User user44 = new User("User44", "", roleUser, 22.5f, cal, "user1@mail.ru", child);
        User user54 = new User("User54", "", roleUser, 22.5f, cal, "user1@mail.ru", child);
        User user66 = new User("User66", "", roleAdmin, 0f, new GregorianCalendar(), "", new String[]{});

        // Test method toString()
        System.out.println("Test method toString():");
        list.add("user00: " + user00);
        list.add("user11: " + user11);
        list.add("user21: " + user21);
        list.add("user31: " + user31);
        list.add("user44: " + user44);
        list.add("user54: " + user54);
        list.add("user66: " + user66);
        testObj.testToString(list);

        // Test method equals()
        System.out.print("\nTest method equals(): ");
        map.put("true: user11.equals(user11)", user11.equals(user11));
        map.put("true: user11.equals(user21)", user11.equals(user21));
        map.put("true: user21.equals(user11)", user21.equals(user11));
        map.put("true: user21.equals(user31)", user21.equals(user31));
        map.put("true: user31.equals(user11)", user31.equals(user11));
        map.put("false: user11.equals(user00)", !user11.equals(user00));
        map.put("false: user44.equals(user54)", !user44.equals(user54));
        map.put("false: user11.equals(user44)", !user11.equals(user44));
        testObj.testEquals(map);

        // Test method hashCode()
        System.out.print("\nTest method hashCode(): ");
        map.clear();
        map.put("true: user11.hashCode() == user11.hashCode()", (user11.hashCode() == user11.hashCode()));
        map.put("true: user11.hashCode() == user21.hashCode()", (user11.hashCode() == user21.hashCode()));
        map.put("true: user21.hashCode() == user11.hashCode()", (user21.hashCode() == user11.hashCode()));
        map.put("true: user31.hashCode() == user11.hashCode()", (user31.hashCode() == user11.hashCode()));
        map.put("false: user44.hashCode() == user54.hashCode()", !(user44.hashCode() == user54.hashCode()));
        map.put("false: user11.hashCode() == user44.hashCode()", !(user11.hashCode() == user44.hashCode()));
        testObj.testHashCode(map);

        testObj.printError();
    }

    public static void testItem() {
        TestObject testObj = new TestObject();
        ArrayList<String> list = new ArrayList();
        LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();

        User user1 = new User("user1");
        User user2 = new User("user2");

        System.out.println("***************************************");
        System.out.println("*                 Item                *");
        System.out.println("***************************************");

        // Test constructors and clone()
        Item item00 = null;
        Item item11 = new Item(user1);
        Item item21 = item11;
        Item item31 = item21;
        Item item44 = new Item(0, user2, "two name", "two desc", new GregorianCalendar(2015, 0, 1));
        Item item54 = new Item(1, user2, "two name", "two desc", new GregorianCalendar(2016, 1, 2));
        Item item66 = new Item(2, new User("temp"), "", "", new GregorianCalendar(2017, 2, 3));

        // Test method toString()
        System.out.println("Test method toString():");
        list.add("item00: " + item00);
        list.add("item11: " + item11);
        list.add("item21: " + item21);
        list.add("item31: " + item31);
        list.add("item44: " + item44);
        list.add("item54: " + item54);
        list.add("item66: " + item66);
        testObj.testToString(list);

        // Test method equals()
        System.out.print("\nTest method equals(): ");
        map.put("TRUE: item11.equals(item11)", item11.equals(item11));
        map.put("TRUE: item11.equals(item21)", item11.equals(item21));
        map.put("TRUE: item21.equals(item11)", item21.equals(item11));
        map.put("TRUE: item21.equals(item31)", item21.equals(item31));
        map.put("TRUE: item31.equals(item11)", item31.equals(item11));
        map.put("FALSE: item11.equals(item00)", !item11.equals(item00));
        map.put("FALSE: item44.equals(item54)", !item44.equals(item54));
        map.put("FALSE: item11.equals(item44)", !item11.equals(item44));
        testObj.testEquals(map);

        // Test method hashCode()
        System.out.print("\nTest method hashCode(): ");
        map.clear();
        map.put("TRUE: item11.hashCode() == item11.hashCode()", item11.hashCode() == item11.hashCode());
        map.put("TRUE: item11.hashCode() == item21.hashCode()", item11.hashCode() == item21.hashCode());
        map.put("TRUE: item21.hashCode() == item11.hashCode()", item21.hashCode() == item11.hashCode());
        map.put("TRUE: item31.hashCode() == item11.hashCode()", item31.hashCode() == item11.hashCode());
        map.put("FALSE: item44.hashCode() == item54.hashCode()", !(item44.hashCode() == item54.hashCode()));
        map.put("FALSE: item11.hashCode() == item44.hashCode()", !(item11.hashCode() == item44.hashCode()));
        testObj.testHashCode(map);

        testObj.printError();
    }

    public static void testSaveLoadDate() {
        TestObject testObj = new TestObject();

        System.out.println("***************************************");
        System.out.println("           Save and load date         *");
        System.out.println("***************************************");

        // Test save and load users
        try {
            Role roleAdmin = new Role(UserRole.ADMIN);
            Role roleUser = new Role(UserRole.USER);
            GregorianCalendar calendar1 = new GregorianCalendar(1970, 3, 4);
            GregorianCalendar calendar2 = new GregorianCalendar(1985, 5, 6);
            GregorianCalendar calendar3 = new GregorianCalendar(1995, 7, 8);
            String[] children1 = new String[]{"Ivan"};
            String[] children2 = new String[]{"Sviatoslav", "Eva"};
            String[] children3 = new String[]{"Maria", "Sergio", "Irina"};

            List<User> users = new ArrayList<>();
            users.add(new User("admin", "admin", roleAdmin, 145f, calendar1, "admin@admin.ru", children1));
            users.add(new User("user", "user", roleUser, 145f, calendar1, "vlad@admin.ru", children1));
            users.add(new User("Elena", "", roleUser, 150f, calendar2, "elena@mail.ru", children2));
            users.add(new User("Sviatoslav", "", roleUser, 160f, calendar3, "sviat@ya.ru", children3));
            users.add(new User("Nikita"));
            users.add(new User("Olga"));

            for (User user : users) {
                UserService.getInstance().add(user);
            }

            UserService.getInstance().saveFile();
            UserService.getInstance().loadFile();
        } catch (Exception ex) {
            testObj.errorList.add("SaveLoad users");
        }

        // Test save and load items
        try {
            User user = UserService.getInstance().get("user");
            User admin = UserService.getInstance().get("admin");

            List<Item> items = new ArrayList<>();
            items.add(new Item(0, user, "1 item", "user", new GregorianCalendar(2001, 0, 1)));
            items.add(new Item(1, user, "2 item", "user", new GregorianCalendar(2002, 1, 2)));
            items.add(new Item(2, user, "3 item", "user", new GregorianCalendar(2003, 2, 3)));
            items.add(new Item(2, admin, "4 item", "admin", new GregorianCalendar(2004, 3, 4)));
            items.add(new Item(1, user, "5 item", "user", new GregorianCalendar(2005, 4, 5)));
            items.add(new Item(1, user, "6 item", "user", new GregorianCalendar(2006, 5, 6)));
            items.add(new Item(0, admin, "7 item", "admin", new GregorianCalendar(2007, 6, 7)));

            for (Item item : items) {
                ItemService.getInstance().add(item);
            }

            ItemService.getInstance().saveFile();
            ItemService.getInstance().loadFile();

        } catch (Exception ex) {
            testObj.errorList.add("SaveLoad items");
        }

        ItemService.getInstance().clear();
        UserService.getInstance().clear();

        testObj.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (errorList.size() > 0) {
            sb.append("\n//////////////// ERROR ////////////////");
            for (String str : errorList) {
                sb.append("\n").append(str);
            }
            sb.append("///////////////////////////////////////");
        }
        return sb.toString();
    }

    private void testToString(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
    }

    private void testEquals(Map<String, Boolean> map) {
        String result = testOut(map);
        if (!result.isEmpty()) {
            errorList.add(result);
            System.out.println("FAIL");
        } else {
            System.out.println("ok");
        }
    }

    private void testHashCode(Map<String, Boolean> map) {
        String result = testOut(map);
        if (!result.isEmpty()) {
            errorList.add(result);
            System.out.println("FAIL");
        } else {
            System.out.println("ok");
        }
    }

    private void printError() {
        System.out.print(toString());
    }

    private static String testOut(Map<String, Boolean> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (!entry.getValue()) {
                sb.append(entry.getKey()).append("\n");
            }
        }
        return sb.length() == 0 ? "" : sb.toString();
    }
}
