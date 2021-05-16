package com.example.proyectobanca.service.impl;

import com.example.proyectobanca.model.*;
import com.example.proyectobanca.repository.UserRepository;
import com.example.proyectobanca.service.CreditCardService;
import com.example.proyectobanca.dao.CreditCardDAO;
import com.example.proyectobanca.repository.CreditCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    private final Logger log = LoggerFactory.getLogger(CreditCardServiceImpl.class);

    private final CreditCardRepository creditCardRepository;
    private final CreditCardDAO creditCardDAO;
    private final UserRepository userRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, CreditCardDAO creditCardDAO, UserRepository userRepository) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardDAO = creditCardDAO;
        this.userRepository = userRepository;
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
    public CreditCard createCreditCard(CreditCardDTO creditCardDTO) {


        try {

            CreditCard creditCardValidated = createValidateCreditCard(creditCardDTO);

            if (creditCardValidated.getNumCreditCard() == null)
                return new CreditCard();;

            return creditCardRepository.save(creditCardValidated);

        }catch (Exception e){

            log.error(e.getMessage());
            e.printStackTrace();
            CreditCard creditCardError = new CreditCard();
            creditCardError.setId(-500L);

            return creditCardError;
        }
    }


    @Override
    public CreditCard updateCreditCard(CreditCard modifiedCreditCard) {

       /* log.debug("Update a CreditCard: {}", modifiedCreditCard);

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
        return updatedCreditCard;*/
        return null;
    }

    @Override
    public void deleteCreditCard(CreditCard creditCardToDelete){
        log.info("REST request to delete an CreditCard by id");
        this.creditCardRepository.deleteById(creditCardToDelete.getId());

    }

    /**
     * Validate a credit card before to save in db
     * @param creditCardDTO
     * @return CreditCard
     */
    private CreditCard createValidateCreditCard (CreditCardDTO creditCardDTO){

        CreditCard creditCardEmpty = new CreditCard();

        if ( creditCardDTO.getNumCreditCard() == null || creditCardDTO.getPlaceholder() == null || creditCardDTO.getCvv() == null || creditCardDTO.getPin() == null || creditCardDTO.getExpirationDate() == null ){

            return creditCardEmpty;

        }else {

            if(creditCardDTO.getCvv().length() != 3 || creditCardDTO.getPin().length() != 4)
                return creditCardEmpty;

            Boolean numCreditCardExist = creditCardRepository.existsByNumCreditCard(creditCardDTO.getNumCreditCard());
            if (numCreditCardExist)
                return creditCardEmpty;

            if ( creditCardDTO.getIdUser() != null && userRepository.existsById(creditCardDTO.getIdUser()) == false)
                return creditCardEmpty;

            // If passed all validations

            if ( creditCardDTO.getEnabled() == null)
                creditCardDTO.setEnabled(false);

            if ( creditCardDTO.getType() == null)
                creditCardDTO.setType(CreditCardType.debito);

            CreditCard creditCard = new CreditCard();

            creditCard.setNumCreditCard(creditCardDTO.getNumCreditCard());
            creditCard.setPlaceholder(creditCardDTO.getPlaceholder());
            creditCard.setType(creditCardDTO.getType());
            creditCard.setCardProvider(creditCardDTO.getCardProvider());
            creditCard.setCvv(creditCardDTO.getCvv());
            creditCard.setPin(creditCardDTO.getPin());
            creditCard.setExpirationDate(creditCardDTO.getExpirationDate());
            creditCard.setEnabled(creditCardDTO.getEnabled());
            creditCard.setCreatedAt(LocalDateTime.now());
            if (creditCardDTO.getIdUser() != null) {
                Optional<User> user = userRepository.findOneById(creditCardDTO.getIdUser());
                creditCard.setUser(user.get());
            }

            return creditCard;
        }


    }
}

