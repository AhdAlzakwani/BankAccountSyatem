package com.example.BankAccountSystem.Services;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.Models.Loan;
import com.example.BankAccountSystem.ObjectRequest.NewLoanRequest;
import com.example.BankAccountSystem.Reprository.AccountReprository;
import com.example.BankAccountSystem.Reprository.CustomerReprository;
import com.example.BankAccountSystem.Reprository.LoanReprository;
import com.example.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {
@Autowired
    LoanReprository loanReprository;
@Autowired
    CustomerReprository customerReprository;
@Autowired
    AccountReprository accountReprository;

@Autowired
    SlackClient slackClient;

Double interest = 3.5;

    public void applyNewLoan(NewLoanRequest loanInfo){
        Loan loan = new Loan();
        loan.setAmount(loanInfo.getAmount());
        loan.setIsActive(loanInfo.getIsActive());
        Integer customerId = accountReprository.getCustomerIdByaccountNumber(loanInfo.getAccountNumber());
        Customer customer = customerReprository.findById(customerId).get();
        loan.setCoustomer(customer);
        loan.setInterest(loanInfo.getAmount() * interest);
        loanReprository.save(loan);


        Account account = new Account();
        Integer accountID = accountReprository.findByAccountNumber(loanInfo.getAccountNumber());
        account.setId(accountID);
        account.setAccountNumber(loanInfo.getAccountNumber());
        account.setIsActive(loanInfo.getIsActive());
        Double accountBalance = accountReprository.getBalanceByAccountNumber(loanInfo.getAccountNumber());
        Double newBalance = loanInfo.getAmount() + accountBalance;
        account.setAccountBalance(newBalance);
        account.setCustomer(customer);
        account.setUpdatedDate(account.getUpdatedDate());
        accountReprository.save(account);




    }

    public String getCustomerStatusOfLoan( Integer customerId){
        return loanReprository.getCustomerStatusOfLoan(customerId);
    }

    public String getStatusOfLoan(Integer loanId){
        return loanReprository.getStatusOfLoan(loanId);
    }

    public void deleteLoan(Integer id){

         loanReprository.deleteLona(id);
    }

    public Loan calculateLoanInterest(Integer loanId, Integer interestRate) {
        Loan loan = loanReprository.findById(loanId).get();
        Integer currentBalance = loan.getAmount();
        Integer interestCalculation = currentBalance * interestRate;
        Integer newBalance = currentBalance + interestCalculation;
        loan.setAmount(newBalance);
        loanReprository.save(loan);
        return loan;
    }


    public Loan approveOrRejectLoan(Integer loanId, double cardScore) {
        Loan loan = loanReprository.findById(loanId).get();

        if (cardScore >= 850) {
            loan.setIsActive("true");
            slackClient.sendMessage("New laon application approved - Loan ID: " + loanId);
        } else {
            loan.setIsActive("false");
            slackClient.sendMessage("New loan application rejected - Loan ID: " + loanId);
        }
        loanReprository.save(loan);
        return loan;
    }

//    public String generateReportForLoanPayment() {
//
//        public String generateReportForLoanPayment() {
//            List<Loan> findAllLoan = loanReprository.findAll();
//            List<LoanPaymentDTO> loanPaymentDTOS = new ArrayList<>();
//
//            for (LoanPayment loanPayment : findAllLoan) {
//                Loan loanPayment1 = loanPayment.getLoan();
//
//                LoanPaymentDTO loanPaymentDTO = new LoanPaymentDTO(
//                        loanPayment.getId(),
//                        loanPayment1.getId(),
//                        loanPayment1.getAmount(),
//                        loanPayment.getPaymentAmount(),
//                        loanPayment.getPaymentDate()
//
//                );
//
//                loanPaymentDTOS.add(loanPaymentDTO);
//            }
//
//            File file = new File("C:\\Users\\user015\\Documents\\BankAccountSystem\\src\\main\\resources\\LoanPaymentReport.jrxml");
//            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(loanPaymentDTOS);
//            Map<String, Object> parameters = new HashMap<>();
//            parameters.put("CreatedBy", "Ruqiya");
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//            JasperExportManager.exportReportToPdfFile(jasperPrint, pathToReports + "\\LoanPaymentReport.pdf");
//
//            return "Report generated: " + pathToReports + "\\LoanPaymentReport.pdf";
//        }
//
//
//
//    }
}
