package ru.netology.storage;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

public class CustomerProductsStorage {

    private static CustomerProductsStorage instance;
    private Categories categories;
    private Set<ProductsByCategory> productsByCategories;

    private CustomerProductsStorage() {
    }

    public static CustomerProductsStorage getInstance() {
        if (instance == null) {
            instance = new CustomerProductsStorage();
        }
        return instance;
    }

    public Set<ProductsByCategory> getProductsByCategories() {
        return productsByCategories;
    }

    public void setProductsByCategories(Set<ProductsByCategory> productsByCategories) {
        this.productsByCategories = productsByCategories;
    }

    public void setCategories(Categories categories) {

        this.categories = categories;
    }

    public Categories getCategories() {
        return categories;
    }

    public Optional<ProductsByCategory> getObjectProductsByCategory(String category) {
        if (categories.getCategoriesStorage().get().contains(category)) {
            return Optional.of((ProductsByCategory) getProductsByCategories()
                    .stream()
                    .filter(p -> p.getCategory().equals(category))
                    .toArray()[0]);
        }
        return Optional.empty();
    }
}
