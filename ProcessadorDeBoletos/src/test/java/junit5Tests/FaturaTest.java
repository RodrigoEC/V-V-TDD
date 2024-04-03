package junit5Tests;

import org.example.Fatura;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class FaturaTest {

    String client = "Rodrigo Eloy Cavalcanti";
    Date date = new Date();
    double price = 1500;


    @Test
    @DisplayName("Checking a Fatura is created successfully")
    public void testCreateFatura() {
        Fatura fatura = new Fatura(this.client, this.date, this.price);
        assertEquals(fatura.getClass(), Fatura.class);
    }

    @Test
    @DisplayName("Checking a Fatura once created has a client")
    public void testGetFaturasClient() {
        Fatura fatura = new Fatura(this.client, this.date, this.price);
        assertEquals(fatura.getClient(), this.client);
    }

    @Test
    @DisplayName("Checking a Fatura once created has a correct date")
    public void testGetFaturasDate() {
        Fatura fatura = new Fatura(this.client, this.date, this.price);
        assertEquals(fatura.getDate(), this.date);
    }

    @Test
    @DisplayName("Checking a Fatura once created has a correct price")
    public void testGetFaturasPrice() {
        Fatura fatura = new Fatura(this.client, this.date, this.price);
        assertEquals(fatura.getPrice(), this.price, 0);
    }

    @Test
    @DisplayName("Checking a Fatura can't be created with a negative price")
    public void testGetFaturasNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Fatura fatura = new Fatura(this.client, this.date, -1200);
        });
    }

    @Test
    @DisplayName("Checking a Fatura can't be created with a zero price")
    public void testGetFaturasZeroPrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Fatura fatura = new Fatura(this.client, this.date, 0);
        });
    }

    @Test
    @DisplayName("Asserting that a Fatura, once created, has the status of 'NAO PAGA'")
    public void testFaturaIsNotPaid() {
        Fatura fatura = new Fatura(this.client, this.date, this.price);
        assertEquals(fatura.getIsPaid(), "NÃO PAGA");
    }

    @Test
    @DisplayName("Asserting that a Fatura, once payed assumes the status of 'PAGA'")
    public void testFaturaIsPaid() {
        Fatura fatura = new Fatura(this.client, this.date, this.price);
        fatura.pay();
        assertEquals(fatura.getIsPaid(), "PAGA");
    }

}
