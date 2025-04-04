package ru.netology.storage;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

public class CustomerProductsStorage implements IGetProducts {

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

    public OptionalInt numberProductByStore(String category, Product product) {
        Optional<ProductsByCategory> op = getObjectProductsByCategory(category);
        if (op.isPresent()) {
            return op.get().getProductsStorage().getValue(product);
        }
        return OptionalInt.empty();
    }

    @Override
    public Object get(Object o, Object o2) {

        return null;
    }
}
