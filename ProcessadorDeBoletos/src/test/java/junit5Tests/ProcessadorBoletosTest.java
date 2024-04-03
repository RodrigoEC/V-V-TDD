package junit5Tests;

import org.example.Boleto;
import org.example.Fatura;
import org.example.Pagamento;
import org.example.ProcessadorBoletos;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

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
    @DisplayName("Asserting the ProcessadorBOleto object was created successfully")
    public void testCreateProcessadorBoletos() {
        ProcessadorBoletos processador = new ProcessadorBoletos();
        assertEquals(processador.getClass(), ProcessadorBoletos.class);
    }

    @Test
    @DisplayName("Asserting the ProcessadorBOleto object was payed successfully")
    public void testProcessingBoletos() {
        ProcessadorBoletos processador = new ProcessadorBoletos();
        ArrayList<Pagamento> pagamentos = processador.processingBoletos(boletos, fatura);
        assertEquals(pagamentos.size(), 3);
    }

    @Test
    @DisplayName("Asserting the ProcessadorBOleto object was payed successfully but now paying exactly what was being owed")
    public void testProcessingBoletosExectlyWhatWeOwe() {
        ProcessadorBoletos processador = new ProcessadorBoletos();
        Fatura anotherFatura = new Fatura("Rodrigo Eloy", new Date(), 310);

        ArrayList<Pagamento> pagamentos = processador.processingBoletos(boletos, anotherFatura);
        assertEquals(pagamentos.size(), 3);
    }

    @Test
    @DisplayName("Asserting the ProcessadorBOleto object was payed successfully but now paying more than what was owed")
    public void testProcessingBoletosMoreThenSuffiecientMoney() {
        ProcessadorBoletos processador = new ProcessadorBoletos();
        Fatura anotherFatura = new Fatura("Rodrigo Eloy", new Date(), 100);

        ArrayList<Pagamento> pagamentos = processador.processingBoletos(boletos, anotherFatura);
        assertEquals(pagamentos.size(), 3);
    }

    @Test
    @DisplayName("Trying to pay without the necessary amount")
    public void testProcessingBoletosNotSuffiecientMoney() {
        ProcessadorBoletos processador = new ProcessadorBoletos();
        Fatura anotherFatura = new Fatura("Rodrigo Eloy", new Date(), 1000);

        ArrayList<Pagamento> pagamentos = processador.processingBoletos(boletos, anotherFatura);
        assertEquals(pagamentos.size(), 0);
        assertEquals(this.fatura.getIsPaid(), "NÃO PAGA");
    }

    @Test
    @DisplayName("Trying to pay without the necessary amount")
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
    @DisplayName("Trying to pay the Fatura without any money")
    public void testProcessingBoletosFaturaZeroMoney() { // this test verifies that we can't create a fatura for a 0 price
        assertThrows(IllegalArgumentException.class, () -> {
            Fatura anotherFatura = new Fatura("Rodrigo Eloy", new Date(), 0);
        });
    }

    @Test
    @DisplayName("PAying the Fatura correctly")
    public void testProcessingBoletosPayedFatura() {
        ProcessadorBoletos processador = new ProcessadorBoletos();
        ArrayList<Pagamento> pagamentos = processador.processingBoletos(boletos, fatura);
        assertEquals(this.fatura.getIsPaid(), "PAGA");
    }

}
