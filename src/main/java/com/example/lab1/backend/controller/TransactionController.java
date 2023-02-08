package com.example.lab1.backend.controller;

import com.example.lab1.backend.model.Transaction;
import com.example.lab1.backend.model.TransactionType;
import com.example.lab1.backend.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    public TransactionRepository transactionRepository;


    @GetMapping("/")
    public Iterable<Transaction> findAll() {
        return this.transactionRepository.findAll();
    }

    @GetMapping("/balance")
    public JsonNode getBalance() {
        List<Transaction> transactions = transactionRepository.findAll();
        double balance = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.valueOf("extraction")) {
                balance -= transaction.getMount();
            } else {
                balance += transaction.getMount();
            }
        }

        //String to JSON conversion
        String str = "{\"balance\": " + balance + " }";

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("/deposit")
    public Transaction deposit(@RequestParam(value = "mount", defaultValue = "0") double mount) {

        Transaction transaction = new Transaction();
        transaction.setMount(mount);
        transaction.setType(TransactionType.valueOf("deposit"));
        transactionRepository.save(transaction);
        return transaction;
    }

    @PostMapping("/extraction")
    public Transaction extraction(@RequestParam(value = "mount", defaultValue = "0") double mount) {

        Transaction transaction = new Transaction();
        transaction.setMount(mount);
        transaction.setType(TransactionType.valueOf("extraction"));
        transactionRepository.save(transaction);
        return transaction;
    }

    @PostMapping("/interest")
    public Transaction interest(@RequestParam(value = "percentage", defaultValue = "0") double percentage) {
        List<Transaction> transactions = transactionRepository.findAll();

        double balance = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.valueOf("extraction")) {
                balance -= transaction.getMount();
            } else {
                balance += transaction.getMount();
            }
        }

        double mount = balance / 100 * percentage;

        Transaction transaction = new Transaction();
        transaction.setMount(mount);
        transaction.setType(TransactionType.valueOf("interest"));
        transactionRepository.save(transaction);
        return transaction;
    }


}
