package com.example.proyectobanca.security.service;

import com.example.proyectobanca.model.User;
import com.example.proyectobanca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Autentica un usuario de la base de datos
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nif) throws UsernameNotFoundException {
        User user = userRepository.findByNif(nif)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + nif));

        return new org.springframework.security.core.userdetails.User(
                user.getNif(),user.getPassword(),new ArrayList<>());
    }
}
