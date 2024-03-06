package org.example;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PagamentoTest {

    double valuePaid = 120.60;
    Date date = new Date();

    String type = "BOLETO";


    @Test
    public void testCreatePagamento() {
        Pagamento pagamento = new Pagamento(this.valuePaid, this.date, this.type);
        assertEquals(pagamento.getClass(), Pagamento.class);
    }
}
