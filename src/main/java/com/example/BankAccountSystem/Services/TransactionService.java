package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.Models.Transaction;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

@Autowired
TransactionService transactionService;

@
    public void addTransaction(Transaction transactionInfo){
        Transaction transactions = new Transaction();
        
        transactions.setAmount(transactionInfo.getAmount());
        Integer customerId = customerReprository.findById(accountInfo.getCustomerName());
        Customer customer = customerReprository.findById(customerId).get();
        transactions.setAccount();


    }
}
