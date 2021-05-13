package com.example.proyectobanca.dao;

import com.example.proyectobanca.model.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    List<User> findAllByFilters(Map<String, String> map1);
}
