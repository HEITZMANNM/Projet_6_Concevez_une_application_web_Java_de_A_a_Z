package com.Projet6.PayMyBuddy.paymybuddy;

import com.Projet6.PayMyBuddy.paymybuddy.model.Transaction;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.repository.TransactionRepository;
import com.Projet6.PayMyBuddy.paymybuddy.repository.UserRepository;
import com.Projet6.PayMyBuddy.paymybuddy.service.TransactionService;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock(lenient = true)
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService = new TransactionService();

    private List<Transaction> listOfTransactionByUserSenderId;

    private List<Transaction> listOfTransactionByUserReceiverId;


    private User userSender;

    private User userReceiver;

    private Transaction transaction;

    @BeforeEach
    public void setUp()
    {
        userSender = new User();
        userSender.setBalance(100);
        userSender.setPassword("newUser");
        userSender.setEmail("newuser@gmail.user");
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
        transaction.setAmount(10);
        transaction.setDescription("test");

        listOfTransactionByUserReceiverId = new ArrayList<>();
        listOfTransactionByUserReceiverId.add(transaction);

        listOfTransactionByUserSenderId = new ArrayList<>();

        when(transactionRepository.findByUserReceiverId(anyInt())).thenReturn(listOfTransactionByUserReceiverId);
        when(transactionRepository.findByUserSenderId(anyInt())).thenReturn(listOfTransactionByUserSenderId);
    }

    //test to get all transaction by user id
    @Test
    public void testToGetTransactionByUserId()
    {
        List<Transaction> listOfExpectedTransaction = transactionService.getTransactionByUserId(1);

        assertEquals(listOfExpectedTransaction.get(0).getDescription(), "test");
    }


    @Test
    public void testToConvertADoubleToADoubleWithOnlyTwoDecimals()
    {
        double doubleToConvert = 20.2222222;

        double doubleConverted = transactionService.convertDoubleWithTwoDecimal(doubleToConvert);

        assertEquals(doubleConverted, 20.22);
    }



}
