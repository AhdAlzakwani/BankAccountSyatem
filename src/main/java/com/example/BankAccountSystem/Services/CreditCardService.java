package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.CreditCard;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.ObjectRequest.CustomerPayment;
import com.example.BankAccountSystem.ObjectRequest.NewCreditCardInfo;
import com.example.BankAccountSystem.Reprository.AccountReprository;
import com.example.BankAccountSystem.Reprository.CridetCardReprository;
import com.example.BankAccountSystem.Reprository.CustomerReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
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

    public void addCreditCardPayment(CustomerPayment customerPayment){
        CreditCard creditCard = new CreditCard();
        Integer creditId = cridetCardReprository.getIdByCreditCardNumber(customerPayment.getCreditCardNumber());
        creditCard.setId(creditId);
        creditCard.setCardNumber(customerPayment.getCreditCardNumber());

        Double creditCardBalance = cridetCardReprository.getCresitCardBalance(customerPayment.getCreditCardNumber());
        Double balance = creditCardBalance - customerPayment.getPayment();
        creditCard.setCredirCardBalanse(balance);

        Integer customerId = cridetCardReprository.getCustomerIdByCreditCardNumber(customerPayment.getCreditCardNumber());
        Customer customer = customerReprository.findById(customerId).get();
        creditCard.setCoustomer(customer);
        creditCard.setIsActive(customerPayment.getIsActive());
        if(customerPayment.getPayment() < creditCardBalance){

            cridetCardReprository.save(creditCard);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            String customerMAil = customerReprository.getMailById(customerId);
            mailMessage.setTo(customerMAil);
            mailMessage.setText("Thank For Using BANK MUSCAT TO TRANSACTION \n" +
                    "Your Balance Before Transaction was :\t" + creditCardBalance +
                    "\n You send \t" + customerPayment.getPayment()+ "OR \n" +
                    "Your Balance after Payment are :\t" + balance);

            mailMessage.setSubject("Bank Muscat Notification");

            // Sending the mail
            javaMailSender.send(mailMessage);

        }
        else{
            try{
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom(sender);
                String customerMAil = customerReprository.getMailById(customerId);
                mailMessage.setTo(customerMAil);
                mailMessage.setText("Thank For Using BANK MUSCAT \n"+"Sorry !!!!! :\n YOUR BALANCE VERY LOW CAN NOT COMPLETE YOUR REQUEST");
                mailMessage.setSubject("Bank Muscat Notification");

                // Sending the mail
                javaMailSender.send(mailMessage);

            }catch (Exception e){

            }
        }


    }


}
