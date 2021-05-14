package com.example.proyectobanca.service.impl;

import com.example.proyectobanca.dao.CategoryDao;
import com.example.proyectobanca.model.Category;
import com.example.proyectobanca.model.User;
import com.example.proyectobanca.repository.CategoryRepository;
import com.example.proyectobanca.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Session session;

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final CategoryRepository repository;

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryRepository repository, CategoryDao categoryDao) {
        this.repository = repository;
        this.categoryDao = categoryDao;
    }


    /**
     * Get all categories with optionals filters options (String name, String pag, String limit)
     * Service Method
     * @param map1 map with all filters options (String name, String pag, String limit)
     * @return List of categories from database
     */
    @Override
    public List<Category> findAll(Map<String, String> map1) {

        try {

            return this.categoryDao.findAllByFilters(map1);

        }catch (Exception e){

            log.error(e.getMessage());
            e.printStackTrace();
            List<Category> categoriesError = new ArrayList<>();
            Category categoryError = new Category();
            categoryError.setId(-500L);
            categoriesError.add(categoryError);

            return categoriesError;
        }
    }

    /**
     * Get category by ID - Service
     * @param id Primary key of Category: Long
     * @return Optional<Category> from database
     */
    @Override
    public Optional<Category> findOne(Long id){
        try {

            if(id == null)
                return Optional.empty();

            Optional<Category> result = this.repository.findById(id);

            return result;

        }catch (Exception e){

            log.error(e.getMessage());
            Category categoryError = new Category();
            categoryError.setId(-500L);

            return Optional.of(categoryError);
        }
    }

    @Override
    public Category createOne(Category tag) {
        return null;
    }

    @Override
    public Category updateOne(Long id, Category tag) {
        return null;
    }

    @Override
    public Optional<Boolean> deleteOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Long getTotalCount() {
        return null;
    }
}
