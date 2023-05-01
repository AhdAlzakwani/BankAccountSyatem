package com.example.BankAccountSystem.Reprository;

import com.example.BankAccountSystem.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerReprository extends JpaRepository<Customer, Integer> {

    @Query(value = "Select id from customer Where customer_name = :customerName", nativeQuery = true)
    Integer findById(@Param("customerName") String customerName);
}
