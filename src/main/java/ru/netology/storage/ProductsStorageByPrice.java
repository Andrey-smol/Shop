package ru.netology.storage;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductsStorageByPrice implements IGetProducts<Set<Product>, Integer, Integer>{
    private final IProductsStorage<Product> productsStorage;

    public ProductsStorageByPrice(IProductsStorage<Product> productsStorage) {
        this.productsStorage = productsStorage;
    }

    @Override
    public Set<Product> get(Integer minPrice, Integer maxPrice){
        return productsStorage.get().stream()
                .filter(p->p.getPrice() >= minPrice.intValue() && p.getPrice() <= maxPrice.intValue())
                .collect(Collectors.toSet());
    }
}
