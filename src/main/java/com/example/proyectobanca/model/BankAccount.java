package com.example.proyectobanca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BankAccount {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="numer_cuenta")
    private Long numeroCuenta;

    @Column(name="saldo")
    private Long saldo;

    @Column(name="enabled")
    private Boolean enabled;

    @Column(name="created_date")
    private Instant createdDate;

    @Column(name="last_modified")
    private Instant lastModified;

    @ManyToMany(mappedBy = "cuentas")
    @JsonIgnore
    @ApiModelProperty("Lista de usuarios asociados a una cuenta: List<User>")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    @ApiModelProperty("List of transactions that a bank account has: List<Transaction>")
    private List<Transaction> transactions = new ArrayList<>();


    public BankAccount() {
    }

    public BankAccount(Long numeroCuenta, Long saldo, Boolean enabled, Instant createdDate, Instant lastModified) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.enabled = enabled;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
    }

    public Long getNumeroTarjeta() {
        return numeroCuenta;
    }

    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroCuenta = numeroTarjeta;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
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

    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", numeroTarjeta=" + numeroCuenta +
                ", saldo=" + saldo +
                ", enabled=" + enabled +
                ", createdDate=" + createdDate +
                ", lastModified=" + lastModified +
                '}';
    }
}
