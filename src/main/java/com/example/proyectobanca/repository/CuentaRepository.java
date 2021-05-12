package com.example.proyectobanca.repository;

import com.example.proyectobanca.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<BankAccount, Long> {
}
