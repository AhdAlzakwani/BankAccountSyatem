package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
public class AddNewAccountForStudent {

    @NotNull
    @Size(max = 6)
    Integer accountNumber;

    @NotNull
    Double accountBalance;

    @NotNull
    @Size(min = 3, max = 50)
    String customerName;

    String isActive;
}
