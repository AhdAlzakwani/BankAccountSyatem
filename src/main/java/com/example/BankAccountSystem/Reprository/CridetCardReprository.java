package com.example.BankAccountSystem.Reprository;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CridetCardReprository extends JpaRepository<CreditCard, Integer> {

    @Query(value = "Select is_active from credit_card where card_number = :cardNumber", nativeQuery = true)
    String getStatusOfCreditCard(@Param("cardNumber") Integer cardNumber);


    @Query(value = "Update credit_card SET is_active = 'false' WHERE card_number = :cardNumber", nativeQuery = true)
    CreditCard deletedCreditCard(@Param("cardNumber") Integer cardNumber);
}
