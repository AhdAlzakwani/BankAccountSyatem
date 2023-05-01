package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AddNewAccountForStudent {
    Integer accountNumber;

    Double accountBalance;

    String customerName;

    String isActive;
}
