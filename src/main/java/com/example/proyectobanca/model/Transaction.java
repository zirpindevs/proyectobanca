package com.example.proyectobanca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Primary key: Long")
    private Long id;

    @ApiModelProperty("Transaction amount: Long")
    private Long importe;

    @ApiModelProperty("Transaction description: String")
    private String concepto;

    @Column(name="tipo_movimiento")
    @ApiModelProperty("Type of transaction: String")
    private String tipoMovimiento;

    @Column(name="created_date")
    @ApiModelProperty("Created date: Instant")
    private Instant createdDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bank_account")
    @JsonIgnore
    @ApiModelProperty("Bank account to which a single transaction belongs: BankAccount")
    private BankAccount bankAccount;

    @ManyToOne()
    @JoinColumn(name = "id_credit_card")
    @JsonIgnore
    @ApiModelProperty("Credit card to which a single transaction belongs: CreditCard")
    private CreditCard creditCard;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category")
    @JsonIgnore
    @ApiModelProperty("Category to which a single transaction belongs: Category")
    private Category category;

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImporte() {
        return importe;
    }

    public void setImporte(Long importe) {
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

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", importe=" + importe +
                ", concepto='" + concepto + '\'' +
                ", tipoMovimiento='" + tipoMovimiento + '\'' +
                ", createdDate=" + createdDate +
                ", bankAccount=" + bankAccount +
                ", creditCard=" + creditCard +
                ", category=" + category +
                '}';
    }
}
