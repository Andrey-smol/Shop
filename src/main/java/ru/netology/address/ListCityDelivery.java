package ru.netology.address;

import java.util.List;

public class ListCityDelivery implements ICheckCityDeliveryStorage {
    private static ListCityDelivery instance;
    private List<String> cities = List.of("Москва", "Смоленск", "Тула", "Вязьма");

    private ListCityDelivery() {
    }

    public static ListCityDelivery getInstance() {
        if (instance == null) {
            instance = new ListCityDelivery();
        }
        return instance;
    }

    @Override
    public boolean checkList(String city) {
        return cities.contains(city);
    }

    @Override
    public List<String> getCities() {
        return cities;
    }
}
