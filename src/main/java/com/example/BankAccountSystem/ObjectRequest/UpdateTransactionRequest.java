package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
public class UpdateTransactionRequest {

    @NotNull
    Integer id;
    @NotNull
    Integer customerId;
    @NotNull
    @Size(max = 6)
    Integer accountNumber;
    @NotNull
    String isActive;

}
