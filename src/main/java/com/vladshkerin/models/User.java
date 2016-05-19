package com.vladshkerin.models;

import com.vladshkerin.enums.UserRole;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.PatternSyntaxException;

/**
 * The class to store the users id and name.
 *
 * @author Vladimir Shkerin
 * @since 13.04.2016
 */
public class User {

    /* For generating item ID */
    private static long itemNumber;
    private static synchronized long nextItemID() {
        return ++itemNumber;
    }

    private long id;
    private String name;
    private Float growth;
    private Calendar birthDay;
    private String email;
    private String[] children;
    private Role role;

    public static void main(String[] args) {
        Role roleAdmin = new Role(UserRole.ADMIN);
        Role roleUser = new Role(UserRole.USER);
        Calendar cal = new GregorianCalendar(1980, 5, 10);
        String[] child = new String[]{"Sviatoslav", "Eva"};

        // Test constructors and clone()
        User user00 = null;
        User user11 = new User("User11");
        User user21 = user11;
        User user31 = user21;
        User user44 = new User("User44", roleUser, 22.5f, cal, "user1@mail.ru", child);
        User user54 = new User("User54", roleUser, 22.5f, cal, "user1@mail.ru", child);
        User user66 = new User("User66", roleAdmin, 0f, new GregorianCalendar(), "", new String[]{});

        // Test method toString()
        System.out.println("Test method toString():");
        System.out.println("user00: " + user00);
        System.out.println("user11: " + user11);
        System.out.println("user21: " + user21);
        System.out.println("user31: " + user31);
        System.out.println("user44: " + user44);
        System.out.println("user54: " + user54);
        System.out.println("user66: " + user66);

        // Test method equals()
        System.out.println("\nTest method equals():");
        System.out.println("TRUE: user11.equals(user11) = " + user11.equals(user11));
        System.out.println("TRUE: user11.equals(user21) = " + user11.equals(user21));
        System.out.println("TRUE: user21.equals(user11) = " + user21.equals(user11));
        System.out.println("TRUE: user21.equals(user31) = " + user21.equals(user31));
        System.out.println("TRUE: user31.equals(user11) = " + user31.equals(user11));
        System.out.println("FALSE: user11.equals(user00) = " + user11.equals(user00));
        System.out.println("FALSE: user44.equals(user54) = " + user44.equals(user54));
        System.out.println("FALSE: user11.equals(user44) = " + user11.equals(user44));

        // Test method hashCode()
        System.out.println("\nTest method hashCode():");
        System.out.println("TRUE: user11.hashCode() == user11.hashCode() = "
                + (user11.hashCode() == user11.hashCode()));
        System.out.println("TRUE: user11.hashCode() == user21.hashCode() = "
                + (user11.hashCode() == user21.hashCode()));
        System.out.println("TRUE: user21.hashCode() == user11.hashCode() = "
                + (user21.hashCode() == user11.hashCode()));
        System.out.println("TRUE: user31.hashCode() == user11.hashCode() = "
                + (user31.hashCode() == user11.hashCode()));
        System.out.println("FALSE: user44.hashCode() == user54.hashCode() = "
                + (user44.hashCode() == user54.hashCode()));
        System.out.println("FALSE: user11.hashCode() == user44.hashCode() = "
                + (user11.hashCode() == user44.hashCode()));
    }

    //TODO to delete
    public static String getNextId() {
        return String.format("%09d", itemNumber + 1);
    }

    public User(String name, Role role, Float growth, Calendar birthDay, String email, String[] children) {
        this.id = nextItemID();
        this.role = role;
        this.name = name;
        this.growth = growth;
        this.birthDay = birthDay;
        this.email = email;
        this.children = children;
    }

    public User(String name) {
        this(name, new Role(UserRole.USER), 0f, new GregorianCalendar(0, 0, 0), "", new String[]{});
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String strBirthDay = dateFormat.format(birthDay.getTime());

        return getClass().getName() +
                "{id=" + id +
                ", name=" + name +
                ", role" + role.toString() +
                ", growth=" + growth +
                ", birthDay=" + strBirthDay +
                ", email=" + email +
                ", children=" + Arrays.toString(children) +
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

        User other = (User) obj;
        return Objects.equals(id, other.id);
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getGrowth() {
        return this.growth;
    }

    public String getGrowthStr() {
        return String.valueOf(this.growth);
    }

    public void setGrowth(Float growth) {
        this.growth = growth;
    }

    public void setGrowth(String growth) {
        if (growth != null) {
            Float growthBuf = this.growth;
            try {
                this.growth = Float.parseFloat(growth);
            } catch (Exception e) {
                this.growth = growthBuf;
            }
        }
    }

    public Calendar getBirthDay() {
        return this.birthDay;
    }

    public String getBirthDayStr() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(this.birthDay.getTime());
    }

    public String getBirthDayStr(String pattern) {
        if (pattern == null || pattern.isEmpty())
            pattern = "dd.MM.yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        if (this.birthDay.getTimeInMillis() > 0)
            return format.format(this.birthDay.getTime());
        else
            return "";
    }

    public void setBirthDay(Calendar birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthDay(String birthDay) {
        if (birthDay != null) {
            Calendar birthDayBuf = this.birthDay;
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(birthDay.trim());
                this.birthDay.setTime(date);
            } catch (ParseException e) {
                this.birthDay = birthDayBuf;
            }
        }
    }

    public String[] getChildren() {
        return this.children;
    }

    public String getChildrenStr() {
        StringBuilder sbChildren = new StringBuilder();
        for (String child : this.children)
            sbChildren.append(child).append(", ");
        if (sbChildren.length() > 0)
            sbChildren.delete(sbChildren.length() - 2, sbChildren.length());
        return sbChildren.toString();
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setChildren(String[] children) {
        ArrayList<String> list = new ArrayList<>();
        for (String child : children) {
            try {
                Collections.addAll(list, parseStr(child));
            } catch (PatternSyntaxException e) {
                //TODO add output to log
                System.out.println(e.getMessage());
            }
        }
        this.children = list.toArray(new String[list.size()]);
    }

    public void setChildren(String children) {
        if (children != null) {
            String[] childrenBuf = this.children;
            try {
                this.children = parseStr(children);
            } catch (PatternSyntaxException e) {
                this.children = childrenBuf;
            }
        }
    }

    public Role getRole() {
        return role;
    }

    protected void setRole(Role role) {
        this.role = role;
    }

    private String[] parseStr(String str) throws PatternSyntaxException {
        return str.trim().split("\\s+|,\\s*");
    }
}
