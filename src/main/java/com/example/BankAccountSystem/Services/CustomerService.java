package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.ObjectRequest.UpdateCustomerInfo;
import com.example.BankAccountSystem.Reprository.CustomerReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerReprository customerReprository;


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







}
