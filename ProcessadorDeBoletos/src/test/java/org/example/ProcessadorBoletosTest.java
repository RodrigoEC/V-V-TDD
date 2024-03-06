package org.example;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProcessadorBoletosTest {

    private ArrayList<Boleto> boletos;
    private Fatura fatura;

    @Before
    public void init() {
        this.boletos = new ArrayList<Boleto>();
        this.boletos.add(new Boleto("123124125412", new Date(), 50));
        this.boletos.add(new Boleto("123124125419", new Date(), 110));
        this.boletos.add(new Boleto("123124125410", new Date(), 150));

        this.fatura = new Fatura("Rodrigo Eloy", new Date(), 310);
    }

    @Test
    public void testCreateProcessadorBoletos() {
        ProcessadorBoletos processador = new ProcessadorBoletos(this.boletos, this.fatura);
        assertEquals(processador.getClass(), ProcessadorBoletos.class);
    }
}
