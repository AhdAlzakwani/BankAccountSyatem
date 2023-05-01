package com.example.BankAccountSystem.Controller;

import com.example.BankAccountSystem.Models.Transaction;
import com.example.BankAccountSystem.ObjectRequest.AccountTransection;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import com.example.BankAccountSystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "addTransaction", method = RequestMethod.POST)
    public String addTransaction(@RequestBody AccountTransection transaction) {
        transactionService.addTransaction(transaction);
        String transactions = "Transaction Add Successfully";
//        slackClient.sendMessage(sName);
        return transactions;

    }




}
