package com.vladshkerin.models;

import com.vladshkerin.enums.UserRole;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.PatternSyntaxException;

/**
 * The class to store the users id and name.
 *
 * @author Vladimir Shkerin
 * @since 13.04.2016
 */
public class User {

    private static AtomicLong itemNumber = new AtomicLong();

    private long id;
    private String name;
    private String password;
    private Role role;
    private Float growth;
    private Calendar birthDay;
    private String email;
    private String[] children;

    public User(String name, String password, Role role, Float growth, Calendar birthDay, String email, String[] children) {
        this.id = itemNumber.incrementAndGet();
        this.name = name;
        this.password = password;
        this.role = role;
        this.growth = growth;
        this.birthDay = birthDay;
        this.email = email;
        this.children = children;
    }

    public User(String name) {
        this(name, "", new Role(UserRole.USER), 0f, new GregorianCalendar(0, 0, 0), "", new String[]{});
    }

    public User() {
        this("", "", new Role(UserRole.USER), 0f, new GregorianCalendar(0, 0, 0), "", new String[]{});
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        return getBirthDayStr("dd.MM.yyyy");
    }

    public String getBirthDayStr(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            pattern = "dd.MM.yyyy";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        if (birthDay.getTimeInMillis() > 0) {
            return dateFormat.format(birthDay.getTime());
        } else {
            return "";
        }
    }

    public void setBirthDay(Calendar birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthDay(String birthDay) {
        setBirthDay(birthDay, "yyyy-MM-dd");
    }

    public void setBirthDay(String birthDay, String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            pattern = "yyyy-MM-dd";
        }
        if (birthDay != null) {
            Calendar birthDayBuf = this.birthDay;
            try {
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                Date date = format.parse(birthDay.trim());
                this.birthDay.setTime(date);
            } catch (ParseException e) {
                this.birthDay = birthDayBuf;
            }
        }
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getChildren() {
        return this.children;
    }

    public String getChildrenStr() {
        StringBuilder sb = new StringBuilder();
        for (String child : this.children)
            sb.append(child).append(", ");
        if (sb.length() > 0)
            sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public void setChildren(String[] children) {
        List<String> list = new ArrayList<>();
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
        if (children != null && !children.isEmpty()) {
            String[] childrenBuf = this.children;
            try {
                this.children = parseStr(children);
            } catch (PatternSyntaxException e) {
                this.children = childrenBuf;
            }
        }
    }

    private String[] parseStr(String str) throws PatternSyntaxException {
        return str.trim().split("\\s+|,\\s*");
    }
}
