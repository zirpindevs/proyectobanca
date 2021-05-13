package com.example.proyectobanca.service;

import com.example.proyectobanca.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    List<User> findAll(Map<String, String> map1);

    Optional<User> findOne(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    Optional<Boolean> deleteOne(Long id);
}
