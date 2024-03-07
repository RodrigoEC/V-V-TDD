package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

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
        ProcessadorBoletos processador = new ProcessadorBoletos();
        assertEquals(processador.getClass(), ProcessadorBoletos.class);
    }

    @Test
    public void testProcessingBoletos() {
        ProcessadorBoletos processador = new ProcessadorBoletos();
        ArrayList<Pagamento> pagamentos = processador.processingBoletos(boletos, fatura);
        assertEquals(pagamentos.size(), 3);
    }

    @Test
    public void testProcessingBoletosPayedFatura() {
        ProcessadorBoletos processador = new ProcessadorBoletos();
        ArrayList<Pagamento> pagamentos = processador.processingBoletos(boletos, fatura);
        assertEquals(this.fatura.getIsPaid(), "PAGA");
    }

}
