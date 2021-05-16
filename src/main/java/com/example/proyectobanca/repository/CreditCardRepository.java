package com.example.proyectobanca.repository;

import com.example.proyectobanca.model.BankAccount;
import com.example.proyectobanca.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    boolean existsById(Long id);

    Boolean existsByNumCreditCard(String numCreditCard);

}
