package ru.netology.storage;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;

public interface ICustomerProductsAdapter {
    Optional<ProductsByCategory> get(String category);
    Optional<ProductsByCategory> get(String category, Predicate<Product> param);
    OptionalInt numberProductByStore(String category, Product product);
    Categories getCategories();
}
