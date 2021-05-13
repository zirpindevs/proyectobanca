package com.example.proyectobanca.repository;

import com.example.proyectobanca.model.User;
import com.example.proyectobanca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNif(String nif);

    Boolean existsByNif(String nif);

    Boolean existsByEmail(String email);

}
