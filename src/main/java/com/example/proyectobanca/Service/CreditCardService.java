package com.example.proyectobanca.Service;

import com.example.proyectobanca.model.CreditCard;

import java.util.List;

public interface CreditCardService {
    CreditCard createCreditCard(CreditCard creditCard);
    CreditCard updateCreditCard(CreditCard modifiedCreditCard);
    void deleteCreditCard(CreditCard creditCardtToDelete);

    CreditCard findOne(Long id);
    List<CreditCard> findAll();

}
