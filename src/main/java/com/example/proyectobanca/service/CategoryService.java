package com.example.proyectobanca.service;

import com.example.proyectobanca.model.Category;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll(Map<String, String> map1);

    Optional<Category> findOne(Long id);

    Category createOne(Category tag);

    Category updateOne(Long id, Category tag);

    Optional<Boolean> deleteOne(Long id);

    Long getTotalCount();
}
