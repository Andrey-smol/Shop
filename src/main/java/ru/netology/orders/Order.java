package ru.netology.orders;

import ru.netology.customers.Customer;
import ru.netology.delivery.Delivery;
import ru.netology.storage.Product;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Customer customer;
    private Delivery delivery;
    private Map<Product, Integer> products = new HashMap<>();

}
