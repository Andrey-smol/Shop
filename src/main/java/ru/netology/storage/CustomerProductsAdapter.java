package ru.netology.storage;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomerProductsAdapter implements ICustomerProductsAdapter {
    private final CustomerProductsStorage customerProducts;

    public CustomerProductsAdapter(CustomerProductsStorage customerProducts) {
        this.customerProducts = customerProducts;
    }

    @Override
    public Optional<ProductsByCategory> get(String category) {
        return customerProducts.getObjectProductsByCategory(category);
    }

    @Override
    public Optional<ProductsByCategory> get(String category, Predicate<Product> param) {
        Optional<ProductsByCategory> op = get(category);
        if (op.isPresent()) {
            Set<Product> productSet = op.get().getProductsStorage().get().stream().filter(param::test).collect(Collectors.toSet());
            ProductsByCategory products = new ProductsByCategory(category, new ProductsStorageDecorator(productSet, op.get().getProductsStorage()));
            return Optional.of(products);
        }
        return op;
    }

    @Override
    public OptionalInt numberProductByStore(String category, Product product) {
        Optional<ProductsByCategory> op = get(category);
        if (op.isPresent()) {
            return op.get().getProductsStorage().getValue(product);
        }
        return OptionalInt.empty();
    }

    @Override
    public Categories getCategories() {
        return customerProducts.getCategories();
    }

}
