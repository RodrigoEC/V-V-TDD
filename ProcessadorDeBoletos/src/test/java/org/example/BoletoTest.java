package org.example;

import org.junit.Test;

import java.lang.reflect.Executable;
import java.util.Date;

import static org.junit.Assert.*;


public class BoletoTest {

    @Test
    public void testCreateBoleto() {
        Boleto boleto = new Boleto("123124125412", new Date(), 50);
        assertEquals(boleto.getClass(), Boleto.class);
    }

    @Test
    public void testBoletoHasCorrectCodigo() {
        String codigo = "123124125412";
        Boleto boleto = new Boleto(codigo, new Date(), 50);
        assertEquals(boleto.getiD(), codigo);
    }

    @Test
    public void testBoletoDifferentCodigo() {
        Boleto boleto = new Boleto("123124125412", new Date(), 50);
        assertNotEquals(boleto.getiD(), "34324");
    }


    @Test
    public void testBoletoHasDate() {
        Date data = new Date();
        Boleto boleto = new Boleto("123124125412", data, 50);
        assertEquals(boleto.getData(), data);
    }


    @Test
    public void testBoletoHasValue() {
        double value = 50.50;
        Boleto boleto = new Boleto("123124125412", new Date(), value);
        assertEquals(boleto.getValor(), value, 0);
    }

    @Test
    public void testBoletoValueNegative() {
        double value = -50.50;
        assertThrows(IllegalArgumentException.class, () -> {
            Boleto boleto = new Boleto("123124125412", new Date(), value);
        });
    }

    @Test
    public void testBoletoValueZero() {
        double value = 0;
        assertThrows(IllegalArgumentException.class, () -> {
            Boleto boleto = new Boleto("123124125412", new Date(), value);
        });
    }
}
