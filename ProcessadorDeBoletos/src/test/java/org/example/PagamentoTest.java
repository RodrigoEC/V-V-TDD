package org.example;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PagamentoTest {

    double valuePaid = 120.60;
    Date date = new Date();

    String type = "BOLETO";

    @Test
    public void testCreatePagamento() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertEquals(pagamento.getClass(), Pagamento.class);
    }

    @Test
    public void testGetPagamentoValuePaid() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertEquals(pagamento.getValuePaid(), this.valuePaid, 0);
    }

    @Test
    public void testGetPagamentoWrongValuePaid() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertEquals(pagamento.getValuePaid(), 20, 100.60);
    }

    @Test
    public void testGetPagamentoDate() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertEquals(pagamento.getDate(), this.date);
    }

    @Test
    public void testGetPagamentoWrongDate() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        Date myDate = new Date(System.currentTimeMillis());
        assertNotEquals(pagamento.getDate(), new Date(myDate.getTime() - (10 * 24 * 60 * 60 * 1000))); // removes 10 days from current date
    }

    @Test
    public void testGetPagamentoType() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertEquals(pagamento.getType(), this.type);
    }

    @Test
    public void testGetPagamentoWrongType() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertNotEquals(pagamento.getType(), "CARTẪO");
    }

}
