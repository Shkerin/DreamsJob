package com.vladshkerin.models;

import com.vladshkerin.services.TestObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The class to store items user.
 *
 * @author Vladimir Shkerin
 * @since 09.05.2016
 */
public class Item {

    private static AtomicLong itemNumber = new AtomicLong();

    private long id;
    private long parentId;
    private User user;
    private String name;
    private String desc;

    public Item(long parentId, User user, String name, String desc) {
        this.id = itemNumber.incrementAndGet();
        this.parentId = parentId;
        this.user = user;
        this.name = name;
        this.desc = desc;
    }

    public Item(User user) {
        this(0L, user, "", "");
    }

    public Item() {
        this(0L, new User(), "", "");
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "{id=" + id +
                ", parent_id=" + parentId +
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

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
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

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        TestObject testObj = new TestObject();
        ArrayList<String> list = new ArrayList();
        LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();

        User user1 = new User("user1");
        User user2 = new User("user2");

        System.out.println("***************************************");
        System.out.println("*               Item.java             *");
        System.out.println("***************************************");

        // Test constructors and clone()
        Item item00 = null;
        Item item11 = new Item(user1);
        Item item21 = item11;
        Item item31 = item21;
        Item item44 = new Item(0, user2, "two name", "two desc");
        Item item54 = new Item(1, user2, "two name", "two desc");
        Item item66 = new Item(2, new User("temp"), "", "");

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
}
