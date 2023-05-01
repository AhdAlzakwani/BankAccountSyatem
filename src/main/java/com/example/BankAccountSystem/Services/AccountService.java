package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import com.example.BankAccountSystem.Reprository.AccountReprository;
import com.example.BankAccountSystem.Reprository.CustomerReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

@Autowired
    AccountReprository accountReprository;
@Autowired
CustomerReprository customerReprository;
    public void addAccount(AddNewAccountForStudent accountInfo){
        Account account = new Account();

        account.setAccountNumber(accountInfo.getAccountNumber());
        account.setAccountBalance(accountInfo.getAccountBalance());
        Integer customerId = customerReprository.findById(accountInfo.getCustomerName());
        Customer customer = customerReprository.findById(customerId).get();
        account.setCoustomer(customer);
        account.setIsActive(accountInfo.getIsActive());
        accountReprository.save(account);
    }
}
