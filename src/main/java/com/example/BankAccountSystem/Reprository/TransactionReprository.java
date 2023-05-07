package com.example.BankAccountSystem.Reprository;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionReprository extends JpaRepository<Transaction, Integer> {

    List<Transaction> getAllByAccount(Account account);

    @Query(value = "UPDATE bank_transaction SET is_active='false' WHERE id = :id",nativeQuery = true)
    void deleteTransaction(@Param("id")Integer id);

    @Query(value = "SELECT t FROM Transaction t WHERE t.id = :id")
    Transaction retriveDetailsForSpecificTransaction(@Param("id")Integer id);
    @Query(value = "SELECT t FROM Transaction t WHERE t.account.id = :id")
   List<Transaction> retrieveAllTransactionDetailsForSpecificAccount(@Param("id")Integer id);
}
