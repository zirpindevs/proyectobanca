package com.example.proyectobanca.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionDTO {


    @ApiModelProperty("Transaction amount: Double")
    private Double importe;

    @ApiModelProperty("Transaction description: String")
    private String concepto;

    @ApiModelProperty("Type of transaction: String")
    private String tipoMovimiento;

    @ApiModelProperty("Created date: Instant")
    private Instant createdDate;

    @ApiModelProperty("Transaction update date: Instant")
    private Instant lastModified;

    @ApiModelProperty("Primary key of transaction own of bankaccount: Long")
    private Long idBankAccount;

    @ApiModelProperty("Primary key of transaction own of credit card: Long")
    private Long idCreditCard;

    @ApiModelProperty("Primary key of transaction own of category: Long")
    private Long idCategory;

    public TransactionDTO() {
    }

    public TransactionDTO(Double importe, String concepto, String tipoMovimiento, Instant createdDate, Instant lastModified, Long idBankAccount, Long idCreditCard, Long idCategory) {
        this.importe = importe;
        this.concepto = concepto;
        this.tipoMovimiento = tipoMovimiento;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
        this.idBankAccount = idBankAccount;
        this.idCreditCard = idCreditCard;
        this.idCategory = idCategory;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModified() { return lastModified; }

    public void setLastModified(Instant lastModified) { this.lastModified = lastModified; }

    public Long getIdBankAccount() {
        return idBankAccount;
    }

    public void setIdBankAccount(Long idBankAccount) {
        this.idBankAccount = idBankAccount;
    }

    public Long getIdCreditCard() {
        return idCreditCard;
    }

    public void setIdCreditCard(Long idCreditCard) {
        this.idCreditCard = idCreditCard;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }
}
