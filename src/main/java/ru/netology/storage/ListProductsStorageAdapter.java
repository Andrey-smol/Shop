package ru.netology.storage;

import java.util.*;

public class ListProductsStorageAdapter implements IProductsStorage<Product>{
    private final Map<Product, Integer> products = new HashMap<>();

    @Override
    public Integer append(Product product, int value) {
        int val = value;
        if (products.containsKey(product)) {
            val += products.get(product);
        }
        return products.put(product, value + val);
    }

    @Override
    public Set<Product> get() {
        return products.keySet();
    }

    @Override
    public OptionalInt getValue(Product product) {
        Integer value = products.get(product);
        return value == null ? OptionalInt.empty() : OptionalInt.of(value);
    }

    @Override
    public void remove(Product product) {
        products.remove(product);
    }
}
