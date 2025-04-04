package ru.netology.address;

public class Address {
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String phone;

    public Address(String city, String street, String house, String apartment, String phone) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
