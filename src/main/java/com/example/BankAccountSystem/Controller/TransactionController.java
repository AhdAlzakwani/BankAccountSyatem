package com.example.BankAccountSystem.Controller;

import com.example.BankAccountSystem.Models.Transaction;
import com.example.BankAccountSystem.ObjectRequest.AccountTransection;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import com.example.BankAccountSystem.Services.TransactionService;
import com.example.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    SlackClient slackClient;



    @RequestMapping(value = "addTransaction", method = RequestMethod.POST)
    public void addTransaction(@RequestBody AccountTransection transaction) {
        transactionService.addTransaction(transaction);

    }




}
