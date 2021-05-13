package com.example.proyectobanca.dao.impl;

import com.example.proyectobanca.model.BankAccount;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class BankAccountDAOImpl {
    @PersistenceContext
    private EntityManager manager;

/*    @Override
    public BankAccount findById(Long id){

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<BankAccount> criteria =  builder.createQuery(BankAccount.class);
        Root<BankAccount> root = criteria.from(BankAccount.class);
        BankAccount bankAccount;

        criteria.select(root).where(builder.equal(root.get("id"), id));
        try{
            bankAccount = manager.createQuery(criteria).getSingleResult();
        }catch(Exception e){
            bankAccount = null;
        }

        return bankAccount;
    }*/
}
