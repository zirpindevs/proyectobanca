package com.example.proyectobanca.controller;

import com.example.proyectobanca.service.CreditCardService;
import com.example.proyectobanca.model.CreditCard;
import com.example.proyectobanca.repository.CreditCardRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class CreditCardController {



    private final Logger log = LoggerFactory.getLogger(com.example.proyectobanca.controller.CreditCardController.class);

    private final CreditCardService creditCardService;

    private final CreditCardRepository creditCardRepository;

    public CreditCardController(CreditCardService creditCardService, CreditCardRepository creditCardRepository) {
        this.creditCardService = creditCardService;
        this.creditCardRepository = creditCardRepository;
    }


    /**
     * FIND ALL CREDITCARDS
     * @return List<CreditCard>
     */
    @RequestMapping(method = RequestMethod.GET, value = "/creditcards")
    @ApiOperation("Get all CreditCards")
    public List<CreditCard> findAllCreditCards(){
        log.debug("REST request to find all creditcards");

        return this.creditCardRepository.findAll();
    }

    /**
     * FIND CREDITCARD BY ID
     *
     * @param id
     * @return ResponseEntity<CreditCard>
     * @throws URISyntaxException
     */
    @GetMapping("/creditcards/{id}")
    @ApiOperation("Get CreditCards by Id")
    public ResponseEntity<CreditCard> findCreditCardById(@ApiParam("Primary key of creditcard: Long")@PathVariable Long id) throws URISyntaxException {
        CreditCard findCreditCard = this.creditCardService.findOne(id);

        if (findCreditCard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(findCreditCard);

    }

    /**
     * CREATE CREDITCARD
     *
     * @return ResponseEntity<CreditCard>
     * @throws URISyntaxException
     */
    @PostMapping("/creditcards")
    @ApiOperation("Create a new CreditCard in DB")
    public ResponseEntity<CreditCard> createCreditCard(@ApiParam("CreditCard that you want to create: CreditCard") @RequestBody CreditCard creditCardtoCreate) throws URISyntaxException {
        log.debug("REST request to create new a creditcard: {} ", creditCardtoCreate);

        if (creditCardtoCreate.getPlaceholder() == null || creditCardtoCreate.getNumCreditCard() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        CreditCard checkCreditCard = this.creditCardRepository.findBynumCreditCard(creditCardtoCreate.getNumCreditCard());


        if(checkCreditCard == null) {
            CreditCard createCreditCard = this.creditCardService.createCreditCard(creditCardtoCreate);

            return ResponseEntity
                    .created(new URI("/api/etiquetas/" + createCreditCard.getNumCreditCard()))
                    .body(createCreditCard);
        }
        else
        {
            log.warn("already in use");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * UPDATE CREDITCARD
     *
     * @param modifiedCreditCard
     * @return ResponseEntity<CreditCard>
     */
    @PutMapping("/creditcards")
    @ApiOperation("Update an existing CreditCard in DB")
    public ResponseEntity<CreditCard> updateCreditCard(@ApiParam("CreditCard that you want to update: CreditCard")@RequestBody CreditCard modifiedCreditCard) {
        log.debug("REST request to update one CreditCard: {} ", modifiedCreditCard);


        if (modifiedCreditCard.getId() == null) {
            log.warn("update creditcard without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CreditCard updateCreditCard = this.creditCardService.updateCreditCard(modifiedCreditCard);

        if(updateCreditCard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(updateCreditCard);

    }



    /**
     * DELETE CREDIT CARD
     * @param id
     * @return
     */
    @DeleteMapping("/creditcards/{id}")
    @ApiOperation("Delete CreditCard of DB by Id")
    public ResponseEntity<Void> deleteCreditCard(@ApiParam("Primary key of CreditCard: Long") @PathVariable Long id){
        log.debug("REST request to delete a creditcard: {} ", id);

        CreditCard creditCardtToDelete = this.creditCardService.findOne(id);

        if (creditCardtToDelete.getId() == null) {
            log.warn("creditcard not exists");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.creditCardService.deleteCreditCard(creditCardtToDelete);
        return ResponseEntity.noContent().build();
    }

}
