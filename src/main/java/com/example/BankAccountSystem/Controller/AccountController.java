package com.example.BankAccountSystem.Controller;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.Models.Transaction;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import com.example.BankAccountSystem.Services.AccountService;
import com.example.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    SlackClient slackClient;

    @RequestMapping(value = "addAccount", method = RequestMethod.POST)
    public String addAccount(@RequestBody AddNewAccountForStudent accountInfo) {
        accountService.addAccount(accountInfo);
        String account = "Account Add Successfully";
        slackClient.sendMessage(account);
        return account;

    }


    @RequestMapping(value = "retriveBalanceBySpacificAccount", method = RequestMethod.GET)
    public Account getBalanceBySpacificAccount(@RequestParam Integer accountNumber){
        Account account = accountService.getBalanceBySpacificAccount(accountNumber);

        slackClient.sendMessage(account.toString());
        return account;
    }

    @RequestMapping(value = "getCustomerAccountInformation", method = RequestMethod.GET)
    public List<Account> getCustomerAccountInformation(@RequestParam Integer customerId){
        List<Account> account = accountService.getCustomerAccountInformation(customerId);
        for (Account a : account) {

            slackClient.sendMessage(a.toString());
        }
        return account;
    }


    @RequestMapping(value = "getMonthlyStatementsForAccount", method = RequestMethod.GET)
    public String generateMonthlyStatementForAccount(@RequestParam Integer accountId) {
        String statement = accountService.generateMonthlyStatement(accountId);
        return statement;
    }

    @RequestMapping(value = "getAccountHistory", method = RequestMethod.GET)
    public List<Transaction> getAccountHistory(@RequestParam Integer accountId) {
        List<Transaction> account = accountService.getAccountHistory(accountId);
        return account;
    }


}
