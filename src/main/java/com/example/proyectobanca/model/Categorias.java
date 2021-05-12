package com.example.proyectobanca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Categorias {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="nombre")
    private String nombre;

    public Categorias() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Categorias{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
