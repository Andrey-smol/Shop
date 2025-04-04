package ru.netology.address;

public class CheckCityDelivery {
    private static CheckCityDelivery instance;

    ICheckCityDeliveryStorage iCheckCityDeliveryStorage;

    private CheckCityDelivery() {
    }

    public static CheckCityDelivery getInstance() {
        if (instance == null) {
            instance = new CheckCityDelivery();
        }
        return instance;
    }

    public void setICheckCityDeliveryStorage(ICheckCityDeliveryStorage iCheckCityDeliveryStorage) {
        this.iCheckCityDeliveryStorage = iCheckCityDeliveryStorage;
    }

    public ICheckCityDeliveryStorage getiCheckCityDeliveryStorage() {
        return iCheckCityDeliveryStorage;
    }
}
