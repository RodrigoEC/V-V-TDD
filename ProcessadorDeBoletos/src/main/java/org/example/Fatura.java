package org.example;

import java.util.Date;

public class Fatura {

    private final String client;
    private final Date date;
    private final double price;

    public Fatura(String client, Date date, double price) {
        this.client = client;
        this.date = date;
        this.price = price;
    }

    public String getClient() {
        return this.client;
    }

    public Date getDate() {
        return this.date;
    }

    public double getPrice() {
        return this.price;
    }
}
