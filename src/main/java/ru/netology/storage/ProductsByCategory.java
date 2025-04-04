package ru.netology.storage;

import java.util.OptionalInt;
import java.util.Set;

public class ProductsByCategory {
    private final String category;
    private final IProductsStorage<Product> productsStorage;
    private int count = 0;

    public ProductsByCategory(String category, IProductsStorage<Product> productsStorage) {
        this.category = category;
        this.productsStorage = productsStorage;
    }

    public Integer changeProduct(Product product, int amount) {
        return productsStorage.append(product, amount);
    }

    public void removeProduct(Product product) {
        productsStorage.remove(product);
    }

    public Set<Product> getProducts() {
        return productsStorage.get();
    }

    public OptionalInt getValue(Product product) {
        return productsStorage.getValue(product);
    }

    public String getCategory() {
        return category;
    }

}
