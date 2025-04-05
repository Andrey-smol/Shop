package ru.netology.storage;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductsStorageByManufacturer implements Predicate<Product> {
    private final String manufacturer;

    public ProductsStorageByManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean test(Product product)  {
        return product.getManufacturer().matches(manufacturer);
    }
}
