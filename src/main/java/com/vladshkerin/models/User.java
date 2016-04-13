package com.vladshkerin.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Class to store the users id and name.
 */
public class User {

    private String id;
    private String name;
    private Float growth;
    private Calendar birthDay;
    private String[] children;

    public User(String id, String name, Float growth, Calendar birthDay, String[] children) {
        if (id == null)
            throw new NullPointerException("Argument \"id\" construction is null");
        if (name == null)
            throw new NullPointerException("Argument \"name\" construction is null");
        if (growth == null)
            growth = 0f;
        if (birthDay == null)
            birthDay = new GregorianCalendar(0,0,0);
        if (children == null)
            children = new String[]{};

        this.id = id;
        this.name = name;
        this.growth = growth;
        this.birthDay = birthDay;
        this.children = children;
    }

    public User(String id, String name) {
        this(id, name, null, null, null);
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

        return getClass().getName()
                + "[id=" + id
                + ",name=" + name
                + ",growth=" + growth
                + ",birthDay=" + strBirthDay
                + ",children=" + sbChildren
                + "]";
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (getClass() != otherObject.getClass()) return false;

        User other = (User) otherObject;
        return Objects.equals(id, other.id) &&
                Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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
            sbChildren.deleteCharAt(sbChildren.length() - 2);
        return sbChildren.toString();
    }

    public void setChildren(String[] children) {
        ArrayList<String> list = new ArrayList<>();
        for (String child : children) {
            try {
                Collections.addAll(list, parseStr(child));
            } catch (PatternSyntaxException e) {
                //TODO empty
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

    private String[] parseStr(String str) throws PatternSyntaxException {
        return str.trim().split("\\s+|,");
    }
}
