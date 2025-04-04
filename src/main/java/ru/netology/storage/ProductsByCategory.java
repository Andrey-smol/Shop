package ru.netology.storage;

import java.util.OptionalInt;
import java.util.Set;

public class ProductsByCategory {
    private final String category;
    private final IProductsStorage<Product> productsStorage;

    public ProductsByCategory(String category, IProductsStorage<Product> productsStorage) {
        this.category = category;
        this.productsStorage = productsStorage;
    }

    public IProductsStorage<Product> getProductsStorage() {
        return productsStorage;
    }

    public String getCategory() {

        return category;
    }

}
