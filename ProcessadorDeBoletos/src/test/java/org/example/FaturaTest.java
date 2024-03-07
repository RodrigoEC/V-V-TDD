package org.example;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class FaturaTest {

    String client = "Rodrigo Eloy Cavalcanti";
    Date date = new Date();
    double price = 1500;


    @Test
    public void testCreateFatura() {
        Fatura fatura = new Fatura(this.client, this.date, this.price);
        assertEquals(fatura.getClass(), Fatura.class);
    }

    @Test
    public void testGetFaturasClient() {
        Fatura fatura = new Fatura(this.client, this.date, this.price);
        assertEquals(fatura.getClient(), this.client);
    }

    @Test
    public void testGetFaturasDate() {
        Fatura fatura = new Fatura(this.client, this.date, this.price);
        assertEquals(fatura.getDate(), this.date);
    }

    @Test
    public void testGetFaturasPrice() {
        Fatura fatura = new Fatura(this.client, this.date, this.price);
        assertEquals(fatura.getPrice(), this.price, 0);
    }

    @Test
    public void testGetFaturasNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Fatura fatura = new Fatura(this.client, this.date, -1200);
        });
    }

    @Test
    public void testGetFaturasZeroPrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Fatura fatura = new Fatura(this.client, this.date, 0);
        });
    }

    @Test
    public void testFaturaIsNotPaid() {
        Fatura fatura = new Fatura(this.client, this.date, this.price);
            assertEquals(fatura.getIsPaid(), "NÃO PAGA");
    }

    @Test
    public void testFaturaIsPaid() {
        Fatura fatura = new Fatura(this.client, this.date, this.price);
        fatura.pay();
        assertEquals(fatura.getIsPaid(), "PAGA");
    }

}
