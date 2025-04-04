package ru.netology.storage;

import java.util.Objects;

public class Product {
    private final String name;
    private int price;
    private int rating;
    private final String manufacturer;

    public Product(String name, int price, int rating, String manufacturer) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.manufacturer = manufacturer;
    }

    public String getName() {

        return name;
    }

    public int getPrice() {

        return price;
    }

    public int getRating() {

        return rating;
    }

    public void setPrice(int price) {

        this.price = price;
    }

    public void setRating(int rating) {

        this.rating = rating;
    }

    public String getManufacturer() {

        return manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
