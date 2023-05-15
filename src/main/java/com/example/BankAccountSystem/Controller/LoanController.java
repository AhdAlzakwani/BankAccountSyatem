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

    @Autowired
    SlackClient slackClient;


    @RequestMapping(value = "addLoan", method = RequestMethod.POST)
    public String addLoan(@RequestBody NewLoanRequest loan) {
        try {
            loanService.applyNewLoan(loan);
            slackClient.sendMessage("Loan Approved");

            return  "Loan Added Successfully";

        }catch (Exception e){
            slackClient.sendMessage("Loan Rejected");

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


    @PostMapping("calculateLoanInterest")
    public String calculateCardInterest(@RequestParam Integer loanId, Integer interestRate) {

            Loan loan = loanService.calculateLoanInterest(loanId, interestRate);

            if (loan == null) {
                return "Loan not found with id: " + loanId;
            }
           return  "Interest calculation made successfully\n" +
                    "loan Id: " + loan.getId() + "\n" +
                    "Interest Rate: " + interestRate + "\n" +
                    "Loan new Balance: " + loan.getAmount();

    }

    @PostMapping("loanStatus")
    public String approveOrRejectLoan(@RequestParam Integer loanId, double cardScore) {
            Loan loan = loanService.approveOrRejectLoan(loanId, cardScore);
            String message;
            if (loan.getIsActive().equals("true")) {
                message = "Loan application approved";
            } else {
                message = "Loan not found";
            }
            return message;

    }

    @RequestMapping(value = "/LoanPaymentReport", method = RequestMethod.GET)
    public String generateReportForLoanPayment() throws Exception {

        return loanService.generateReportForLoanPayment();
    }


}
