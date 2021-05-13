package com.example.proyectobanca.service.impl;

import com.example.proyectobanca.dao.UserDao;
import com.example.proyectobanca.model.User;
import com.example.proyectobanca.repository.UserRepository;
import com.example.proyectobanca.service.UserService;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Session session;

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository repository;

    private final UserDao userDao;

    public UserServiceImpl(UserRepository repository, UserDao userDao) {
        this.repository = repository;
        this.userDao = userDao;
    }

    /**
     * Get all users with optionals filters options (String name, String pag, String limit)
     * Service Method
     * @param map1 map with all filters options (String name, String pag, String limit)
     * @return List of users from database
     */
    @Override
    public List<User> findAll(Map<String, String> map1) {

        try {

            return this.userDao.findAllByFilters(map1);

        }catch (Exception e){

            log.error(e.getMessage());
            e.printStackTrace();
            List<User> usersError = new ArrayList<>();
            User userError = new User();
            userError.setId(-500L);
            usersError.add(userError);

            return usersError;
        }
    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

    @Override
    public Optional<Boolean> deleteOne(Long id) {
        return Optional.empty();
    }
}
