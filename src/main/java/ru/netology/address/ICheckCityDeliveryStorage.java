package ru.netology.address;

import java.util.List;

public interface ICheckCityDeliveryStorage {
    boolean checkList(String city);

    List<String> getCities();
}
