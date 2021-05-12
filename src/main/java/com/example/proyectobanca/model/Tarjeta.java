package com.example.proyectobanca.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
public class Tarjeta {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="numer_tarjeta")
    private Long numeroTarjeta;

    @Column(name="placeholder")
    private String placeholder;

    @Column(name="tipo")
    private String tipo;

    @Column(name="proveedor")
    private String proveedor;

    @Column(name="CVV")
    private String CVV;

    @Column(name="pin")
    private String pin;

    @Column(name="fecha_expiracion")
    private Date fechaExpiracion;

    @Column(name="enabled")
    private Boolean enabled;

    @Column(name="created_date")
    private Instant createdDate;

    @Column(name="last_modified")
    private Instant lastModified;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    private User user;

    public Tarjeta() {
    }

    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
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
                ", numeroTarjeta=" + numeroTarjeta +
                ", placeholder='" + placeholder + '\'' +
                ", tipo='" + tipo + '\'' +
                ", proveedor='" + proveedor + '\'' +
                ", CVV='" + CVV + '\'' +
                ", pin='" + pin + '\'' +
                ", fechaExpiracion=" + fechaExpiracion +
                ", enabled=" + enabled +
                ", createdDate=" + createdDate +
                ", lastModified=" + lastModified +
                '}';
    }
}
