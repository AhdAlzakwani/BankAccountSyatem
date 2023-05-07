package com.example.BankAccountSystem.Reprository;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountReprository extends JpaRepository<Account, Integer> {

    @Query(value = "Select s from Account s Where s.accountNumber = :accountNumber")
    Account getBalanceBySpacificAccount(@Param("accountNumber") Integer accountNumber);

    @Query(value = "Select s from Account s Where s.customer.id = :customerId")
    List<Account> getCustomerAccountInformation(@Param("customerId") Integer customerId);

    @Query(value = "Select id from Account s Where s.accountNumber = :accountNumber")
    Integer findByAccountNumber(@Param("accountNumber") Integer accountNumber);

    @Query(value = "Select account_balance from account Where customer_id = :customerId", nativeQuery = true)
    Double getBalanceByCustomerId(@Param("customerId") Integer customerId);

    @Query(value = "Select a from Account a Where a.id = :accountId")
    Account getAccountById(@Param("accountId") Integer accountId);

    List<Account> getAllByCustomer(Customer customer);

    @Query(value = "Select a from Account a Where a.customer.id = :customerId")
    Account getAccountByCustomerId(@Param("customerId") Integer customerId);






}
