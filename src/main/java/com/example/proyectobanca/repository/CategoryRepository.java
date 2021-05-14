package com.example.proyectobanca.repository;

import com.example.proyectobanca.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findOneByName(String name);
}
