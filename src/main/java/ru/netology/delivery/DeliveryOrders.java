package ru.netology.delivery;

import ru.netology.orders.IObserver;
import ru.netology.orders.ISubject;
import ru.netology.orders.Order;

import java.util.function.Consumer;

public class DeliveryOrders implements IObserver {
    Consumer<Order> consumer;
    public DeliveryOrders(ISubject subject, Consumer<Order> consumer) {
        this.consumer = consumer;
        subject.registerObserver(this);
    }

    @Override
    public void update(Order data) {
        consumer.accept(data);
    }
}
