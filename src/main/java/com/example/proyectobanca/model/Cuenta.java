package com.example.proyectobanca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Cuenta {

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


    public Cuenta() {
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
