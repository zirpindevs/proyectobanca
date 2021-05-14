package com.example.proyectobanca.service.impl;

import com.example.proyectobanca.dao.UserDao;
import com.example.proyectobanca.model.User;
import com.example.proyectobanca.model.UserStatus;
import com.example.proyectobanca.repository.UserRepository;
import com.example.proyectobanca.service.UserService;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
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

    /**
     * Get user by ID - Service
     * @param id Primary key of User: Long
     * @return Optional<User> from database
     */
    @Override
    public Optional<User> findOne(Long id) {
        try {

            if(id == null)
                return Optional.empty();

            Optional<User> userDb = this.repository.findById(id);

            return userDb;

        }catch (Exception e){

            log.error(e.getMessage());
            User userError = new User();
            userError.setId(-500L);

            return Optional.of(userError);
        }
    }

    /**
     * Create a new user in database - Service
     * @param user User to create
     * @return user created in database
     */
    @Override
    public User createUser(User user) {

        try {

            Optional<User> userExist = repository.findOneByNif(user.getNif());

            if (!ObjectUtils.isEmpty(userExist))
                return new User();

            Boolean userEmailExist = repository.existsByEmail(user.getEmail());

            if (userEmailExist)
                return new User();


            user.setCreatedAt(LocalDateTime.now());
            user.setStatus(UserStatus.pendiente);
            user.setEnabled(false);

            return repository.save(user);

        }catch (Exception e){

            log.error(e.getMessage());
            User userError = new User();
            userError.setId(-500L);

            return userError;
        }
    }

    /**
     * It update a user of database - Service
     * @param user to update
     * @return user updated in database
     */
    @Override
    public User updateUser(Long id, User user) {

        try {

            Optional<User> beforeUser = repository.findById(id);

            if (!beforeUser.isPresent()){
                User userError = new User();
                userError.setId(-404L);
                return userError;
            }

            if(user.getEnabled()==null)
                user.setEnabled(beforeUser.get().getEnabled());

            if(user.getStatus()==null)
                user.setStatus(beforeUser.get().getStatus());

            user.setId(id);
            user.setCreatedAt(beforeUser.get().getCreatedAt());
            user.setUpdatedAt(LocalDateTime.now());
            return repository.save(user);

        }catch (Exception e){

            log.error(e.getMessage());
            User userError = new User();
            userError.setId(-500L);
            return userError;

        }
    }

    /**
     * Delete user of database by ID - Service
     * @param id user primary key that you want to delete
     * @return Optional<Boolean>
     */
    @Override
    public Optional<Boolean> deleteOne(Long id){

        try {
            if (id != null && repository.existsById(id)) {

                // TODO - ELIMINAR LA RELACIÓN CON CUENTAS BANCARIAS
               // Optional<Boolean> result = userDao.deleteUsersBankAccountsRelation(id);

           /*     if (Objects.equals(result, Optional.of(true))){
                    repository.deleteById(id);
                }*/

                repository.deleteById(id);

                return Optional.of(true);
            }
            return Optional.of(false);
        }catch (Exception e){
            log.error(e.getMessage());
            return Optional.empty();
        }

    }
}
