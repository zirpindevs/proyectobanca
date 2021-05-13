package com.example.proyectobanca.repository;

import com.example.proyectobanca.model.BankAccount;
import com.example.proyectobanca.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findBynumeroCuenta(Long numBankAcoount);

}
