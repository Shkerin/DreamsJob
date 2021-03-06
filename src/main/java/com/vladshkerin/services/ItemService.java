package com.vladshkerin.services;

import com.vladshkerin.exceptions.NotFoundItem;
import com.vladshkerin.exceptions.NotFoundUser;
import com.vladshkerin.models.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The class for control items.
 *
 * @author Vladimir Shkerin
 * @since 09.05.2016
 */
public class ItemService {

    private static final ItemService INSTANCE = new ItemService();

    private final List<Item> items = new CopyOnWriteArrayList<>();

    private ItemService() {
//        try {
//            User user = UserService.getInstance().get("user");
//            User admin = UserService.getInstance().get("admin");
//
//            items.add(new Item(0, user, "1 item", "user", new GregorianCalendar(2001, 0, 1)));
//            items.add(new Item(1, user, "2 item", "user", new GregorianCalendar(2002, 1, 2)));
//            items.add(new Item(2, user, "3 item", "user", new GregorianCalendar(2003, 2, 3)));
//            items.add(new Item(2, admin, "4 item", "admin", new GregorianCalendar(2004, 3, 4)));
//            items.add(new Item(1, user, "5 item", "user", new GregorianCalendar(2005, 4, 5)));
//            items.add(new Item(1, user, "6 item", "user", new GregorianCalendar(2006, 5, 6)));
//            items.add(new Item(0, admin, "7 item", "admin", new GregorianCalendar(2007, 6, 7)));
//
//        } catch (NotFoundUser ex) {
//            ex.printStackTrace();
//        }
    }

    public static ItemService getInstance() {
        return INSTANCE;
    }

    public List<Item> getAll() {
        return items;
    }

    public List<Item> getAllValidation() {
        List<Item> list = new ArrayList<>();
        for (Item item : getAll()) {
            if (FilterService.getInstance().validation(item)) {
                list.add(item);
            }
        }
        return list;
    }

    public Item get(long id) throws NotFoundItem {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new NotFoundItem("Item not fount to \"id\"" + id);
    }

    public void add(Item item) {
        items.add(item);
    }

    public void delete(long id) {
        try {
            items.remove(get(id));
        } catch (NotFoundItem e) {
            //TODO add out to log
            System.out.println(e.getMessage());
        }
    }

    public void clear() {
        items.clear();
    }

    public String validateForm(Map<String, String> itemMap) {
        StringBuilder errorValues = new StringBuilder();
        for (Map.Entry<String, String> item : itemMap.entrySet()) {

            String key = item.getKey();
            key = key.substring(0, 1).toUpperCase() + key.substring(1, key.length());

            if (item.getValue().isEmpty()) {
                errorValues.append(key).append(", ");
            }
        }
        if (!errorValues.toString().isEmpty()) {
            errorValues.replace(errorValues.length() - 2, errorValues.length(), "");
        }
        return errorValues.toString();
    }

    public String getTreeItems(long id) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;

        for (Item item : getSheetsItem(id)) {
            if (!flag) {
                flag = true;
                sb.append("<ul>");
            }
            sb.append("<li>");
            sb.append(String.format("<input type=\"checkbox\" name=\"tree\" value=\"%d\">%s (%s)",
                    item.getId(), item.getName(), item.getUser().getName()));
            sb.append("</li>");
            sb.append(getTreeItems(item.getId()));
        }

        if (flag)
            sb.append("</ul>");

        return sb.toString();
    }

    private List<Item> getSheetsItem(long id) {
        List<Item> items = new ArrayList<>();
        for (Item item : getAllValidation()) {
            if (item.getParentId() == id) {
                items.add(item);
            }
        }
        return items;
    }

    public void saveFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/tmp/items.txt", false));
            for (Item item : getAll()) {
                String id = String.valueOf(item.getId());
                String userId = String.valueOf(item.getUser().getId());
                String data = item.getDateStr();
                String parentId = String.valueOf(item.getParentId());
                String name = item.getName();
                String desc = item.getDesc();

                writer.write(id + "/");
                writer.write(userId.isEmpty() ? " /" : userId + "/");
                writer.write(data.isEmpty() ? " /" : data + "/");
                writer.write(parentId.isEmpty() ? " /" : parentId + "/");
                writer.write(name.isEmpty() ? " /" : name + "/");
                writer.write(desc.isEmpty() ? " \n" : desc + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            //TODO out to log
            ex.printStackTrace();
        }
    }

    public void loadFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/tmp/items.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                makeItem(line);
            }
        } catch (IOException ex) {
            //TODO out to log
            ex.printStackTrace();
        }
    }

    private void makeItem(String lineToParse) {
        try {
            String[] result = lineToParse.split("/");

            Item item;
            try {
                item = get(Long.valueOf(result[0]));
            } catch (NotFoundItem ex) {
                item = new Item();
                items.add(item);
            }
            item.setUser(UserService.getInstance().get(Long.valueOf(result[1])));
            item.setData(result[2], "yyyy-MM-dd-HH-mm");
            item.setParentId(Long.valueOf(result[3]));
            item.setName(result[4]);
            item.setDesc(result[5]);
        } catch (NotFoundUser ex) {
            //TODO out to log
            ex.printStackTrace();
        }
    }
}
