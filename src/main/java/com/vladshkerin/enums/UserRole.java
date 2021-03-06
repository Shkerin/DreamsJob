package com.vladshkerin.enums;

/**
 * The enum for roles users.
 *
 * @author Vladimir Shkerin
 * @since 10.05.2016
 */
public enum UserRole {
    ADMIN,
    USER;

    @Override
    public String toString() {
        return this.equals(ADMIN) ? "admin" : "user";
    }
}
