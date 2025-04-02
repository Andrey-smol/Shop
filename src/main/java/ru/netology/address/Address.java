package ru.netology.address;

public class Address {
    private String city;
    private String street;
    private String house;
    private int apartment;
    private String phone;

    public Address(String city, String street, String house, int apartment, String phone) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.phone = phone;
    }

}
