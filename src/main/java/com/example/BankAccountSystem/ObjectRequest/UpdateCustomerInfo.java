package com.example.BankAccountSystem.ObjectRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateCustomerInfo {
    Integer id;

    String customerName;

    Integer customerAge;
    String customerGender;

    String customerPhoneNumber;

    String customerEmail;
}
