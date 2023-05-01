package com.example.BankAccountSystem.Reprository;

import com.example.BankAccountSystem.Models.Account;
import com.example.BankAccountSystem.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountReprository extends JpaRepository<Account, Integer> {
}
