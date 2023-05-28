package com.example.BankAccountSystem.Controller;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.Models.Loan;
import com.example.BankAccountSystem.Models.Transaction;
import com.example.BankAccountSystem.ObjectRequest.NewCreditCardInfo;
import com.example.BankAccountSystem.ObjectRequest.NewLoanRequest;
import com.example.BankAccountSystem.ObjectRequest.UpdateCustomerInfo;
import com.example.BankAccountSystem.Services.*;
import com.example.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountService accountService;

    @Autowired
    CreditCardService creditCardService;


    @Autowired
    LoanService loanService;

    @Autowired
    TransactionService transactionService;



    @Autowired
    SlackClient slackClient;

    @RequestMapping(value = "addCustomer", method = RequestMethod.POST)
    public String addcustomer(@RequestBody @Valid Customer customerInfo, BindingResult bindingResult) {
        customerService.addCustomer(customerInfo);
        String cName = "Customer Add Successfull";
        slackClient.sendMessage(cName);
        return cName;

    }

    @RequestMapping(value = "updateCustomerInfo", method = RequestMethod.POST)
    public String updateCustomerInfo(@RequestBody @Valid UpdateCustomerInfo updateCustomerInfo, BindingResult bindingResult) {
        customerService.updateCustomerInfo(updateCustomerInfo);
        String cName = "Customer Update Successfully";
        slackClient.sendMessage(cName);
        return cName;

    }

    @RequestMapping(value = "getCustomerAccountInformation", method = RequestMethod.GET)
    public List<Account> getCustomerAccountInformation(@RequestParam Integer customerId) {
        List<Account> account = customerService.getCustomerAccountInformation(customerId);
        return account;
    }
    @RequestMapping(value = "applyNewLoan", method = RequestMethod.POST)
    public String applyNewLoan(@RequestBody @Valid NewLoanRequest loanInfo, BindingResult bindingResult) {
        loanService.applyNewLoan(loanInfo);
        String cLoan = "New Loan For Customer Add Successfully";
        slackClient.sendMessage(cLoan);
        return cLoan;
    }


    @RequestMapping(value = "applyCreditCard", method = RequestMethod.POST)
    public String applyCreditCard(@RequestBody NewCreditCardInfo creditCard) {
        creditCardService.addCreditCard(creditCard);
        String creditCards = "CreditCard Add Successfully";
        slackClient.sendMessage(creditCards);
        return creditCards;

    }


    @RequestMapping(value = "getCustomerStatusOfCreditCard", method = RequestMethod.GET)
    public String getCustomerStatusOfCreditCard(@RequestParam Integer cardNumber) {
        return creditCardService.getStatusOfCreditCard(cardNumber);
    }

    @RequestMapping(value = "getCustomerStatusOfLoan", method = RequestMethod.GET)
    public String getCustomerStatusOfLoan(@RequestParam Integer customerId) {
        return loanService.getCustomerStatusOfLoan(customerId);
    }

    @RequestMapping(value = "deleteCustomer", method = RequestMethod.POST)
    public String deleteCustomer(Integer id){
        try{
//            customerService.deleteCustomer(id);
            return "Customer deleted Successfully";

        }catch (Exception e) {
            return "Customer delete failed";
        }

    }



    @RequestMapping(value = "getCustomerHistory", method = RequestMethod.GET)
    public List<Account> getCustomerHistory(@RequestParam Integer customerId) {
        List<Account> account = customerService.getAccountHistory(customerId);
        return account;
    }



    @RequestMapping(value = "getCustomerTransactionAccountList", method = RequestMethod.GET)
    public List<Transaction> getCustomerTransactionAccountList(@RequestParam Integer customerId) {
        List<Transaction> transactions = transactionService.getCustomerTransactionAccountList(customerId);
        return transactions;
    }








}
