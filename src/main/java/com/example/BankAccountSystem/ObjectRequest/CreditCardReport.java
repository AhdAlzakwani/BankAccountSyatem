package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreditCardReport {

    Integer creditCardId;

    public CreditCardReport(Integer creditCardId, Integer cardNumber, Integer customerId, Double creditCardBalance, Double payment) {
        this.creditCardId = creditCardId;
        this.cardNumber = cardNumber;
        this.customerId = customerId;
        this.creditCardBalance = creditCardBalance;
        this.payment = payment;
    }

    Integer cardNumber;
    Integer customerId;
    Double creditCardBalance;

    Double payment;
}
