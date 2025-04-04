package ru.netology.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Emmiter implements ISubject {
    private Order order;
    private final List<IObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(IObserver observer) {

        observers.add(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {

        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(order);
        }
    }

    public void sendData(Order order) {
        this.order = order;
        notifyObservers();
    }
}
