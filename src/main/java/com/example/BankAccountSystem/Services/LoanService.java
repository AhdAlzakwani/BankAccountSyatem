package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Account;
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
        Integer customerId = accountReprository.getCustomerIdByaccountNumber(loanInfo.getAccountNumber());
        Customer customer = customerReprository.findById(customerId).get();
        loan.setCoustomer(customer);
        loan.setInterest(loanInfo.getAmount() * interest);
        loanReprository.save(loan);


        Account account = new Account();
        Integer accountID = accountReprository.findByAccountNumber(loanInfo.getAccountNumber());
        account.setId(accountID);
        account.setAccountNumber(loanInfo.getAccountNumber());
        account.setIsActive(loanInfo.getIsActive());
        Double accountBalance = accountReprository.getBalanceByAccountNumber(loanInfo.getAccountNumber());
        Double newBalance = loanInfo.getAmount() + accountBalance;
        account.setAccountBalance(newBalance);
        account.setCustomer(customer);
        account.setUpdatedDate(account.getUpdatedDate());
        accountReprository.save(account);




    }

    public String getCustomerStatusOfLoan( Integer customerId){
        return loanReprository.getCustomerStatusOfLoan(customerId);
    }

    public String getStatusOfLoan(Integer loanId){
        return loanReprository.getStatusOfLoan(loanId);
    }

    public void deleteLoan(Integer id){

         loanReprository.deleteLona(id);
    }
}
