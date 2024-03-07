package org.example;

import java.util.ArrayList;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        System.out.println("Work in Progress! See our tests. Focusing on that first :3");

        Fatura fatura = new Fatura("Rodrigo Eloy Cavalcanti", new Date(), 1000);

        ArrayList<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto("8469812e658e4f", new Date(), 100));
        boletos.add(new Boleto("8469812e658e40", new Date(), 801));
        boletos.add(new Boleto("8469812e658e42", new Date(), 100));


        ProcessadorBoletos processador = new ProcessadorBoletos();
        ArrayList<Pagamento> pagamentos = processador.processingBoletos(boletos, fatura);

        System.out.println("The status of your _Fatura_: " +  fatura.getIsPaid());

        System.out.println("Your payment status:");
        System.out.println("Date - Type - Value paid");

        double valuePaid = 0;
        if (pagamentos.isEmpty()) {
            System.out.println("You paid nothing yet");
        } else {
            for (Pagamento pagamento : pagamentos) {
                valuePaid += pagamento.getValuePaid();
                System.out.println(pagamento.toString());
            }
        }
        System.out.println("Value paid: " + valuePaid);
        System.out.println("Debt value: " + fatura.getPrice());

    }
}
