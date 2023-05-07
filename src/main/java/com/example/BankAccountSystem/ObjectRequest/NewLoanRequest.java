package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class NewLoanRequest {

    Integer amount;

    Double interest;

    Integer accountNumber;

    String isActive;
}
