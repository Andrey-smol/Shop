package ru.netology.storage;

import java.util.OptionalInt;
import java.util.Set;

public interface IProductsStorage<Product> {
    Integer append(Product product, int value);

    Set<Product> get();

    OptionalInt getValue(Product product);

    void remove(Product product);
}
