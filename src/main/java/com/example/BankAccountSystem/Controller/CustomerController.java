package com.example.BankAccountSystem.Controller;

import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.ObjectRequest.UpdateCustomerInfo;
import com.example.BankAccountSystem.Services.CustomerService;
import com.example.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    SlackClient slackClient;

    @RequestMapping(value = "addCustomer", method = RequestMethod.POST)
    public String addcustomer(@RequestBody Customer customerInfo) {
        customerService.addCustomer(customerInfo);
        String cName = "Customer Add Successfull";
        slackClient.sendMessage(cName);
        return cName;

    }

    @RequestMapping(value = "updateCustomerInfo", method = RequestMethod.POST)
    public String updateCustomerInfo(@RequestBody UpdateCustomerInfo updateCustomerInfo) {
        customerService.updateCustomerInfo(updateCustomerInfo);
        String cName = "Customer Update Successfully";
        slackClient.sendMessage(cName);
        return cName;

    }




}
