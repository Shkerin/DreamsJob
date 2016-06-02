package com.vladshkerin.services;

import com.vladshkerin.exception.NotFoundFilter;
import com.vladshkerin.models.Filter;
import com.vladshkerin.models.Item;
import com.vladshkerin.models.Role;
import com.vladshkerin.models.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The class to control filter.
 *
 * @author Vladimir Shkerin
 * @since 02.06.2016
 */
public class FilterService {

    private static final FilterService INSTANCE = new FilterService();

    private final List<Filter> filters = new CopyOnWriteArrayList<Filter>();

    public static FilterService getInstance() {
        return INSTANCE;
    }

    public List<Filter> getAll() {
        return filters;
    }

    public Filter get(User user) throws NotFoundFilter {
        for (Filter filter : filters) {
            if (filter.getUser().equals(user)) {
                return filter;
            }
        }
        throw new NotFoundFilter("Filter not fount by user: " + user.getName());
    }

    public void add(Filter filter) {
        filters.add(filter);
    }

    public void delete(Filter filter) {
        filters.remove(filter);
    }

    public void clear() {
        filters.clear();
    }

    public boolean validationItem(Item item) {
        for (Filter filter : filters) {
            if (filter.getUser().getRole().isRoleAdmin() ||
                    filter.getUser().equals(item.getUser())) {
                return true;
            }
        }
        return false;
    }
}
