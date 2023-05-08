package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class GeneRateReportOffAllTransactionsWithinASpecificTimePeriod {

    Integer transactionId;
    Integer accountId;

    public GeneRateReportOffAllTransactionsWithinASpecificTimePeriod(Integer transactionId, Integer accountId, Integer accountNumber, Integer transactionAmount, Date createdDate) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.transactionAmount = transactionAmount;
        this.createdDate = createdDate;
    }

    Integer accountNumber;
    Integer transactionAmount;

    Date createdDate;


}
