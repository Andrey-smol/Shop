package ru.netology.storage;

import java.util.HashSet;
import java.util.Set;

public class CustomerProductsStorage implements IGetProducts{

    private static CustomerProductsStorage instance;
    private Categories categories;
    private Set<ProductsByCategory> productsByCategories;

    private CustomerProductsStorage(){}

    public static CustomerProductsStorage getInstance(){
        if(instance == null){
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

    @Override
    public Object get(Object o, Object o2) {
        return null;
    }
}
