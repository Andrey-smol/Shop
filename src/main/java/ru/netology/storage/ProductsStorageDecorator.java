package ru.netology.storage;

import java.util.OptionalInt;
import java.util.Set;

public class ProductsStorageDecorator implements IProductsStorage<Product> {
    private final Set<Product> productSet;
    private final IProductsStorage<Product> productsStorage;

    public ProductsStorageDecorator(Set<Product> productSet, IProductsStorage<Product> productsStorage) {
        this.productSet = productSet;
        this.productsStorage = productsStorage;
    }

    @Override
    public Integer append(Product product, int value) {
        return productsStorage.append(product, value);
    }

    @Override
    public OptionalInt getValue(Product product) {
        return productsStorage.getValue(product);
    }

    @Override
    public void remove(Product product) {
        productsStorage.remove(product);
    }

    @Override
    public Set<Product> get() {
        return productSet;
    }
}
