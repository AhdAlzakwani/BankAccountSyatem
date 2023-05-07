package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateTransactionRequest {

    Integer id;
    Integer customerId;
    Integer accountNumber;
    String isActive;

}
