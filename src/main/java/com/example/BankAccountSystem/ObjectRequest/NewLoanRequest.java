package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class NewLoanRequest {

    Integer amount;

    Integer accountNumber;

    String isActive;
}
