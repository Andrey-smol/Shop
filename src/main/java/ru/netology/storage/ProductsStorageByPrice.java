package ru.netology.storage;

import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductsStorageByPrice implements IProductsStorage<Product> {
    private final IProductsStorage<Product> productsStorage;

    public ProductsStorageByPrice(IProductsStorage<Product> productsStorage) {

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

    public Set<Product> get(Integer minPrice, Integer maxPrice) {
        return get().stream()
                .filter(p -> p.getPrice() >= minPrice.intValue() && p.getPrice() <= maxPrice.intValue())
                .collect(Collectors.toSet());
    }
}
