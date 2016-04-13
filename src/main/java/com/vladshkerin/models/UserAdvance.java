package com.vladshkerin.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 * Class to store the users id and name.
 */
public class UserAdvance {

    private String id;
    private String name;
    private Float growth;
    private Calendar birthDay;
    private String[] children;

    public UserAdvance(String id, String name, Float growth, Calendar birthDay, String[] children) {
        if (name == null)
            throw new NullPointerException("Argument \"name\" construction is null");
        if (id == null)
            id = String.valueOf(name.hashCode());
        if (growth == null)
            growth = 0f;
        if (birthDay == null)
            birthDay = Calendar.getInstance();
        if (children == null)
            children = new String[]{};

        this.id = id;
        this.name = name;
        this.growth = growth;
        this.birthDay = birthDay;
        this.children = children;
    }

    public UserAdvance(String id, String name) {
        this(id, name, null, null, null);
    }

    public UserAdvance(String name) {
        this(null, name, null, null, null);
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

        UserAdvance other = (UserAdvance) otherObject;
        return Objects.equals(id, other.id) &&
                Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getGrowth() {
        return growth;
    }

    public void setGrowth(Float growth) {
        this.growth = growth;
    }

    public Calendar getBirthDay() {
        return birthDay;
    }

    public String getBirthDayStr() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(birthDay.getTime());
    }

    public void setBirthDay(Calendar birthDay) {
        this.birthDay = birthDay;
    }

    public String[] getChildren() {
        return children;
    }
    public String getChildrenStr() {
        StringBuilder sbChildren = new StringBuilder();
        for (String child : children)
            sbChildren.append(child).append(", ");
        if (sbChildren.length() > 0)
            sbChildren.deleteCharAt(sbChildren.length() - 2);
        return sbChildren.toString();
    }

    public void setChildren(String[] children) {
        this.children = children;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
