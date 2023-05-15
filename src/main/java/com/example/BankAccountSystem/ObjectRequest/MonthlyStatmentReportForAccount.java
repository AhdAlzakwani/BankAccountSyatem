package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class MonthlyStatmentReportForAccount {

    Integer transactionId;
    Date transactionDate;
    Integer transactionAmount;

    public MonthlyStatmentReportForAccount(Integer transactionId, Date transactionDate, Integer transactionAmount) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
    }
}
