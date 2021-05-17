package com.example.proyectobanca.service;

import com.example.proyectobanca.model.CreditCard;
import com.example.proyectobanca.model.CreditCardDTO;

import java.util.List;
import java.util.Optional;

public interface CreditCardService {

    List<CreditCard> findAll();

    CreditCard findOne(Long id);

    CreditCard createCreditCard(CreditCardDTO creditCardDTO);

    CreditCard updateCreditCard(Long id, CreditCardDTO creditCardDTO);

    void deleteCreditCard(CreditCard creditCardtToDelete);

}
