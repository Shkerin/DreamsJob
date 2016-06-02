package com.vladshkerin.models;

import com.vladshkerin.enums.UserRole;

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
