package org.example;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class BoletoTest {

    @Test
    public void testCreateBoleto() {
        Boleto boleto = new Boleto("123124125412", new Date(), 50);
        assertEquals(boleto.getClass(), Boleto.class);
    }
}
