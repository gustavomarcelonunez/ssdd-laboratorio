package com.example.lab1.backend.model;

import javax.persistence.*;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_gen")
    @SequenceGenerator(name = "account_gen", sequenceName = "account_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private double saldo = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double extraccion(double saldo) {
        this.saldo -= saldo;
        return this.saldo;
    }

    public double depositar(double saldo) {
        this.saldo += saldo;
        return this.saldo;
    }

    public double interes(int interes) {
        this.saldo = this.saldo + (this.saldo / 100 * interes);
        return this.saldo;
    }

    public BankAccount() {  }
}
