package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.Models.Transaction;
import com.example.BankAccountSystem.ObjectRequest.AccountTransection;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import com.example.BankAccountSystem.Reprository.AccountReprository;
import com.example.BankAccountSystem.Reprository.CustomerReprository;
import com.example.BankAccountSystem.Reprository.TransactionReprository;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

@Autowired
TransactionReprository transactionReprository;

@Autowired
AccountReprository accountReprository;

@Autowired
    CustomerReprository customerReprository;

    @Autowired
    private JavaMailSender javaMailSender;



    @Value("${spring.mail.username}")
    private String sender;

    public void addTransaction(AccountTransection transactionInfo){


        Transaction transactions = new Transaction();
        transactions.setAmount(transactionInfo.getAccountAmount());
        Integer accountId = accountReprository.findByAccountNumber(transactionInfo.getAccountNumber());
        Account customer = accountReprository.findById(accountId).get();
        transactions.setAccount(customer);
        transactions.setIsActive(transactionInfo.getIsActive());
        Double balance = accountReprository.getBalanceByCustomerId(transactionInfo.getCustomerId());



        if(transactionInfo.getAccountAmount() > balance)
        {

            try {

                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom(sender);
                String customerMAil = customerReprository.getMailById(transactionInfo.getCustomerId());
                mailMessage.setTo(customerMAil);
                mailMessage.setText("Thank For Using BANK MUSCAT TO TRANSACTION \n"+"Sorry !!!!! :\n YOUR BALANCE VERY LOW CAN NOT COMPLETE YOUR REQUEST");
                mailMessage.setSubject("Bank Muscat Notification");

                // Sending the mail
                javaMailSender.send(mailMessage);

            }

// Catch block to handle the exceptions
            catch (Exception e) {
            }



        }
        else {
            transactionReprository.save(transactions);

            Account account = new Account();
            account.setId(transactionInfo.getId());
            Customer customerId = customerReprository.findById(transactionInfo.getCustomerId()).get();
            account.setCustomer(customerId);

            account.setAccountNumber(transactionInfo.getAccountNumber());
            Double balanceAfterTransaction = balance - transactionInfo.getAccountAmount();
            account.setAccountBalance(balanceAfterTransaction);
            account.setIsActive(transactionInfo.getIsActive());
            accountReprository.save(account);
            try {

                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom(sender);
                String customerMAil = customerReprository.getMailById(transactionInfo.getCustomerId());
                mailMessage.setTo(customerMAil);
                mailMessage.setText("Thank For Using BANK MUSCAT TO TRANSACTION \n" + "Your Balance Before Transaction was :\t" + balance + "\n You send \t" + transactionInfo.getAccountAmount() + "OR \n" + "Your Balance after Transection are :\t" + balanceAfterTransaction);
                mailMessage.setSubject("Bank Muscat Notification");

                // Sending the mail
                javaMailSender.send(mailMessage);

            }

// Catch block to handle the exceptions
            catch (Exception e) {
            }

        }



    }


    public List<Transaction> getCustomerTransactionAccountList(Integer customerId){
        Account account = accountReprository.getAccountByCustomerId(customerId);
        List<Transaction> transactions = transactionReprository.getAllByAccount(account);
        return transactions;
    }
}
