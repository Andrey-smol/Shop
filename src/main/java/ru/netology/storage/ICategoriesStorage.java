package ru.netology.storage;

import java.util.Set;

public interface ICategoriesStorage {
    ICategoriesStorage add(String category);
    boolean remove(String category);
    Set<String> get();
}
