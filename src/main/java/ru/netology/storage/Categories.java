package ru.netology.storage;

import java.util.HashSet;
import java.util.Set;

public class Categories {
    private static Categories instance;
    private Set<String> list = new HashSet<>();

    private Categories(){}
    public static Categories getInstance(){
        if(instance == null){
            instance = new Categories();
        }
        return instance;
    }

    public boolean add(String category){
        return list.add(category);
    }
    public boolean remove(String category){
        return list.remove(category);
    }
    public Set<String> get(){
        return list;
    }
}
