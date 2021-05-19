package com.example.proyectobanca.repository;

import com.example.proyectobanca.model.BankAccount;
import com.example.proyectobanca.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findBynumAccount(Long numBankAcoount);
    Optional<BankAccount> findOneById(Long id);

    boolean existsById(Long id);


}
