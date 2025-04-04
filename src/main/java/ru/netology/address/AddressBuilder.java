package ru.netology.address;

public class AddressBuilder implements IAddressBuilder{
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String phone;
    @Override
    public IAddressBuilder setCity(String city) {
        return this;
    }

    @Override
    public IAddressBuilder setStreet(String street) {
        return this;
    }

    @Override
    public IAddressBuilder setHouse(String house) {
        return this;
    }

    @Override
    public IAddressBuilder setApartment(String apartment) {
        return this;
    }

    @Override
    public IAddressBuilder setPhone(String phone) {
        return this;
    }

    @Override
    public Address build() {
        return new Address(city,street,house, apartment, phone);
    }
}
