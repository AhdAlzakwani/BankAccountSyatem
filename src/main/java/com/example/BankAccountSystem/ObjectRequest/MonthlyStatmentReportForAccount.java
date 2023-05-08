package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MonthlyStatmentReportForAccount {

    Integer accountNumber;
    String customerName;
    String customerEmail;
    String customerPhoneNumber;
    Double accountBalance;

    public MonthlyStatmentReportForAccount(Integer accountName, String customerName, String customerEmail, String customerPhoneNumber, Double accountBalance) {
        this.accountNumber = accountName;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.accountBalance = accountBalance;

    }
}
