package com.example.BankAccountSystem.Controller;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.CreditCard;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import com.example.BankAccountSystem.ObjectRequest.CustomerPayment;
import com.example.BankAccountSystem.ObjectRequest.NewCreditCardInfo;
import com.example.BankAccountSystem.Services.CreditCardService;
import com.example.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/creditCard")
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;


    @Autowired
    SlackClient slackClient;


    @RequestMapping(value = "addCreditCard", method = RequestMethod.POST)
    public String addCreditCard(@RequestBody @Valid  NewCreditCardInfo creditCard, BindingResult bindingResult) {
        creditCardService.addCreditCard(creditCard);
        String creditCards = "CreditCard Add Successfully";
        slackClient.sendMessage(creditCards);
        return creditCards;

    }

    @RequestMapping(value = "getStatusOfCreditCard", method = RequestMethod.GET)
    public String getStatusOfCreditCard(@RequestParam Integer cardNumber){
        return creditCardService.getStatusOfCreditCard(cardNumber);
    }

    @RequestMapping(value = "deletedCreditCard", method = RequestMethod.POST)
    public String deletedCreditCard(@RequestParam Integer cardNumber) {
        CreditCard creditCard = creditCardService.deletedCreditCard(cardNumber);
        return "creditCard Offline Successfully";
    }

    @RequestMapping(value = "customerPaymentCreditCard", method = RequestMethod.POST)
    public String getCustomerPaymentCreditCard(@RequestBody @Valid CustomerPayment customerPayment, BindingResult bindingResult){
        try{
         creditCardService.addCreditCardPayment(customerPayment);
            return "Payment Send Successfully";
    }catch (Exception e){
            return "Payment Can't Send";
        }
    }


    @RequestMapping(value = "generateCreditCardReport", method = RequestMethod.GET)
    public void generateReport() throws Exception {
        creditCardService.generateCreditCardReport();



    }

}
