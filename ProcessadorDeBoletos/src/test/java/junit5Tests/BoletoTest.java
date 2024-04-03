package junit5Tests;

import org.example.Boleto;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Date;

import static org.junit.Assert.*;


public class BoletoTest {

    @Test
    @DisplayName("Creating a Boleto object successfully")
    public void testCreateBoleto() {
        Boleto boleto = new Boleto("123124125412", new Date(), 50);
        assertEquals(boleto.getClass(), Boleto.class);
    }

    @Test
    @DisplayName("Checking if the Boleto is created with the code information right")
    public void testBoletoHasCorrectCodigo() {
        String codigo = "123124125412";
        Boleto boleto = new Boleto(codigo, new Date(), 50);
        assertEquals(boleto.getiD(), codigo);
    }

    @Test
    @DisplayName("Checking if once a boleo is created the code information is actually right")
    public void testBoletoDifferentCodigo() {
        Boleto boleto = new Boleto("123124125412", new Date(), 50);
        assertNotEquals(boleto.getiD(), "34324");
    }


    @Test
    @DisplayName("Checking if the Boleto is created with the right Date")
    public void testBoletoHasDate() {
        Date data = new Date();
        Boleto boleto = new Boleto("123124125412", data, 50);
        assertEquals(boleto.getData(), data);
    }


    @Test
    @DisplayName("Checking if the Boleto is created with the right value")
    public void testBoletoHasValue() {
        double value = 50.50;
        Boleto boleto = new Boleto("123124125412", new Date(), value);
        assertEquals(boleto.getValor(), value, 0);
    }

    @Test
    @DisplayName("Checking that a boleto can't be created with a negative value")
    public void testBoletoValueNegative() {
        double value = -50.50;
        assertThrows(IllegalArgumentException.class, () -> {
            Boleto boleto = new Boleto("123124125412", new Date(), value);
        });
    }

    @Test
    @DisplayName("Checking that a boleto can't be created with a zero value")
    public void testBoletoValueZero() {
        double value = 0;
        assertThrows(IllegalArgumentException.class, () -> {
            Boleto boleto = new Boleto("123124125412", new Date(), value);
        });
    }
}
