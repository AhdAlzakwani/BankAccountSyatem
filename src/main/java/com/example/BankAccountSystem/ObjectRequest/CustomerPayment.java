package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CustomerPayment {

    Integer creditCardNumber;
    Double payment;
    String isActive;
}
