package ru.netology.delivery;

import java.util.Date;

public class Delivery {
    private final String deliveryMan;
    private Date date;

    public Delivery(String deliveryMan, Date date) {
        this.deliveryMan = deliveryMan;
        this.date = date;
    }

    public String getDeliveryMan() {
        return deliveryMan;
    }

    public Date getDate() {
        return date;
    }
}
