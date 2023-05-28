package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
public class NewCreditCardInfo {
    @NotNull
    @Size(max = 6)
    Integer cardNumber;
    @NotNull
    @Size(min = 3, max = 50)
    String customerName;
    @NotNull
    String isActive;
}
