package com.example.proyectobanca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="num_credit_card")
    private Long numCreditCard;

    private String placeholder;

    private String type;

    @Column(name="card_provider")
    private String cardProvider;

    private String cvv;

    private String pin;

    @Column(name="expiration_date")
    private Date expirationDate;

    private Boolean enabled;

    @Column(name="created_date")
    private Instant createdDate;

    @Column(name="last_modified")
    private Instant lastModified;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    @JsonIgnore
    @ApiModelProperty("User to which a single credid card belongs: User")
    private User user;

    public CreditCard() {
    }

    public Long getNumCreditCard() {
        return numCreditCard;
    }

    public void setNumCreditCard(Long numCreditCard) {
        this.numCreditCard = numCreditCard;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardProvider() {
        return cardProvider;
    }

    public void setCardProvider(String cardProvider) {
        this.cardProvider = cardProvider;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "id=" + id +
                ", numeroTarjeta=" + numCreditCard +
                ", placeholder='" + placeholder + '\'' +
                ", tipo='" + type + '\'' +
                ", proveedor='" + cardProvider + '\'' +
                ", CVV='" + cvv + '\'' +
                ", pin='" + pin + '\'' +
                ", fechaExpiracion=" + expirationDate +
                ", enabled=" + enabled +
                ", createdDate=" + createdDate +
                ", lastModified=" + lastModified +
                '}';
    }
}
