package com.example.BankAccountSystem.Reprository;

import com.example.BankAccountSystem.Models.Customer;
import com.example.BankAccountSystem.Models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanReprository extends JpaRepository<Loan, Integer> {

    @Query(value = "Select is_active from loan where customer_id = :customerId", nativeQuery = true)
    String getCustomerStatusOfLoan(@Param("customerId") Integer customerId);

    @Query(value = "Select is_active from loan where id = :loanId", nativeQuery = true)
    String getStatusOfLoan(@Param("loanId") Integer loanId);

    @Query(value = "Update Loan c SET c.isActive='false' WHERE c.id = :id")
    void deleteLona(@Param("id")Integer id);

}
