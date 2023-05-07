package com.example.BankAccountSystem.Controller;

import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.Models.Loan;
import com.example.BankAccountSystem.ObjectRequest.NewLoanRequest;
import com.example.BankAccountSystem.Services.AccountService;
import com.example.BankAccountSystem.Services.CustomerService;
import com.example.BankAccountSystem.Services.LoanService;
import com.example.BankAccountSystem.Services.TransactionService;
import com.example.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/loan")
public class LoanController {

    @Autowired
    LoanService loanService;

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;


    @RequestMapping(value = "addLoan", method = RequestMethod.POST)
    public String addLoan(@RequestBody NewLoanRequest loan) {
        try {
            loanService.applyNewLoan(loan);
            return  "Loan Added Successfully";
        }catch (Exception e){
            return "Loan Added Failed";

        }

    }


    @RequestMapping(value = "deleteLoan", method = RequestMethod.POST)
    public String deleteLoan(@RequestParam Integer id) {
        try {
            loanService.deleteLoan(id);
            return  "Loan deleted Successfully";
        }catch (Exception e){
            return "Loan deleted Failed";

        }

    }

    @RequestMapping(value = "getStatusOfLoan", method = RequestMethod.GET)
    public String getStatusOfLoan(@RequestParam Integer loanId) {
        return loanService.getStatusOfLoan(loanId);
    }





}
