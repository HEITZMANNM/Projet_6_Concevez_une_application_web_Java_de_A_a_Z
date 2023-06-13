package com.Projet6.PayMyBuddy.paymybuddy.integration;

import com.Projet6.PayMyBuddy.paymybuddy.model.Transaction;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;

import com.Projet6.PayMyBuddy.paymybuddy.service.TransactionService;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;


@SpringBootTest
public class TransactionServiceIT {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    private User userSender;

    private User userReceiver;

    private Transaction transaction;

    @BeforeEach
    public void setUp()
    {
        userSender = new User();
        userSender.setBalance(100);
        userSender.setPassword("UserSender");
        userSender.setEmail("userSender@gmail.user");
        userSender.setFirstName("Bernard");
        userSender.setLastName("Dupont");

        userReceiver = new User();
        userReceiver.setBalance(0);
        userReceiver.setPassword("UserReceiver");
        userReceiver.setEmail("userReceiver@gmail.user");
        userReceiver.setFirstName("Bob");
        userReceiver.setLastName("Sponge");

        transaction = new Transaction();
        transaction.setUserReceiver(userReceiver);
        transaction.setUserSender(userSender);
        transaction.setAmount(0);
        transaction.setDescription("test transactionService IT");

        userService.saveUser(userSender);
        userService.saveUser(userReceiver);

    }

    @AfterEach
    public void clearDataBase()
    {
        transactionService.deleteTransactionForTest(transaction);
        userService.deleteUserByFirstNameAndLastName(userSender.getFirstName(), userSender.getLastName());
        userService.deleteUserByFirstNameAndLastName(userReceiver.getFirstName(), userReceiver.getLastName());

    }

    //test to save a transaction
    @Test
    public void testToSaveATransaction()
    {
        transactionService.saveNewTransaction(transaction);

        int userSenderId = userSender.getId();

        Transaction transactionExpectedSaved = transactionService.getTransactionByUserId(userSenderId).get(0);

        assertEquals(transactionExpectedSaved.getDescription(), "test transactionService IT");
    }

}
