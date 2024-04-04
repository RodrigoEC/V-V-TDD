package junit5Tests;

import org.example.Pagamento;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PagamentoTest {

    double valuePaid = 120.60;
    Date date = new Date();

    String type = "BOLETO";

    @Test
    @DisplayName("Asserting that a Fatura object was created successfully")
    public void testCreatePagamento() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertEquals(pagamento.getClass(), Pagamento.class);
    }

    @Test
    @DisplayName("Asserting that a Fatura object was created having the right amount of paid value")
    public void testGetPagamentoValuePaid() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertEquals(pagamento.getValuePaid(), this.valuePaid, 0);
    }

    @Test
    @DisplayName("Second test asserting that a Fatura object was created having the right amount of paid value")
    public void testGetPagamentoWrongValuePaid() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertEquals(pagamento.getValuePaid(), 20, 100.60);
    }

    @Test
    @DisplayName("Asserting that a Fatura object was created having the right date")
    public void testGetPagamentoDate() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertEquals(pagamento.getDate(), this.date);
    }

    @Test
    @DisplayName("Asserting that a Fatura object was created having the right date using a wrong date in purpose")
    public void testGetPagamentoWrongDate() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        Date myDate = new Date(System.currentTimeMillis());
        assertNotEquals(pagamento.getDate(), new Date(myDate.getTime() - (10 * 24 * 60 * 60 * 1000))); // removes 10 days from current date
    }

    @Test
    @DisplayName("Asserting that thae pagamento has the BOLETO type")
    public void testGetPagamentoType() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertEquals(pagamento.getType(), this.type);
    }

    @Test
    @DisplayName("Asserting that the pagamento hasnt' a type different than BOLETO")
    public void testGetPagamentoWrongType() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertNotEquals(pagamento.getType(), "CARTẪO");
    }

    @Test
    @DisplayName("Asserting that the toString method is working properly")
    public void testGetPagamentoToString() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertEquals(pagamento.toString(), String.format("%s - %s - %s", this.date, this.type, this.valuePaid));
    }


}
