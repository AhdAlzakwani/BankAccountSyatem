package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
public class CustomerPayment {
    @NotNull
    @Size(max = 6)
    Integer creditCardNumber;
    @NotNull
    Double payment;
    @NotNull

    String isActive;
}
