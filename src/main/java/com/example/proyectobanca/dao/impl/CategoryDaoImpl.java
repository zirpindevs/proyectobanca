package com.example.proyectobanca.dao.impl;

import com.example.proyectobanca.dao.CategoryDao;
import com.example.proyectobanca.model.Category;
import com.example.proyectobanca.model.User;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Session session;

    private final Logger log = LoggerFactory.getLogger(CategoryDaoImpl.class);

    /**
     * Get all categories with optionals filters options (String name, String pag, String limit)
     * Dao Method
     * @param map1 map with all filters options (String name, String pag, String limit)
     * @return List of categories from database
     */
    @Override
    public List<Category> findAllByFilters(Map<String, String> map1) {

        try {

            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
            Root<Category> root = criteria.from(Category.class);

            List<Predicate> predicates = buildPredicatesFilter(map1, builder, root);

            criteria.distinct(true).select(root).where(builder.and(predicates.toArray(new Predicate[0])));

            TypedQuery<Category> usersQuery = manager.createQuery(criteria);

            if(map1.get("page")!=null && map1.get("limit")!=null){
                usersQuery.setFirstResult(Integer.parseInt(map1.get("page")));
                usersQuery.setMaxResults(Integer.parseInt(map1.get("limit")));
            }

            return usersQuery.getResultList();

        }catch (Exception e){

            log.error(e.getMessage());
            List<Category> categoriesError = new ArrayList<>();
            Category categoryError = new Category();
            categoryError.setId(-500L);
            categoriesError.add(categoryError);

            return categoriesError;
        }
    }

    /**
     * Build the predicates needed to create the filter search sql query
     * @param map1 Filters of
     * @param builder
     * @param root
     * @return predicates to sql search
     */
    private List<Predicate> buildPredicatesFilter(Map<String, String> map1,CriteriaBuilder builder, Root<Category> root){

        List<Predicate> predicates = new ArrayList<>();

        if (map1.get("name") != null)
            predicates.add(builder.like(root.get("name"), map1.get("name") + "%"));


        return predicates;
    }
}
