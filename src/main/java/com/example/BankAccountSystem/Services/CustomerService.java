package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.Models.Transaction;
import com.example.BankAccountSystem.ObjectRequest.UpdateCustomerInfo;
import com.example.BankAccountSystem.Reprository.AccountReprository;
import com.example.BankAccountSystem.Reprository.CustomerReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerReprository customerReprository;

    @Autowired
    AccountReprository accountReprository;


    public void addCustomer(Customer customerInfo){
        Customer customer = new Customer();
        customer.setCustomerName(customerInfo.getCustomerName());
        customer.setCustomerGender(customerInfo.getCustomerGender());
        customer.setCustomerAge(customerInfo.getCustomerAge());
        customer.setCustomerPhoneNumber(customerInfo.getCustomerPhoneNumber());
        customer.setCustomerEmail(customerInfo.getCustomerEmail());

        customerReprository.save(customer);
    }

    public void updateCustomerInfo(UpdateCustomerInfo updateCustomerInfo){
        Customer customer = new Customer();
        customer.setId(updateCustomerInfo.getId());
        customer.setCustomerName(updateCustomerInfo.getCustomerName());
        customer.setCustomerGender(updateCustomerInfo.getCustomerGender());
        customer.setCustomerAge(updateCustomerInfo.getCustomerAge());
        customer.setCustomerPhoneNumber(updateCustomerInfo.getCustomerPhoneNumber());
        customer.setCustomerEmail(updateCustomerInfo.getCustomerEmail());
        customerReprository.save(customer);
    }


    public List<Account> getAccountHistory(Integer custommerId){
        Customer customer = customerReprository.findById(custommerId).get();
        List<Account> accounts = accountReprository.getAllByCustomer(customer);
        return accounts;
    }

//    public void deleteCustomer(Integer id){
//        customerReprository.deleteCustomer(id);
//    }

    public List<Account> getCustomerAccountInformation(Integer custommerId){
        List<Account> accounts = accountReprository.getCustomerAccountInformation(custommerId);
        return accounts;
    }








}
