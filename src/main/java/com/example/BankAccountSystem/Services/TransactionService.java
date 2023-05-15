package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.Models.Transaction;
import com.example.BankAccountSystem.ObjectRequest.*;
import com.example.BankAccountSystem.Reprository.AccountReprository;
import com.example.BankAccountSystem.Reprository.CustomerReprository;
import com.example.BankAccountSystem.Reprository.TransactionReprository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
    public static final String pathToReports = "C:\\Users\\User009\\Downloads\\reports";



    @Value("${spring.mail.username}")
    private String sender;

    public void addTransaction(AccountTransection transactionInfo){

        Double fees = 0.150;
        Transaction transactions = new Transaction();
        transactions.setAmount(transactionInfo.getAccountAmount());
        Integer accountId = accountReprository.findByAccountNumber(transactionInfo.getAccountNumber());
        Account customer = accountReprository.findById(accountId).get();
        Double transactionWithFees = transactionInfo.getAccountAmount() * fees ;
        transactions.setAccount(customer);
        transactions.setIsActive(transactionInfo.getIsActive());
        Double balance = accountReprository.getBalanceByCustomerId(transactionInfo.getCustomerId());



        if(transactionWithFees > balance)
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
            Double balanceAfterTransaction = balance- transactionWithFees;
            account.setAccountBalance(balanceAfterTransaction);
            account.setIsActive(transactionInfo.getIsActive());
            accountReprository.save(account);
            try {

                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom(sender);
                String customerMAil = customerReprository.getMailById(transactionInfo.getCustomerId());
                mailMessage.setTo(customerMAil);
                mailMessage.setText("Thank For Using BANK MUSCAT TO TRANSACTION \n" + "Your Balance Before Transaction was :\t" + balance + "\n You send \t" + transactionInfo.getAccountAmount() + "The Fees For Transaction : \t"+fees+ "OR \n" + "Your Balance after Transection are :\t" + balanceAfterTransaction);
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
public void updateTransaction(UpdateTransactionRequest updateTransactionRequest){
    Transaction transactions = new Transaction();
    transactions.setId(updateTransactionRequest.getId());
    transactions.setAmount(transactions.getAmount());
    Integer accountId = accountReprository.findByAccountNumber(updateTransactionRequest.getAccountNumber());
    Account customer = accountReprository.findById(accountId).get();
    transactions.setAccount(customer);
    transactions.setIsActive(updateTransactionRequest.getIsActive());
}

    public void deleteTransaction(Integer id){
        transactionReprository.deleteTransaction(id);
    }

    public Transaction retriveDetailsForSpecificTransaction(Integer transactionId){

        return transactionReprository.retriveDetailsForSpecificTransaction(transactionId);
    }
    public List<Transaction> retrieveAllTransactionDetailsForSpecificAccount(Integer accountId){

        return transactionReprository.retrieveAllTransactionDetailsForSpecificAccount(accountId);
    }

    public String generateReportOffAllTransactionsWithinASpecificTimePeriod() throws FileNotFoundException, JRException {

        List<Transaction> transactions = transactionReprository.findAll();
        List<GeneRateReportOffAllTransactionsWithinASpecificTimePeriod> monthlyStatmentReportForAccountsList = new ArrayList<>();
        for (Transaction transaction:transactions) {
            Integer transactionId = transactions.get(0).getId();
            Integer accountId  = transaction.getAccount().getId();
            Integer accountNumber =transaction.getAccount().getAccountNumber();
            Integer transactionAmount = transaction.getAmount();
            Date createdDate = transaction.getCreatedDate();
            GeneRateReportOffAllTransactionsWithinASpecificTimePeriod accountReport = new
                    GeneRateReportOffAllTransactionsWithinASpecificTimePeriod( transactionId,  accountId,  accountNumber, transactionAmount, createdDate);
            monthlyStatmentReportForAccountsList.add(accountReport);

        }



        File file = new File("C:\\Users\\User009\\Downloads\\BankAccountSystem\\BankAccountSystem\\src\\main\\resources\\generateReportOffAllTransactionsWithinASpecificTimePeriod.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(monthlyStatmentReportForAccountsList);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("CreatedBy", "AhdYahya");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,paramters , dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, pathToReports+"\\generateReportOffAllTransactionsWithinASpecificTimePeriod.pdf");
        return "Report generated : " + pathToReports+"\\generateReportOffAllTransactionsWithinASpecificTimePeriod.pdf";
    }




}

