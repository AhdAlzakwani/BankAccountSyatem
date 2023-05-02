package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.Models.Loan;
import com.example.BankAccountSystem.Models.Transaction;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import com.example.BankAccountSystem.Reprository.AccountReprository;
import com.example.BankAccountSystem.Reprository.CustomerReprository;
import com.example.BankAccountSystem.Reprository.LoanReprository;
import com.example.BankAccountSystem.Reprository.TransactionReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

@Autowired
    AccountReprository accountReprository;
@Autowired
CustomerReprository customerReprository;

@Autowired
    TransactionReprository transactionReprository;


    public void addAccount(AddNewAccountForStudent accountInfo){
        Account account = new Account();

        account.setAccountNumber(accountInfo.getAccountNumber());
        account.setAccountBalance(accountInfo.getAccountBalance());
        Integer customerId = customerReprository.findById(accountInfo.getCustomerName());
        Customer customer = customerReprository.findById(customerId).get();
        account.setCustomer(customer);
        account.setIsActive(accountInfo.getIsActive());
        accountReprository.save(account);
    }


    public Account getBalanceBySpacificAccount(Integer accountNumber){
        Account account = accountReprository.getBalanceBySpacificAccount(accountNumber);
        return  account;
    }

    public List<Account> getCustomerAccountInformation(Integer customerId){
        List<Account> account = accountReprository.getCustomerAccountInformation(customerId);
        return  account;
    }



    public String generateMonthlyStatement(Integer accountId) {
        Account account = accountReprository.getAccountById(accountId);
        Customer customer = account.getCustomer();
        String statementForAccount = "Monthly Statement for Account: " + account.getAccountNumber() + "\n" +
                "Customer Name: " + customer.getCustomerName() + "\n" +
                "Customer Email: " + customer.getCustomerEmail() + "\n" +
                "Customer Phone: " + customer.getCustomerPhoneNumber() + "\n" +
                "Account Balance: " + account.getAccountBalance() + "\n";
        return statementForAccount;
    }


    public List<Transaction> getAccountHistory(Integer accountId){
        Account account = accountReprository.findById(accountId).get();
        List<Transaction> transactions = transactionReprository.getAllByAccount(account);
        return transactions;
    }




}
