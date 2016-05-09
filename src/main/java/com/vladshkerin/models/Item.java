package com.vladshkerin.models;

import java.util.Objects;

/**
 * Class to store session user
 */
public class Item {

    private User user;
    private String name;
    private String desc;

    public Item(User user, String name, String desc) {
        this.user = user;
        this.name = name;
        this.desc = desc;
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

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
