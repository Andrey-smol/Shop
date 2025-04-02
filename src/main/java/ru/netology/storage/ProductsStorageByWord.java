package ru.netology.storage;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductsStorageByWord implements IGetProducts<Set<Product>, Optional<String>, String>{
    private final IProductsStorage<Product> productsStorage;

    public ProductsStorageByWord(IProductsStorage<Product> productsStorage) {
        this.productsStorage = productsStorage;
    }

    @Override
    public Set<Product> get(Optional<String> op, String s) {
        if(op.isPresent()) {
            return productsStorage.get();
        }
        String matches = ".*" + op.get() + ".*";
        return productsStorage.get().stream().filter(p->p.getName().matches(matches)).collect(Collectors.toSet());
    }
}
