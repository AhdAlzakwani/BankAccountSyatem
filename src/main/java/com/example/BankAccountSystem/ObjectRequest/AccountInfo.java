package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
public class AccountInfo {
    @NotNull
    Integer id;

    @NotNull
    @Size(max = 6)
    Integer accountNumber;

    @NotNull
    Double accoubtBalance;
    @NotNull
    Integer customerId;
    @NotNull
    String isActive;
}
