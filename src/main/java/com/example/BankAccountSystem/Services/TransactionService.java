package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.Models.Transaction;
import com.example.BankAccountSystem.ObjectRequest.AccountTransection;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import com.example.BankAccountSystem.Reprository.AccountReprository;
import com.example.BankAccountSystem.Reprository.CustomerReprository;
import com.example.BankAccountSystem.Reprository.TransactionReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

@Autowired
TransactionReprository transactionReprository;

@Autowired
AccountReprository accountReprository;

@Autowired
    CustomerReprository customerReprository;

    public void addTransaction(AccountTransection transactionInfo){
        Transaction transactions = new Transaction();

        transactions.setAmount(transactionInfo.getAccountAmount());
        Integer accountId = accountReprository.findByAccountNumber(transactionInfo.getAccountNumber());
        Account customer = accountReprository.findById(accountId).get();
        transactions.setAccount(customer);
        transactions.setIsActive(transactionInfo.getIsActive());
        transactionReprository.save(transactions);


        Account account = new Account();

        account.setId(transactionInfo.getId());
        Customer customerId = customerReprository.findById(transactionInfo.getCustomerId()).get();
        account.setCustomer(customerId);
        account.setAccountNumber(transactionInfo.getAccountNumber());
        account.setAccountBalance((transactionInfo.getBalance())-(transactionInfo.getAccountAmount()));
        account.setIsActive(transactionInfo.getIsActive());
        accountReprository.save(account);



    }
}
