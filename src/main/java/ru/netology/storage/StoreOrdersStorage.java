package ru.netology.storage;

import ru.netology.orders.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StoreOrdersStorage implements Consumer<Order> {
    private final List<Order> orderList = new ArrayList<>();
    private static StoreOrdersStorage instance;

    private StoreOrdersStorage() {
    }

    public static StoreOrdersStorage getInstance() {
        if (instance == null) {
            instance = new StoreOrdersStorage();
        }
        return instance;
    }

    public List<Order> getOrderList() {

        return orderList;
    }

    public void remove(Order order) {

        orderList.remove(order);
    }

    @Override
    public void accept(Order order) {
        orderList.add(order);
        System.out.println("Пришел заказ на склад с номером " + order.getId());
        AdministratorProductsStorage.removingPurchasedProductsFromStore(order);
    }
}
