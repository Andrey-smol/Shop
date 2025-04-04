package ru.netology.orders;

import ru.netology.customers.Customer;
import ru.netology.delivery.Delivery;
import ru.netology.storage.Product;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int id;
    private Customer customer;
    private Delivery delivery;
    private Map<Product, Integer> products = new HashMap<>();
    private int price;

    public Order(){
        id++;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            products.remove(product);
        }
    }

    public void addProduct(Product product, int count) {
        int value = count;
        if (products.containsKey(product)) {
            value += products.get(product);
        }
        products.put(product, value);
    }

    public int getId() {
        return id;
    }
}
