package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.Models.Loan;
import com.example.BankAccountSystem.Models.Transaction;
import com.example.BankAccountSystem.ObjectRequest.AccountInfo;
import com.example.BankAccountSystem.ObjectRequest.AddNewAccountForStudent;
import com.example.BankAccountSystem.ObjectRequest.MonthlyStatmentReportForAccount;
import com.example.BankAccountSystem.Reprository.AccountReprository;
import com.example.BankAccountSystem.Reprository.CustomerReprository;
import com.example.BankAccountSystem.Reprository.LoanReprository;
import com.example.BankAccountSystem.Reprository.TransactionReprository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

@Autowired
    AccountReprository accountReprository;
@Autowired
CustomerReprository customerReprository;

@Autowired
    TransactionReprository transactionReprository;

    public static final String pathToReports = "C:\\Users\\User009\\Downloads\\reports";


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


    public void updateAccount(AccountInfo accountInfo) {
        Account account = new Account();
        account.setId(accountInfo.getId());
        account.setAccountNumber(accountInfo.getAccountNumber());
        account.setIsActive(accountInfo.getIsActive());
        Customer customer = customerReprository.findById(accountInfo.getCustomerId()).get();
        account.setCustomer(customer);
        accountReprository.save(account);


    }

        public void deleteAccount(Integer id){
        accountReprository.deleteAccount(id);
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

    public String generateReportMonthlyStatementsForAccount() throws FileNotFoundException, JRException {
        List<Account> account = accountReprository.findAll();
        List<MonthlyStatmentReportForAccount> monthlyStatmentReportForAccountsList = new ArrayList<>();
       for (Account a:account) {
           Integer accounId= a.getId();
           String customerName = a.getCustomer().getCustomerName();
           String customerEmail =a.getCustomer().getCustomerEmail();
           String customerPhoneNumber = a.getCustomer().getCustomerPhoneNumber();
           Double accountBalance = a.getAccountBalance();
           MonthlyStatmentReportForAccount accountReport = new MonthlyStatmentReportForAccount( accounId,  customerName,  customerEmail, customerPhoneNumber, accountBalance);
           monthlyStatmentReportForAccountsList.add(accountReport);

       }



        File file = new File("C:\\Users\\User009\\Downloads\\BankAccountSystem\\BankAccountSystem\\src\\main\\resources\\monthlyStatmentReportForAccountsList.jrxml");
//        File file = new File("src/main/resources/monthlyStatmentReportForAccountsList.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(monthlyStatmentReportForAccountsList);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("CreatedBy", "AhdYahya");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,paramters , dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, pathToReports+"\\monthlyStatmentReportForAccountsList.pdf");
        return "Report generated : " + pathToReports+"\\monthlyStatmentReportForAccountsList.pdf";
    }


    public List<Transaction> getAccountHistory(Integer accountId){
        Account account = accountReprository.findById(accountId).get();
        List<Transaction> transactions = transactionReprository.getAllByAccount(account);
        return transactions;
    }


    public Double calculateInterestOnAccountBalance(Integer accountNumber) {
        Double accountBalance = accountReprository.getBalanceByAccountNumber(accountNumber);
        Double interest = 2.5;
        return accountBalance * interest;

    }
}
