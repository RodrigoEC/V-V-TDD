package org.example;

import java.util.Date;

public class Pagamento {
    private final String type;
    private final Date date;
    private final double valuePaid;

    public Pagamento(double valuePaid, Date date, String type) {
        this.valuePaid = valuePaid;
        this.date = date;
        this.type = type;

    }

    public double getValuePaid() {
        return this.valuePaid;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }
}
