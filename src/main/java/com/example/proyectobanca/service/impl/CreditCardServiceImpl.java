package com.example.proyectobanca.service.impl;

import com.example.proyectobanca.service.CreditCardService;
import com.example.proyectobanca.dao.CreditCardDAO;
import com.example.proyectobanca.model.CreditCard;
import com.example.proyectobanca.repository.CreditCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    private final Logger log = LoggerFactory.getLogger(CreditCardServiceImpl.class);

    private final CreditCardRepository creditCardRepository;
    private final CreditCardDAO creditCardDAO;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, CreditCardDAO creditCardDAO) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardDAO = creditCardDAO;
    }


    @Override
    public CreditCard createCreditCard(CreditCard creditCard) {
        log.debug("Create creditCard: {}", creditCard);

        CreditCard creditCardCreated = null;

            try{
                creditCard.setCreatedDate(Instant.now());
                creditCard.setLastModified(Instant.now());
                creditCardCreated = creditCardRepository.save(creditCard);
            }catch(Exception e) {
                log.error("Cannot save the creditcard: {} , error : {}", creditCard, e);
            }

        return creditCardCreated;
    }



    @Override
    public CreditCard updateCreditCard(CreditCard modifiedCreditCard) {

        log.debug("Update a CreditCard: {}", modifiedCreditCard);

        CreditCard updatedCreditCard = null;
        CreditCard findedCreditCard = creditCardDAO.findById(modifiedCreditCard.getId());

        if (findedCreditCard != null) {

            try{
                modifiedCreditCard.setLastModified(Instant.now());
                updatedCreditCard = creditCardRepository.save(modifiedCreditCard);
            }catch(Exception e){
                log.error("Cannot save CreditCard: {} , error : {}", modifiedCreditCard, e);
            }
        }else{
            log.warn("Cannot save CreditCard: {}, because it doesn´t exist", updatedCreditCard);
        }
        return updatedCreditCard;
    }


    @Override
    public List<CreditCard> findAll() {
        log.info("REST request to find all CreditCards");

        return this.creditCardRepository.findAll();
    }

    @Override
    public CreditCard findOne(Long id) {
        log.info("REST request to find one CreditCard by id");

        if(id == null)
            return null;
        return this.creditCardDAO.findById(id);
    }


    @Override
    public void deleteCreditCard(CreditCard creditCardToDelete){
        log.info("REST request to delete an CreditCard by id");
        this.creditCardRepository.deleteById(creditCardToDelete.getId());

    }
}

