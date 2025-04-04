package ru.netology.customers;

import ru.netology.address.Address;

public class Customer {
    private final String name;
    private final String surname;

    private final Address address;

    public Customer(String name, String surname, Address address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public String getName() {

        return name;
    }

    public String getSurname() {

        return surname;
    }

    public Address getAddress() {

        return address;
    }

}
