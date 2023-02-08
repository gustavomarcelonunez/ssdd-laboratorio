package com.example.lab1.backend.model;

import javax.persistence.*;

@Entity
public class Transaction implements Identificable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_gen")
    @SequenceGenerator(name = "account_gen", sequenceName = "account_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)

    private long id;
    private double mount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMount() {
        return mount;
    }

    public void setMount(double mount) {
        this.mount = mount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Transaction() {  }
}
