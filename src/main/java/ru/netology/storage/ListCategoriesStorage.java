package ru.netology.storage;

import java.util.HashSet;
import java.util.Set;

public class ListCategoriesStorage implements ICategoriesStorage {
    private static ICategoriesStorage instance;
    private final Set<String> list = new HashSet<>();

    private ListCategoriesStorage() {
    }

    public static ICategoriesStorage getInstance() {
        if (instance == null) {
            instance = new ListCategoriesStorage();
        }
        return instance;
    }

    @Override
    public ICategoriesStorage add(String category) {
        list.add(category);
        return this;
    }

    @Override
    public boolean remove(String category) {

        return list.remove(category);
    }

    @Override
    public Set<String> get() {

        return list;
    }
}
