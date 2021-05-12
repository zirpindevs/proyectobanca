package com.example.proyectobanca.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="nombre")
    private String nombre;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @ApiModelProperty("List of transactions that a category has: List<Transaction>")
    private List<Transaction> transactions = new ArrayList<>();

    public Category() {
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
