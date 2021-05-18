package com.example.proyectobanca.controller;

import com.example.proyectobanca.model.CreditCardDTO;
import com.example.proyectobanca.service.CreditCardService;
import com.example.proyectobanca.model.CreditCard;
import com.example.proyectobanca.repository.CreditCardRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
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
     * Create a new credit card in database
     * @param creditCardDTO CreditCardDTO to create
     * @return creditCard CreditCard created
     * @throws URISyntaxException
     */
    @PostMapping("/creditcards")
    @ApiOperation("Create a new credit card in DB")
    public ResponseEntity<CreditCard> createCreditCard(@ApiParam("creditCard that you want to create: CreditCardDTO") @RequestBody CreditCardDTO creditCardDTO) throws URISyntaxException {

        if(ObjectUtils.isEmpty(creditCardDTO))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        CreditCard result = creditCardService.createCreditCard(creditCardDTO);

        if (result.getId() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (result.getId() == -500L)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity
                .created(new URI("/api/creditcards/" + result.getId()))
                .body(result);
    }

    /**
     * It update a credit card of database
     * @param creditCardDTO CreditCardDTO to update
     * @return CreditCard updated in database
     */
    @PutMapping("/creditcards/{id}")
    @ApiOperation("Update an existing creditCard in DB")
    public ResponseEntity<CreditCard> updateCreditCard(
            @ApiParam("id of CreditCard that you want to update: Long") @PathVariable Long id,
            @ApiParam("CreditCard that you want to update: CreditCardDTO") @RequestBody CreditCardDTO creditCardDTO
    ){

        if(id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        CreditCard result = creditCardService.updateCreditCard(id, creditCardDTO);

        if (result.getId() == -404L)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (result.getId() == -500L)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.ok().body(result);
    }



    /**
     * DELETE CREDIT CARD
     * @param id
     * @return
     */
    @DeleteMapping("/creditcards/{id}")
    @ApiOperation("Delete CreditCard of DB by Id")
    public ResponseEntity<CreditCard> deleteCreditCard(@ApiParam("Primary key of CreditCard: Long") @PathVariable Long id){
        log.debug("REST request to delete a creditcard: {} ", id);

        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        CreditCard result = creditCardService.markAsDeleteOne(id);

        if (Objects.equals(result, Optional.of(false)))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (result.getId() == -500L)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
