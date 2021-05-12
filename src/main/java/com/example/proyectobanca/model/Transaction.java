package com.example.proyectobanca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="importe")
    private Long importe;

    @Column(name="concepto")
    private String concepto;

    @Column(name="tipo_movimiento")
    private String tipoMovimiento;

    @Column(name="created_date")
    private Instant createdDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bank_account")
    @JsonIgnore
    @ApiModelProperty("Bank account to which a single transaction belongs: BankAccount")
    private BankAccount bankAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category")
    @JsonIgnore
    @ApiModelProperty("Category to which a single transaction belongs: Category")
    private Category category;

    public Transaction() {
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

    @Override
    public String toString() {
        return "Movimientos{" +
                "id=" + id +
                ", importe=" + importe +
                ", concepto='" + concepto + '\'' +
                ", tipoMovimiento='" + tipoMovimiento + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
