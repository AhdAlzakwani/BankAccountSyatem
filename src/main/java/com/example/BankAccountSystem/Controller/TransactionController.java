package com.example.BankAccountSystem.Controller;

import com.example.BankAccountSystem.Models.Transaction;
import com.example.BankAccountSystem.ObjectRequest.AccountTransection;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import com.example.BankAccountSystem.ObjectRequest.UpdateTransactionRequest;
import com.example.BankAccountSystem.Services.TransactionService;
import com.example.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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

    @RequestMapping(value = "updateTransaction", method = RequestMethod.POST)
    public String updateTransaction(@RequestBody @Valid UpdateTransactionRequest updateTransactionRequest, BindingResult bindingResult) {
        try {
            transactionService.updateTransaction(updateTransactionRequest);
            return "Transaction Updated Successfully";

        }catch (Exception e){
            return "Transaction Update Failed";
        }

    }

    @RequestMapping(value = "deleteTransaction", method = RequestMethod.POST)
    public String deleteTransaction(Integer id){
        try{
            transactionService.deleteTransaction(id);
            return "Transaction deleted Successfully";

        }catch (Exception e) {
            return "Transaction delete failed";
        }

    }

    @RequestMapping(value = "retrieveDetailsForSpecificTransaction", method = RequestMethod.GET)
    public Transaction retriveDetailsForSpecificTransaction(@RequestParam Integer transactionId){
        return transactionService.retriveDetailsForSpecificTransaction(transactionId);
    }

    @RequestMapping(value = "retrieveAllTransactionDetailsForSpecificAccount", method = RequestMethod.GET)
    public List<Transaction> retrieveAllTransactionDetailsForSpecificAccount(@RequestParam Integer accountId){
        return transactionService.retrieveAllTransactionDetailsForSpecificAccount(accountId);
    }

    @RequestMapping(value = "generateReportOffAllTransactionsWithinASpecificTimePeriod", method = RequestMethod.GET)
    public void generateReport() throws Exception {
        transactionService.generateReportOffAllTransactionsWithinASpecificTimePeriod();



    }





}
