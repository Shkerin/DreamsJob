package com.vladshkerin.models;

import com.vladshkerin.enums.UserRole;
import com.vladshkerin.services.TestObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * The class for user role.
 *
 * @author Vladimir Shkerin
 * @since 10.05.2016
 */
public class Role {

    private UserRole userRole;

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        TestObject testObj = new TestObject();
        ArrayList<String> list = new ArrayList();
        LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();

        System.out.println("***************************************");
        System.out.println("*               Item.java             *");
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

    public Role(UserRole userRole) {
        this.userRole = userRole;
    }

    public Role() {
        this.userRole = UserRole.USER;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public boolean isRoleAdmin() {
        return userRole == UserRole.ADMIN;
    }

    public boolean isRoleUser() {
        return userRole == UserRole.USER;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "{userRole=" + userRole +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Role other = (Role) obj;
        return Objects.equals(userRole, other.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRole);
    }

    public String getName() {
        return userRole.toString();
    }
}
