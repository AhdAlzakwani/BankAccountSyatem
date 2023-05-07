package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AccountInfo {
    Integer id;
    Integer accountNumber;

    Double accoubtBalance;
    Integer customerId;

    String isActive;
}
