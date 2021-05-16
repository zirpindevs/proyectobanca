package com.example.proyectobanca.service;

import com.example.proyectobanca.model.CreditCard;
import com.example.proyectobanca.model.CreditCardDTO;

import java.util.List;

public interface CreditCardService {
    CreditCard createCreditCard(CreditCardDTO creditCardDTO);
    CreditCard updateCreditCard(CreditCard modifiedCreditCard);
    void deleteCreditCard(CreditCard creditCardtToDelete);

    CreditCard findOne(Long id);
    List<CreditCard> findAll();

}
