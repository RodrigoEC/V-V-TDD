package org.example;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class FaturaTest {


    @Test
    public void testCreateFatura() {
        Fatura fatura = new Fatura("Rodrigo Eloy Cavalcanti", new Date(), 1500);
        assertEquals(fatura.getClass(), Fatura.class);

    }

}
