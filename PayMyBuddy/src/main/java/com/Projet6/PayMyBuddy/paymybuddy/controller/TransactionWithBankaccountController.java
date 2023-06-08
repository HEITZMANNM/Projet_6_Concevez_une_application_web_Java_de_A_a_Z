package com.Projet6.PayMyBuddy.paymybuddy.controller;

import com.Projet6.PayMyBuddy.paymybuddy.model.Transaction;
import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionBankaccount;
import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionBankaccountDTO;
import com.Projet6.PayMyBuddy.paymybuddy.model.View;
import com.Projet6.PayMyBuddy.paymybuddy.service.TransactionWithBankaccountService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class TransactionWithBankaccountController {

    @Autowired
    private TransactionWithBankaccountService transactionWithBankaccountService;


    @JsonView(View.TransactionBankaccountAmountDescriptionDateAndIbanOnly.class)
    @GetMapping("/transactionsWithBankByUserId")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<TransactionBankaccount> getTransactionsWithBankByUserId(@RequestParam(name = "userId") int userId)
    {
        return transactionWithBankaccountService.getTransactionsWithBankByUserId(userId);
    }

    @PostMapping("/transactionBankaccount")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<HttpStatus> postNewTransactionBankaccount(@RequestBody TransactionBankaccountDTO transactionBankaccountDTO)
    {

        if(transactionWithBankaccountService.saveNewTransactionBankAccount(transactionBankaccountDTO))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}
