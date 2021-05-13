package com.example.proyectobanca.dao.impl;

import com.example.proyectobanca.dao.UserDao;
import com.example.proyectobanca.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager manager;

    private final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public List<User> findAllByFilters(Map<String, String> map1) {

        try {

            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);

            List<Predicate> predicates = buildPredicatesFilter(map1, builder, root);

            criteria.distinct(true).select(root).where(builder.and(predicates.toArray(new Predicate[0])));

            TypedQuery<User> usersQuery = manager.createQuery(criteria);

            if(map1.get("page")!=null && map1.get("limit")!=null){
                usersQuery.setFirstResult(Integer.parseInt(map1.get("page")));
                usersQuery.setMaxResults(Integer.parseInt(map1.get("limit")));
            }

            return usersQuery.getResultList();

        }catch (Exception e){

            log.error(e.getMessage());
            List<User> usersError = new ArrayList<>();
            User userError = new User();
            userError.setId(-500L);
            usersError.add(userError);

            return usersError;
        }
    }

    /**
     * Build the predicates needed to create the filter search sql query
     * @param map1 Filters of
     * @param builder
     * @param root
     * @return predicates to sql search
     */
    private List<Predicate> buildPredicatesFilter(Map<String, String> map1,CriteriaBuilder builder, Root<User> root){

        List<Predicate> predicates = new ArrayList<>();

        if (map1.get("name") != null)
            predicates.add(builder.like(root.get("name"), map1.get("name") + "%"));


        return predicates;
    }
}
