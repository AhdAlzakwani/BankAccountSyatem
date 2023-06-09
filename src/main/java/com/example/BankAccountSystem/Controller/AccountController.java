package com.example.BankAccountSystem.Controller;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Transaction;
import com.example.BankAccountSystem.ObjectRequest.AccountInfo;
import com.example.BankAccountSystem.ObjectRequest.AccountTransection;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import com.example.BankAccountSystem.Services.AccountService;
import com.example.BankAccountSystem.Services.TransactionService;
import com.example.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    SlackClient slackClient;

    @RequestMapping(value = "addAccount", method = RequestMethod.POST)
    public String addAccount(@RequestBody @Valid AddNewAccountForStudent accountInfo, BindingResult bindingResult) {
        accountService.addAccount(accountInfo);
        String account = "Account Add Successfully";
        slackClient.sendMessage(account);
        return account;

    }

    @RequestMapping(value = "UpdateAccount", method = RequestMethod.POST)
    public String UpdateAccount(@RequestBody @Valid AccountInfo accountInfo, BindingResult bindingResult) {
        try {
            accountService.updateAccount(accountInfo);
            return "Account updated Successfully";
        }catch (Exception e) {
            return "Account updated Failed";

        }

    }

    @RequestMapping(value = "deleteAccount", method = RequestMethod.POST)
    public String deleteAccount(Integer id){
        try{
            accountService.deleteAccount(id);
            return "Account deleted Successfully";

        }catch (Exception e) {
            return "Account delete failed";
        }

    }





    @RequestMapping(value = "retriveBalanceBySpacificAccount", method = RequestMethod.GET)
    public Account getBalanceBySpacificAccount(@RequestParam Integer accountNumber){
        Account account = accountService.getBalanceBySpacificAccount(accountNumber);

        slackClient.sendMessage(account.toString());
        return account;
    }
    @RequestMapping(value = "addTransactionAndUpdateAccountBalance", method = RequestMethod.GET)
    public void addTransactionAndUpdateAccountBalance(@RequestBody  @Valid AccountTransection accountTransaction, BindingResult bindingResult){
        transactionService.addTransaction(accountTransaction);
    }




    @RequestMapping(value = "getCustomerAccountInformation", method = RequestMethod.GET)
    public List<Account> getCustomerAccountInformation(@RequestParam Integer customerId){
        List<Account> account = accountService.getCustomerAccountInformation(customerId);
        return account;
    }


    @RequestMapping(value = "calculateInterestOnAccountBalance", method = RequestMethod.GET)
    public Double calculateInterestOnAccountBalance(@RequestParam Integer accountNumber){
        return accountService.calculateInterestOnAccountBalance(accountNumber);
    }


    @RequestMapping(value = "getMonthlyStatementsForAccount", method = RequestMethod.GET)
    public String generateMonthlyStatementForAccount(@RequestParam Integer accountId) {
        String statement = accountService.generateMonthlyStatement(accountId);
        return statement;
    }

    @RequestMapping(value = "generateReportMonthlyStatementsForAccount", method = RequestMethod.GET)
    public void generateReport(@RequestParam Integer accountId) throws Exception {
        accountService.generateReportMonthlyStatementsForAccount(accountId);

    }

    @RequestMapping(value = "getAccountHistory", method = RequestMethod.GET)
    public List<Transaction> getAccountHistory(@RequestParam Integer accountId) {
        List<Transaction> account = accountService.getAccountHistory(accountId);
        return account;
    }


}
