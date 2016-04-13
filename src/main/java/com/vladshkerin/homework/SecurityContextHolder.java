package com.vladshkerin.homework;

/**
 * TODO: comment
 *
 * @author Vladimir Shkerin
 * @since 22.03.2016
 */

public class SecurityContextHolder {

    private static final ThreadLocal<Integer> threadLocalCount = new ThreadLocal<>();

    public static Integer getCount() {
        return threadLocalCount.get();
    }

    public static void setCount(Integer count) {
        threadLocalCount.set(count);
    }

    public static void increment() {
        int count = threadLocalCount.get();
        threadLocalCount.set(++count);
    }
}
