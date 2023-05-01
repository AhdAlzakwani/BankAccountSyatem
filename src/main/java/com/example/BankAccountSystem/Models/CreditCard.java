package com.example.BankAccountSystem.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Data
@Entity
public class CreditCard extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer cardNumber;

    Double credirCardBalanse;

    @ManyToOne
    @JoinColumn(name = "Customer_id",referencedColumnName = "id")
    Customer coustomer;

}
