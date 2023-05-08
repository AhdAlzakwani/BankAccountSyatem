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

    @Query(value = "Select id from credit_card where card_number = :cardNumber", nativeQuery = true)
    Integer getIdByCreditCardNumber(@Param("cardNumber") Integer cardNumber);

    @Query(value = "SELECT credir_card_balanse FROM credit_card WHERE card_number = :cardNumber", nativeQuery = true)
    Double getCresitCardBalance(@Param("cardNumber") Integer cardNumber);

    @Query(value = "SELECT customer_id FROM credit_card WHERE card_number = :cardNumber", nativeQuery = true)
    Integer getCustomerIdByCreditCardNumber(@Param("cardNumber") Integer cardNumber);
}
