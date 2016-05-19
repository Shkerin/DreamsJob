package com.vladshkerin.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The class for testing models.
 *
 * @author Vladimir Shkerin
 * @since 19.05.2016
 */
public class TestObject {

    private ArrayList<String> errorList;

    public TestObject() {
        this.errorList = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (errorList.size() > 0) {
            sb.append("\n//////////////// ERROR ////////////////");
            for (String str : errorList) {
                sb.append("\n").append(str);
            }
            sb.append("///////////////////////////////////////");
        }
        return sb.toString();
    }

    public void testToString(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
    }

    public void testEquals(Map<String, Boolean> map) {
        String result = testOut(map);
        if (!result.isEmpty()) {
            errorList.add(result);
            System.out.println("FAIL");
        } else {
            System.out.println("ok");
        }
    }

    public void testHashCode(Map<String, Boolean> map) {
        String result = testOut(map);
        if (!result.isEmpty()) {
            errorList.add(result);
            System.out.println("FAIL");
        } else {
            System.out.println("ok");
        }
    }

    public void printError() {
        System.out.print(toString());
    }

    private static String testOut(Map<String, Boolean> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (!entry.getValue()) {
                sb.append(entry.getKey()).append("\n");
            }
        }
        return sb.length() == 0 ? "" : sb.toString();
    }
}
