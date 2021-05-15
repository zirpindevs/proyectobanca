package com.example.proyectobanca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "num_account", nullable = false, unique = true)
    private Long numAccount;

    @Column(name="balance")
    private Double balance;

    @Column(name="enabled", nullable = false, columnDefinition = "boolean default true")
    private Boolean enabled;

    @Column(name="created_date")
    private Instant createdDate;

    @Column(name="last_modified")
    private Instant lastModified;

    @ManyToMany(mappedBy = "bankAccounts")
    @JsonIgnore
    @ApiModelProperty("Lista de usuarios asociados a una cuenta: List<User>")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    @ApiModelProperty("List of transactions that a bank account has: List<Transaction>")
    private List<Transaction> transactions = new ArrayList<>();


    public BankAccount() {
    }

    public BankAccount(Long numAccount, Double balance, Boolean enabled, Instant createdDate, Instant lastModified) {
        this.numAccount = numAccount;
        this.balance = balance;
        this.enabled = enabled;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getNumAccount() {
        return numAccount;
    }

    public void setNumAccount(Long numAccount) {
        this.numAccount = numAccount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", numAccount=" + numAccount +
                ", balance=" + balance +
                ", enabled=" + enabled +
                ", createdDate=" + createdDate +
                ", lastModified=" + lastModified +
                '}';
    }
}
