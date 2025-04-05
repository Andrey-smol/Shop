package ru.netology.storage;

import java.util.*;
import java.util.Set;

public class ListProductsStorageAdapter implements IProductsStorage<Product> {
    private final Map<Product, Integer> products = new HashMap<>();

    @Override
    public Integer append(Product product, int value) {

        return products.put(product, value);
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
