package com.Projet6.PayMyBuddy.paymybuddy.controller;

import com.Projet6.PayMyBuddy.paymybuddy.model.BankAccount;
import com.Projet6.PayMyBuddy.paymybuddy.model.Transaction;
import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionDTO;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.service.TransactionDTOService;
import com.Projet6.PayMyBuddy.paymybuddy.service.TransactionService;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() throws IOException
    {
        return transactionService.getTransactions();
    }

    @GetMapping("/transactionsByDate")
    public List<Transaction> getTransactionByDate(@RequestParam(name = "date") String date) throws ParseException {
        Date dateWrite = new SimpleDateFormat("%Y%m%d %H%i%s%f").parse(date);
        return transactionService.getTransactionByDate(dateWrite);
    }

    @GetMapping("/transactionByUserSender")
    public List<Transaction> getTransactionByUserSenderFirstNameAndLastName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName)
    {
        return transactionService.getTransactionByUserSender(firstName, lastName);
    }

    @GetMapping("/transactionByUserReceiver")
    public List<Transaction> getTransactionByUserReceiverFirstNameAndLastName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName)
    {
        return transactionService.getTransactionByUserReceiver(firstName, lastName);
    }

//    @PostMapping("/transaction")
//    public ResponseEntity<HttpStatus> postNewUser(@RequestBody Transaction transaction, @RequestParam(name = "userSenderId") int userSenderId, @RequestParam(name = "userReceiverId") int userReceiverId)
//    {
//        User userSender = userService.getUserById(userSenderId);
//        User userReceiver = userService.getUserById(userReceiverId);
//
//        transaction.setUserReceiver(userReceiver);
//        transaction.setUserSender(userSender);
//
//        if(transactionService.saveNewTransaction(transaction))
//        {
//            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
//        }
//        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
//    }

    @PostMapping("/transaction")
    public ResponseEntity<HttpStatus> postNewUser(@RequestBody TransactionDTO transactionDTO)
    {
       Transaction transaction =  transactionDTOService.convertTransactionDTOToTransaction(transactionDTO);

        if(transactionService.saveNewTransaction(transaction))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}
