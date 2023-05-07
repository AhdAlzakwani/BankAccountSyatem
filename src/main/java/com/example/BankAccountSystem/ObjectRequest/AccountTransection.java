package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AccountTransection {

    Integer id;

    Integer customerId;

    Integer accountNumber;

    Integer accountAmount;

    String isActive;

}