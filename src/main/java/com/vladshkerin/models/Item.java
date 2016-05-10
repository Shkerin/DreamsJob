package com.vladshkerin.models;

import java.util.Objects;

/**
 * Class to store items user.
 */
public class Item {

    private static Integer itemCount = 0;

    private String id;
    private User user;
    private String name;
    private String desc;

    public Item(User user, String name, String desc) {
        this.id = generatedId();
        this.user = user;
        this.name = name;
        this.desc = desc;
    }

    public Item(String name) {
        this(new User("temp"), name, "");
    }

    @Override
    public String toString() {
        return getClass().getName() + '[' +
                "user=" + user +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' + ']';
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, name, desc);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Item other = (Item) obj;
        return Objects.equals(user, other.user) &&
                Objects.equals(name, other.name) &&
                Objects.equals(desc, other.desc);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static String getNextId() {
        return String.format("%09d", itemCount + 1);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    private String generatedId() {
        return String.format("%d09", ++itemCount);
    }
}
