package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
public class UpdateCustomerInfo {
    Integer id;

    @NotNull
    @Size(min = 3, max = 50)
    String customerName;
    @NotNull
    Integer customerAge;
    @NotNull
    String customerGender;
    @NotNull
    @Size(min = 8, max = 8)
    String customerPhoneNumber;
    @NotNull
    @Email
    String customerEmail;
}
