package com.vladshkerin.models;

import com.vladshkerin.enums.UserRole;

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
        // Test constructors and clone()
        Role role0 = null;
        Role role1 = new Role(UserRole.USER);
        Role role2 = role1;
        Role role3 = role2;
        Role role4 = new Role(UserRole.ADMIN);
        Role role5 = new Role(UserRole.ADMIN);

        // Test method toString()
        System.out.println("Test method toString():");
        System.out.println("role0: " + role0);
        System.out.println("role1: " + role1);
        System.out.println("role2: " + role2);
        System.out.println("role3: " + role3);
        System.out.println("role4: " + role4);
        System.out.println("role5: " + role5);

        // Test method equals()
        System.out.println("\nTest method equals():");
        System.out.println("TRUE: role1.equals(role1) = " + role1.equals(role1));
        System.out.println("TRUE: role1.equals(role2) = " + role1.equals(role2));
        System.out.println("TRUE: role2.equals(role1) = " + role2.equals(role1));
        System.out.println("TRUE: role2.equals(role3) = " + role2.equals(role3));
        System.out.println("TRUE: role3.equals(role1) = " + role3.equals(role1));
        System.out.println("TRUE: role4.equals(role5) = " + role4.equals(role5));
        System.out.println("FALSE: role1.equals(role0) = " + role1.equals(role0));
        System.out.println("FALSE: role1.equals(role4) = " + role1.equals(role4));

        // Test method hashCode()
        System.out.println("\nTest method hashCode():");
        System.out.println("TRUE: role1.hashCode() == role1.hashCode() = "
                + (role1.hashCode() == role1.hashCode()));
        System.out.println("TRUE: role1.hashCode() == role2.hashCode() = "
                + (role1.hashCode() == role2.hashCode()));
        System.out.println("TRUE: role2.hashCode() == role1.hashCode() = "
                + (role2.hashCode() == role1.hashCode()));
        System.out.println("TRUE: role3.hashCode() == role1.hashCode() = "
                + (role3.hashCode() == role1.hashCode()));
        System.out.println("TRUE: role4.hashCode() == role5.hashCode() = "
                + (role4.hashCode() == role5.hashCode()));
        System.out.println("FALSE: role1.hashCode() == role4.hashCode() = "
                + (role1.hashCode() == role4.hashCode()));
    }

    public Role(UserRole userRole) {
        this.userRole = userRole;
    }

    public Role() {
        this.userRole = UserRole.USER;
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
