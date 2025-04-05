package ru.netology.storage;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductsStorageByPrice implements Predicate<Product> {
    private final int minPrice, maxPrice;

    public ProductsStorageByPrice(int minPrice, int maxPrice) {

        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    public boolean test(Product product) {
        return product.getPrice() >= maxPrice && product.getPrice() <= maxPrice;
    }
}
