package ru.netology.storage;

import java.util.HashSet;
import java.util.Set;

public class Categories{
    private final ICategoriesStorage categoriesStorage;

    public Categories(ICategoriesStorage categoriesStorage) {
        this.categoriesStorage = categoriesStorage;
    }

    public ICategoriesStorage add(String category){
        return categoriesStorage.add(category);
    }
    public boolean remove(String category){
      return categoriesStorage.remove(category);
    }
    public Set<String> get(){
        return categoriesStorage.get();
    }
}
