package com.vladshkerin.models;

import com.vladshkerin.enums.UserRole;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * The test for class User.
 *
 * @author Vladimir Shkerin
 * @since 09.06.2016
 */
public class UserTest {

    private final Role roleAdmin = new Role(UserRole.ADMIN);

    private final User user00 = null;
    private final User user11 = new User();
    private final User user21 = user11;
    private final User user31 = user21;
    private final User user44 = new User("User");
    private final User user54 = new User("User");
    private final User user66 = new User("User66", "", roleAdmin, 0f, new GregorianCalendar(), "", new String[]{});

    @Test
    public void testHashCode() {
        assertTrue(user11.hashCode() == user11.hashCode());
        assertTrue(user11.hashCode() == user21.hashCode());
        assertTrue(user21.hashCode() == user11.hashCode());
        assertTrue(user31.hashCode() == user11.hashCode());

        assertFalse(user44.hashCode() == user54.hashCode());
        assertFalse(user11.hashCode() == user44.hashCode());
    }

    @Test
    public void testEquals() {
        assertNull(user00);

        assertTrue(user11.equals(user11));
        assertTrue(user11.equals(user21));
        assertTrue(user21.equals(user11));
        assertTrue(user21.equals(user31));
        assertTrue(user31.equals(user11));

        assertFalse(user11.equals(user00));
        assertFalse(user44.equals(user54));
        assertFalse(user11.equals(user44));
    }

}