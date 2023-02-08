package com.example.lab1.backend.controller;

import com.example.lab1.backend.model.BankAccount;
import com.example.lab1.backend.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankAccountController {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @GetMapping("/{id}")
    public BankAccount findBankAccountById(@PathVariable Long id) {
        return bankAccountRepository.findBankAccountModelById(id);
    }

    @GetMapping("/{id}/saldo")
    public double getSaldo(@PathVariable Long id) {
        return bankAccountRepository.findBankAccountModelById(id).getSaldo();
    }

   @PostMapping("/{id}/deposit")
    public BankAccount deposit(@PathVariable Long id, @RequestParam(value = "saldo", defaultValue = "0") double saldo) {
       BankAccount bankAccount = bankAccountRepository.findBankAccountModelById(id);
       bankAccount.depositar(saldo);
       return bankAccountRepository.save(bankAccount);

    }
/*
    @GetMapping("/extraction")
    public String extraction(@RequestParam(value = "saldo", defaultValue = "0") double saldo) {
        this.bankAccount.extraccion(saldo);
        return String.format("La cuenta va a extraer $%s. Saldo total $%.2f", saldo, this.bankAccount.getSaldo());
    }

    @GetMapping("/interes")
    public String interest(@RequestParam(value = "interes", defaultValue = "0") int interes) {
        this.bankAccount.interes(interes);
        return String.format("La cuenta recibirá %s de interés. Saldo total $%.2f", interes, this.bankAccount.getSaldo());
    }
*/
}
