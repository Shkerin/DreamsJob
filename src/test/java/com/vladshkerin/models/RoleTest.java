package com.vladshkerin.models;

import com.vladshkerin.enums.UserRole;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The test for class Role.
 *
 * @author Vladimir Shkerin
 * @since 09.06.2016
 */
public class RoleTest {

    private final Role roleUserNull = null;
    private final Role roleUser1 = new Role();
    private final Role roleUser2 = new Role(UserRole.USER);
    private final Role roleUser3 = roleUser2;
    private final Role roleAdmin1 = new Role(UserRole.ADMIN);
    private final Role roleAdmin2 = new Role(UserRole.ADMIN);

    @Test
    public void testGetUserRole() {
        assertTrue(roleUser1.getUserRole() == UserRole.USER);
        assertTrue(roleAdmin1.getUserRole() == UserRole.ADMIN);

        assertFalse(roleUser1.getUserRole() == roleAdmin1.getUserRole());
    }

    @Test
    public void testIsRoleAdmin() {
        assertTrue(roleAdmin1.isRoleAdmin());
        assertFalse(roleUser1.isRoleAdmin());
    }

    @Test
    public void testIsRoleUser() {
        assertTrue(roleUser1.isRoleUser());
        assertFalse(roleAdmin1.isRoleUser());
    }

    @Test
    public void testEquals() {
        assertNull(roleUserNull);

        assertTrue(roleUser1.equals(roleUser1));
        assertTrue(roleUser1.equals(roleUser2));
        assertTrue(roleUser2.equals(roleUser1));
        assertTrue(roleUser3.equals(roleUser2));
        assertTrue(roleAdmin1.equals(roleAdmin2));

        assertFalse(roleUser1.equals(roleUserNull));
        assertFalse(roleUser1.equals(roleAdmin2));
    }

    @Test
    public void testHashCode() {
        assertTrue(roleUser1.hashCode() == roleUser1.hashCode());
        assertTrue(roleUser1.hashCode() == roleUser2.hashCode());
        assertTrue(roleUser2.hashCode() == roleUser1.hashCode());
        assertTrue(roleUser3.hashCode() == roleUser2.hashCode());
        assertTrue(roleAdmin1.hashCode() == roleAdmin2.hashCode());

        assertFalse(roleUser1.hashCode() == roleAdmin2.hashCode());
    }

    @Test
    public void testGetName() {
        assertEquals(roleUser1.getName(), UserRole.USER.toString());
        assertEquals(roleAdmin1.getName(), UserRole.ADMIN.toString());
    }
}
