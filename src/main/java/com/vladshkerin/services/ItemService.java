package com.vladshkerin.services;

import com.vladshkerin.exception.NotFoundUser;
import com.vladshkerin.models.Item;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class for control items.
 *
 * @author vlad
 * @since 09.05.2016
 */
public class ItemService {

    private static final ItemService instance = new ItemService();

    private final List<Item> items = new CopyOnWriteArrayList<>();

    private ItemService() {
        try {
            items.add(new Item(UserService.getInstance().getToName("Petr"), "1 test name", "test desc"));
            items.add(new Item(UserService.getInstance().getToName("Petr"), "2 test name", "test desc"));
            items.add(new Item(UserService.getInstance().getToName("Olga"), "1 test name", "test desc"));
        } catch (NotFoundUser ex) {
            ex.printStackTrace();
        }
    }

    public static ItemService getInstance() {
        return instance;
    }

    public List<Item> getAll() {
        return items;
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
}
