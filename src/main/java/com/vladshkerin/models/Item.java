package com.vladshkerin.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The class to store items user.
 *
 * @author Vladimir Shkerin
 * @since 09.05.2016
 */
public class Item {

    private static AtomicLong itemNumber = new AtomicLong();

    private long id;
    private long parentId;
    private User user;
    private Calendar date;
    private String name;
    private String desc;

    public Item(long parentId, User user, String name, String desc, Calendar date) {
        this.id = itemNumber.incrementAndGet();
        this.parentId = parentId;
        this.user = user;
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

    public Item(User user) {
        this(0L, user, "", "", new GregorianCalendar());
    }

    public Item() {
        this(0L, new User(), "", "", new GregorianCalendar());
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = dateFormat.format(date.getTime());

        return getClass().getName() +
                "{id=" + id +
                ", parent_id=" + parentId +
                ", user=" + user +
                ", name=" + name +
                ", desc=" + desc +
                ", date=" + strDate +
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

        Item other = (Item) obj;
        return Objects.equals(id, other.id);
    }

    public long getId() {
        return id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getDate() {
        return date;
    }

    public String getDateStr() {
        return getDateStr("yyyy-MM-dd-HH-mm");
    }

    public String getDateStr(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            pattern = "yyyy-MM-dd-HH-mm";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        if (date.getTimeInMillis() > 0) {
            return dateFormat.format(date.getTime());
        } else {
            return "";
        }
    }

    public void setData(String birthDay) {
        setData(birthDay, "yyyy-MM-dd-HH-mm");
    }

    public void setData(String birthDay, String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            pattern = "yyyy-MM-dd-HH-mm";
        }
        if (date != null) {
            Calendar birthDayBuf = this.date;
            try {
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                Date date = format.parse(birthDay.trim());
                this.date.setTime(date);
            } catch (ParseException e) {
                this.date = birthDayBuf;
            }
        }
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
