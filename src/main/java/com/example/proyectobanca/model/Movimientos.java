package com.example.proyectobanca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Movimientos {

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

    public Movimientos() {
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
