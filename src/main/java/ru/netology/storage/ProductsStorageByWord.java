package ru.netology.storage;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductsStorageByWord implements IProductsStorage<Product> {
    private final IProductsStorage<Product> productsStorage;

    public ProductsStorageByWord(IProductsStorage<Product> productsStorage) {

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

    public Set<Product> get(Optional<String> op, String s) {
        if (op.isPresent()) {
            return productsStorage.get();
        }
        String matches = ".*" + op.get() + ".*";
        return get().stream().filter(p -> p.getName().matches(matches)).collect(Collectors.toSet());
    }
}
