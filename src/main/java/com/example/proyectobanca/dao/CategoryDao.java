package com.example.proyectobanca.dao;

import com.example.proyectobanca.model.Category;

import java.util.List;
import java.util.Map;

public interface CategoryDao {

    List<Category> findAllByFilters(Map<String, String> map1);

}
