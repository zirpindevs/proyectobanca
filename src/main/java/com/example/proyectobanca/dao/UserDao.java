package com.example.proyectobanca.dao;

import com.example.proyectobanca.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao {

    List<User> findAllByFilters(Map<String, String> map1);

    List findAllBankAccountsByUser(Long idUser, Map<String, String> map1);

    public Optional<Boolean> deleteUsersBankAccountsRelation(Long idUser);

}
