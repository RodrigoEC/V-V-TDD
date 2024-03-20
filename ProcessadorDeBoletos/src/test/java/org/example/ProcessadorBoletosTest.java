package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
    public void testProcessingBoletosExectlyWhatWeOwe() {
        ProcessadorBoletos processador = new ProcessadorBoletos();
        Fatura anotherFatura = new Fatura("Rodrigo Eloy", new Date(), 310);

        ArrayList<Pagamento> pagamentos = processador.processingBoletos(boletos, anotherFatura);
        assertEquals(pagamentos.size(), 3);
    }

    @Test
    public void testProcessingBoletosMoreThenSuffiecientMoney() {
        ProcessadorBoletos processador = new ProcessadorBoletos();
        Fatura anotherFatura = new Fatura("Rodrigo Eloy", new Date(), 100);

        ArrayList<Pagamento> pagamentos = processador.processingBoletos(boletos, anotherFatura);
        assertEquals(pagamentos.size(), 3);
    }

    @Test
    public void testProcessingBoletosNotSuffiecientMoney() {
        ProcessadorBoletos processador = new ProcessadorBoletos();
        Fatura anotherFatura = new Fatura("Rodrigo Eloy", new Date(), 1000);

        ArrayList<Pagamento> pagamentos = processador.processingBoletos(boletos, anotherFatura);
        assertEquals(pagamentos.size(), 0);
        assertEquals(this.fatura.getIsPaid(), "NÃO PAGA");
    }

    @Test
    public void testProcessingBoletosZero() {
        ProcessadorBoletos processador = new ProcessadorBoletos();
        Fatura anotherFatura = new Fatura("Rodrigo Eloy", new Date(), 1000);
        ArrayList<Boleto> novo_boletos = new ArrayList<>();
        Boleto boleto = new Boleto("123124125412", new Date(), 50);
        novo_boletos.add(boleto);

        ArrayList<Pagamento> pagamentos = processador.processingBoletos(novo_boletos, anotherFatura);

        assertEquals(pagamentos.size(), 0);
        assertEquals(this.fatura.getIsPaid(), "NÃO PAGA");
    }

    @Test
    public void testProcessingBoletosFaturaZeroMoney() { // this test verifies that we can't create a fatura for a 0 price
        assertThrows(IllegalArgumentException.class, () -> {
            Fatura anotherFatura = new Fatura("Rodrigo Eloy", new Date(), 0);
        });
    }

    @Test
    public void testProcessingBoletosPayedFatura() {
        ProcessadorBoletos processador = new ProcessadorBoletos();
        ArrayList<Pagamento> pagamentos = processador.processingBoletos(boletos, fatura);
        assertEquals(this.fatura.getIsPaid(), "PAGA");
    }

}
