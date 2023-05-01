package com.example.BankAccountSystem.Controller;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import com.example.BankAccountSystem.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
