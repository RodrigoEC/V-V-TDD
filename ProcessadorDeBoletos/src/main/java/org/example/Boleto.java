package org.example;

import java.util.Date;

public class Boleto {

    private final String id;
    private final Date data;
    private final double valor;
    public Boleto(String id, Date data, double valor) {

        if (valor <= 0) {
            throw new IllegalArgumentException("Value not permitted" + valor);
        }

        this.id = id;
        this.data = data;
        this.valor = valor;
    }

    public String getiD() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }


}
