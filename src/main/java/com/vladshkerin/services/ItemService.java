package com.vladshkerin.services;

import com.vladshkerin.enums.UserRole;
import com.vladshkerin.exception.NotFoundItem;
import com.vladshkerin.exception.NotFoundUser;
import com.vladshkerin.models.Item;
import com.vladshkerin.models.Role;
import com.vladshkerin.models.User;

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
        try {
            User user = UserService.getInstance().getToName("user");
            User admin = UserService.getInstance().getToName("admin");

            items.add(new Item(0, user, "1 item", "user"));
            items.add(new Item(1, user, "2 item", "user"));
            items.add(new Item(2, user, "3 item", "user"));
            items.add(new Item(2, admin, "4 item", "admin"));
            items.add(new Item(1, user, "5 item", "user"));
            items.add(new Item(1, user, "6 item", "user"));
            items.add(new Item(0, admin, "7 item", "admin"));

        } catch (NotFoundUser ex) {
            ex.printStackTrace();
        }
    }

    public static ItemService getInstance() {
        return INSTANCE;
    }

    public List<Item> getAll() {
        return items;
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

    public String validateForm(Map<String, String> itemPropertiesMap) {
        StringBuilder errorValues = new StringBuilder();
        for (Map.Entry<String, String> item : itemPropertiesMap.entrySet()) {

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

    public String getTreeItems(Role role, long id) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;

        for (Item item : getSheetsItem(id)) {
            if (!flag) {
                flag = true;
                sb.append("<ul>");
            }

            if (role.getUserRole() == UserRole.ADMIN ||
                    item.getUser().getRole().getUserRole() == UserRole.USER) {

                sb.append("<li>");
                sb.append(String.format("<input type=\"checkbox\" name=\"tree\" value=\"%d\">%s (%s)",
                        item.getId(), item.getName(), item.getDesc()));
                sb.append("</li>");
                sb.append(getTreeItems(role, item.getId()));
            }
        }

        if (flag)
            sb.append("</ul>");

        return sb.toString();
    }

    private List<Item> getSheetsItem(long id) {
        ArrayList<Item> items = new ArrayList<>();
        for (Item item : getAll()) {
            if (item.getParentId() == id) {
                items.add(item);
            }
        }
        return items;
    }
}
