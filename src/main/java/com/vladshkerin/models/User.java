package com.vladshkerin.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.PatternSyntaxException;

/**
 * Class to store the users id and name.
 *
 * @author Vladimir Shkerin
 * @since 13.04.2016
 */
public class User {

    private static Integer userCount = 0;

    private String id;
    private String name;
    private Float growth;
    private Calendar birthDay;
    private String email;
    private String[] children;

    public User(String name, Float growth, Calendar birthDay, String email, String[] children) {
        this.id = generateId();
        this.name = name;
        this.growth = growth;
        this.birthDay = birthDay;
        this.email = email;
        this.children = children;
    }

    public User(String name) {
        this(name, 0f, new GregorianCalendar(0, 0, 0), "", new String[] {});
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String strBirthDay = format.format(birthDay.getTime());

        StringBuilder sbChildren = new StringBuilder();
        sbChildren.append("[");
        for (String child : children)
            sbChildren.append(child).append(",");
        sbChildren.deleteCharAt(sbChildren.length() - 1).append("]");

        return getClass().getName() + '[' +
                "id=" + id +
                ", name=" + name +
                ", growth=" + growth +
                ", birthDay=" + strBirthDay +
                ", children=" + sbChildren +
                ", email=" + email + ']';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        User other = (User) obj;
        return Objects.equals(id, other.id) &&
                Objects.equals(name, other.name);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static String getNextId() {
        return String.format("%09d", userCount + 1);
    }

    public String getId() {
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

    private String generateId() {
        return String.format("%09d", ++userCount);
    }

    private String[] parseStr(String str) throws PatternSyntaxException {
        return str.trim().split("\\s+|,\\s*");
    }
}
