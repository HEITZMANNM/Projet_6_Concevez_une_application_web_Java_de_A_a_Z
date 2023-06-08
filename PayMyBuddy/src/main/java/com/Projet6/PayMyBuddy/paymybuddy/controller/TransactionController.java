package com.Projet6.PayMyBuddy.paymybuddy.controller;

import com.Projet6.PayMyBuddy.paymybuddy.model.*;
import com.Projet6.PayMyBuddy.paymybuddy.service.TransactionDTOService;
import com.Projet6.PayMyBuddy.paymybuddy.service.TransactionService;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    public UserService userService;

    @Autowired
    public TransactionDTOService transactionDTOService;


    @JsonView(View.TransactionAmountDescriptionAndDateOnly.class)
    @GetMapping("/transactionByUserId")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Transaction> getTransactionByUserId(@RequestParam(name = "userId") int userId)
    {
        return transactionService.getTransactionByUserId(userId);
    }

    @PostMapping("/transaction")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<HttpStatus> postNewTransaction(@RequestBody TransactionDTO transactionDTO)
    {
       Transaction transaction =  transactionDTOService.convertTransactionDTOToTransaction(transactionDTO);

        if(transactionService.saveNewTransaction(transaction))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }


}
