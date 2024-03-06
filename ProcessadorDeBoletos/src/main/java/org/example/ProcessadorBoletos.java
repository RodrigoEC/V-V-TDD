package org.example;

import java.util.ArrayList;
import java.util.Date;

public class ProcessadorBoletos {

    public ProcessadorBoletos() {
    }

    public ArrayList<Pagamento> processingBoletos(ArrayList<Boleto> boletos, Fatura fatura) {

        double totalCost = 0;
        for (Boleto boleto: boletos) {
            totalCost += boleto.getValor();
        }

        if (totalCost <= fatura.getPrice()) {
            fatura.pay();

            ArrayList<Pagamento> pagamentos = new ArrayList<>();
            for (Boleto boleto: boletos) {
                Pagamento pagamento = new Pagamento(boleto.getValor(), new Date(), "BOLETO");
                pagamentos.add(pagamento);
            }

            return pagamentos;
        }

        return new ArrayList<>();

    }

}
