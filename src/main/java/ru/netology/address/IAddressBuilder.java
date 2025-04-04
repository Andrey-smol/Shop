package ru.netology.address;

public interface IAddressBuilder {
    IAddressBuilder setCity(String city);

    IAddressBuilder setStreet(String street);

    IAddressBuilder setHouse(String house);

    IAddressBuilder setApartment(String apartment);

    IAddressBuilder setPhone(String phone);

    Address build();
}
