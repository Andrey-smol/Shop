package ru.netology.storage;

import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductsStorageByManufacturer implements IProductsStorage<Product> {
    private final IProductsStorage<Product> productsStorage;

    public ProductsStorageByManufacturer(IProductsStorage<Product> productsStorage) {
        this.productsStorage = productsStorage;
    }

    @Override
    public Integer append(Product product, int value) {
        return productsStorage.append(product, value);
    }

    @Override
    public Set<Product> get() {
        return productsStorage.get();
    }

    @Override
    public OptionalInt getValue(Product product) {
        return productsStorage.getValue(product);
    }

    @Override
    public void remove(Product product) {
        productsStorage.remove(product);
    }

    public Set<Product> get(String s, String s2) {
        return get().stream().filter(p -> p.getManufacturer().matches(s)).collect(Collectors.toSet());
    }
}
