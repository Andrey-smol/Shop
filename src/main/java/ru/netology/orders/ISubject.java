package ru.netology.orders;

public interface ISubject {
    void registerObserver(IObserver observer);

    void unregisterObserver(IObserver observer);

    void notifyObservers();
}
