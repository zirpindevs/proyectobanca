package com.example.proyectobanca.Service.impl;

import com.example.proyectobanca.Service.CreditCardService;
import com.example.proyectobanca.dao.CreditCardDAO;
import com.example.proyectobanca.model.CreditCard;
import com.example.proyectobanca.repository.CreditCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CreditCardImpl implements CreditCardService {
    private final Logger log = LoggerFactory.getLogger(CreditCardImpl.class);

    private final CreditCardRepository creditCardRepository;
    private final CreditCardDAO creditCardDAO;

    public CreditCardImpl(CreditCardRepository creditCardRepository, CreditCardDAO creditCardDAO) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardDAO = creditCardDAO;
    }


    @Override
    public CreditCard createCreditCard(CreditCard creditCard) {
        log.debug("Create creditCard: {}", creditCard);

        CreditCard creditCardCreated = null;

        if (creditCard.getId() == null) {
            try{
                creditCard.setCreatedDate(Instant.now());
                creditCard.setLastModified(Instant.now());
              //***********  creditCardCreated = creditCardRepository.save(creditCard);
            }catch(Exception e) {
                log.error("Cannot save the creditcard: {} , error : {}", creditCard, e);
            }
        }else{
            log.warn("Creating creditcard with id");
        }
        return creditCardCreated;
    }



    @Override
    public CreditCard updateCreditCard(CreditCard modifiedCreditCard) {

        log.debug("Update a expert: {}", modifiedCreditCard);

/*        CreditCard updatedCreditCard = null;
        CreditCard findedCreditCard = expertDAO.findById(modifiedExpert.getId());

        if (findedExpert != null) {
            if(findedExpert.getTags() != null)
                existingTags = findedExpert.getTags();

            try{
                modifiedExpert.setLast_updated(Instant.now());
                modifiedExpert.setTags(existingTags);
                updatedExpert = expertRepository.save(modifiedExpert);
            }catch(Exception e){
                log.error("Cannot save expert: {} , error : {}", modifiedExpert, e);
            }
        }else{
            log.warn("Cannot save expert: {}, because it doesn´t exist", updatedExpert);
        }
        return updatedExpert;*/
        return null;
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

