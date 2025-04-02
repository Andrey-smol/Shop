package ru.netology.storage;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductsStorageByManufacturer implements IGetProducts<Set<Product>, String, String>{
    private final IProductsStorage<Product> productsStorage;

    public ProductsStorageByManufacturer(IProductsStorage<Product> productsStorage) {
        this.productsStorage = productsStorage;
    }

    @Override
    public Set<Product> get(String s, String s2) {
        return productsStorage.get().stream().filter(p->p.getManufacturer().matches(s)).collect(Collectors.toSet());
    }

//    public Set<Product> get(String manufacturer, ""){
//        return productsStorage.get().stream().filter(p->p.getManufacturer().matches(manufacturer)).collect(Collectors.toSet());
//    }
}
