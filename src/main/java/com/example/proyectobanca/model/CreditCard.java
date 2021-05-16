package com.example.proyectobanca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "credit_cards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ApiModelProperty("User to which a single credit card belongs: User")
    private User user;

    @OneToMany(mappedBy = "creditCard", cascade = CascadeType.ALL)
    @ApiModelProperty("List of transactions that a credit card has: List<Transaction>")
    private List<Transaction> transactions;

    public CreditCard() {
    }

    public Long getId() {
        return id;
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

  /*  public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }*/

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", numCreditCard=" + numCreditCard +
                ", placeholder='" + placeholder + '\'' +
                ", type='" + type + '\'' +
                ", cardProvider='" + cardProvider + '\'' +
                ", cvv='" + cvv + '\'' +
                ", pin='" + pin + '\'' +
                ", expirationDate=" + expirationDate +
                ", enabled=" + enabled +
                ", createdDate=" + createdDate +
                ", lastModified=" + lastModified +
                ", user=" + user +
                '}';
    }
}
