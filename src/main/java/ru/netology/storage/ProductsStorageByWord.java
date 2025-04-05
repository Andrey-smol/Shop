package ru.netology.storage;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductsStorageByWord implements Predicate<Product> {

    private final String reg;

    public ProductsStorageByWord(String reg) {
        this.reg = reg;
    }

    @Override
    public boolean test(Product product) {
        return product.getName().matches(reg);
    }
}
