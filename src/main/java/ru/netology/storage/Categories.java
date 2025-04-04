package ru.netology.storage;

import java.util.HashSet;
import java.util.Set;

public class Categories {
    private final ICategoriesStorage categoriesStorage;

    public Categories(ICategoriesStorage categoriesStorage) {

        this.categoriesStorage = categoriesStorage;
    }

    public ICategoriesStorage getCategoriesStorage() {
        return categoriesStorage;
    }
}
