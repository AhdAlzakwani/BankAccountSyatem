package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.Models.Loan;
import com.example.BankAccountSystem.ObjectRequest.NewLoanRequest;
import com.example.BankAccountSystem.Reprository.AccountReprository;
import com.example.BankAccountSystem.Reprository.CustomerReprository;
import com.example.BankAccountSystem.Reprository.LoanReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
@Autowired
    LoanReprository loanReprository;
@Autowired
    CustomerReprository customerReprository;
@Autowired
    AccountReprository accountReprository;

Double interest = 3.5;

    public void applyNewLoan(NewLoanRequest loanInfo){
        Loan loan = new Loan();
        loan.setAmount(loanInfo.getAmount());
        loan.setIsActive(loanInfo.getIsActive());
        Integer customerId = accountReprository.findByAccountNumber(loanInfo.getAccountNumber());
        Customer customer = customerReprository.findById(customerId).get();
        loan.setCoustomer(customer);
        loan.setInterest(loanInfo.getAmount() * interest );
        loanReprository.save(loan);
    }

    public String getCustomerStatusOfLoan( Integer customerId){
        return loanReprository.getCustomerStatusOfLoan(customerId);
    }
}
