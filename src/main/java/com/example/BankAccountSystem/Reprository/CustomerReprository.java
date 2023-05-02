package com.example.BankAccountSystem.Reprository;

import com.example.BankAccountSystem.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReprository extends JpaRepository<Customer, Integer> {

    @Query(value = "Select id from customer Where customer_name = :customerName", nativeQuery = true)
    Integer findById(@Param("customerName") String customerName);

    @Query(value = "Select customer_email from customer Where id = :customerId", nativeQuery = true)
    String getMailById(@Param("customerId") Integer customerId);

    @Query(value = "Select id from customer Where customer_name = :customerName", nativeQuery = true)
    Integer findByCustomerName(@Param("customerName") String customerName);


}
