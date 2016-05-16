package com.vladshkerin.models;

import java.util.Objects;

/**
 * The class to store items user.
 *
 * @author Vladimir Shkerin
 * @since 09.05.2016
 */
public class Item {

    /* For generating item ID */
    private static long itemNumber;
    private static synchronized long nextItemID() {
        return ++itemNumber;
    }

    private long id;
    private User user;
    private String name;
    private String desc;

    public static void main(String[] args) {
        User user1 = new User("user1");
        User user2 = new User("user2");

        // Test constructors and clone()
        Item item00 = null;
        Item item11 = new Item(user1);
        Item item21 = item11;
        Item item31 = item21;
        Item item44 = new Item(user2, "two name", "two desc");
        Item item54 = new Item(user2, "two name", "two desc");
        Item item66 = new Item(new User("temp"), "", "");

        // Test method toString()
        System.out.println("Test method toString():");
        System.out.println("item00: " + item00);
        System.out.println("item11: " + item11);
        System.out.println("item21: " + item21);
        System.out.println("item31: " + item31);
        System.out.println("item44: " + item44);
        System.out.println("item54: " + item54);
        System.out.println("item66: " + item66);

        // Test method equals()
        System.out.println("\nTest method equals():");
        System.out.println("TRUE: item11.equals(item11) = " + item11.equals(item11));
        System.out.println("TRUE: item11.equals(item21) = " + item11.equals(item21));
        System.out.println("TRUE: item21.equals(item11) = " + item21.equals(item11));
        System.out.println("TRUE: item21.equals(item31) = " + item21.equals(item31));
        System.out.println("TRUE: item31.equals(item11) = " + item31.equals(item11));
        System.out.println("FALSE: item11.equals(item00) = " + item11.equals(item00));
        System.out.println("FALSE: item44.equals(item54) = " + item44.equals(item54));
        System.out.println("FALSE: item11.equals(item44) = " + item11.equals(item44));

        // Test method hashCode()
        System.out.println("\nTest method hashCode():");
        System.out.println("TRUE: item11.hashCode() == item11.hashCode() = "
                + (item11.hashCode() == item11.hashCode()));
        System.out.println("TRUE: item11.hashCode() == item21.hashCode() = "
                + (item11.hashCode() == item21.hashCode()));
        System.out.println("TRUE: item21.hashCode() == item11.hashCode() = "
                + (item21.hashCode() == item11.hashCode()));
        System.out.println("TRUE: item31.hashCode() == item11.hashCode() = "
                + (item31.hashCode() == item11.hashCode()));
        System.out.println("FALSE: item44.hashCode() == item54.hashCode() = "
                + (item44.hashCode() == item54.hashCode()));
        System.out.println("FALSE: item11.hashCode() == item44.hashCode() = "
                + (item11.hashCode() == item44.hashCode()));
    }

    //TODO to delete
    public static synchronized String getNextID() {
        return String.format("%09d", itemNumber + 1);
    }

    public Item(User user, String name, String desc) {
        this.id = nextItemID();
        this.user = user;
        this.name = name;
        this.desc = desc;
    }

    public Item(User user) {
        this(user, "", "");
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "{id=" + id +
                ", user=" + user +
                ", name=" + name +
                ", desc=" + desc +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Item other = (Item) obj;
        return Objects.equals(id, other.id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
