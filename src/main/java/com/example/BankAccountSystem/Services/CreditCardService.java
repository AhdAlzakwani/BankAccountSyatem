package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.CreditCard;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.ObjectRequest.NewCreditCardInfo;
import com.example.BankAccountSystem.Reprository.AccountReprository;
import com.example.BankAccountSystem.Reprository.CridetCardReprository;
import com.example.BankAccountSystem.Reprository.CustomerReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {

    @Autowired
    CustomerReprository customerReprository;

    @Autowired
    CridetCardReprository cridetCardReprository;

    @Autowired
    AccountReprository accountReprository;

    public void addCreditCard(NewCreditCardInfo creditCard){
        CreditCard creditCardInfo = new CreditCard();
        creditCardInfo.setCardNumber(creditCard.getCardNumber());
        String customerName = creditCard.getCustomerName();

        Integer cusid = customerReprository.findByCustomerName(customerName);
        Customer customerId = customerReprository.findById(cusid).get();




        Double balance = accountReprository.getBalanceByCustomerId(cusid);
        creditCardInfo.setCredirCardBalanse(balance);
        creditCardInfo.setIsActive(creditCard.getIsActive());
        creditCardInfo.setCoustomer(customerId);
        cridetCardReprository.save(creditCardInfo);


    }


    public String getStatusOfCreditCard( Integer cardNumber){
        return cridetCardReprository.getStatusOfCreditCard(cardNumber);
        }


    public CreditCard deletedCreditCard(Integer cardNumber){

        return cridetCardReprository.deletedCreditCard(cardNumber);
    }


}
