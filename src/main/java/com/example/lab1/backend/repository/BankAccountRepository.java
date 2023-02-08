package com.example.lab1.backend.repository;

import com.example.lab1.backend.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findBankAccountModelById(Long id);
}
