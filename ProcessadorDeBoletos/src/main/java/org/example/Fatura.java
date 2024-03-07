package org.example;

import java.util.Date;

public class Fatura {

    private final String client;
    private final Date date;
    private final double price;
    private String isPaid;

    public Fatura(String client, Date date, double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Value not permitted" + price);
        }

        this.client = client;
        this.date = date;
        this.price = price;
        this.isPaid = "NÃO PAGA";
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

    public String getIsPaid() {
        return isPaid;
    }

    public void pay() {
        this.isPaid = "PAGA";
    }
}
