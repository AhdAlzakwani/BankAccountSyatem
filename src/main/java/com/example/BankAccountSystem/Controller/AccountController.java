package com.example.BankAccountSystem.Controller;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import com.example.BankAccountSystem.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "addAccount", method = RequestMethod.POST)
    public String addAccount(@RequestBody AddNewAccountForStudent accountInfo) {
        accountService.addAccount(accountInfo);
        String account = "Account Add Successfully";
//        slackClient.sendMessage(sName);
        return account;

    }


    @RequestMapping(value = "retriveBalanceBySpacificAccount", method = RequestMethod.GET)
    public Account getBalanceBySpacificAccount(@RequestParam Integer accountNumber){
        Account account = accountService.getBalanceBySpacificAccount(accountNumber);
        return account;
    }

    @RequestMapping(value = "getCustomerAccountInformation", method = RequestMethod.GET)
    public List<Account> getCustomerAccountInformation(@RequestParam Integer customerId){
        List<Account> account = accountService.getCustomerAccountInformation(customerId);
        return account;
    }
}
