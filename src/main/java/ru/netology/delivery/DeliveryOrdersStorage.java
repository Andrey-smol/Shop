package ru.netology.delivery;

import ru.netology.orders.Order;
import ru.netology.storage.StoreOrdersStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DeliveryOrdersStorage implements Consumer<Order> {
    private List<Order> orderList = new ArrayList<>();
    private static DeliveryOrdersStorage instance;
    private DeliveryOrdersStorage(){}
    public static DeliveryOrdersStorage getInstance(){
        if(instance == null){
            instance = new DeliveryOrdersStorage();
        }
        return instance;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
    public void remove(Order order){
        orderList.remove(order);
    }

    @Override
    public void accept(Order order) {
        orderList.add(order);
        System.out.println("Пришел заказ в доставку с номером " + order.getId());
    }
}
