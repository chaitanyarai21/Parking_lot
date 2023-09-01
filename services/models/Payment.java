package src.main.services.models;

import src.main.enums.PaymentStatus;
import src.main.exceptions.TransactionFailedException;

import java.time.LocalDate;

public class Payment {
    private LocalDate creationDate;

    private long amount;

    private PaymentStatus paymentStatus;

    public Payment() {
        this.creationDate = LocalDate.now();

        paymentStatus = PaymentStatus.NOT_PAID;
    }

    public void initiateTransaction(Double payedAmount) throws TransactionFailedException{

        // call to payment service with the amount
        // If successful then set to True otherwise False

        // By card or by cash
        Boolean isSuccessful = true;

        if(isSuccessful) {
            paymentStatus = PaymentStatus.PAID;
            System.out.println("Amount of " + payedAmount.toString() + " paid!");
        } else {
            paymentStatus = PaymentStatus.NOT_PAID;
            throw new TransactionFailedException();
        }
    }
}