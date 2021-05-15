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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="num_account")
    private Long numAccount;

    @Column(name="balance")
    @ApiModelProperty("Account balance field: Double")
    private Double balance;

    @Column(name="enabled", nullable = false, columnDefinition = "boolean default true")
    @ApiModelProperty("Account enabled status: Boolean")
    private Boolean enabled;

    @Column(name="created_date")
    @ApiModelProperty("Account  created_date: Instant")
    private Instant createdDate;

    @Column(name="last_modified")
    @ApiModelProperty("Account last_modified: Instant")
    private Instant lastModified;

    @ManyToMany(mappedBy = "bankAccounts")
    @JsonIgnore
    @ApiModelProperty("List of users that a bank account has: List<User>")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", numeroCuenta=" + numAccount +
                ", balance=" + balance +
                ", enabled=" + enabled +
                ", createdDate=" + createdDate +
                ", lastModified=" + lastModified +
                '}';
    }
}
