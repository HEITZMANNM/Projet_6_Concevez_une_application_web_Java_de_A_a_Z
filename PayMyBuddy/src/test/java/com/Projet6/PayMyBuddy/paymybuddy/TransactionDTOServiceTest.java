package com.Projet6.PayMyBuddy.paymybuddy;

import com.Projet6.PayMyBuddy.paymybuddy.model.Transaction;
import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionDTO;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.service.TransactionDTOService;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionDTOServiceTest {


    @InjectMocks
    private TransactionDTOService transactionDTOService = new TransactionDTOService();

    @Mock
    private UserService userService;

    private User userOne;

    private User userTwo;

    @BeforeEach
    public void setUp()
    {
        userOne = new User();
        userOne.setEmail("Dutton@gmail.ye");
        userOne.setPassword("DD");
        userOne.setBalance(100);
        userOne.setId(1);

        userTwo = new User();
        userTwo.setEmail("Wolf@gmail.ge");
        userTwo.setPassword("WW");
        userTwo.setBalance(500);
        userTwo.setId(2);

        when(userService.getUserById(1)).thenReturn(userOne);
        when(userService.getUserById(2)).thenReturn(userTwo);
    }

    @Test
    public void testToConvertTransactionDTOToTransaction()
    {
        TransactionDTO transactionDTOToConvert = new TransactionDTO();
        transactionDTOToConvert.setAmount(10);
        transactionDTOToConvert.setDescription("test to convert transactionDTO to transaction");
        transactionDTOToConvert.setUserSenderId(1);
        transactionDTOToConvert.setUserReceiverId(2);

        Transaction transactionConverted = transactionDTOService.convertTransactionDTOToTransaction(transactionDTOToConvert);

        assertEquals(transactionConverted.getDescription(),"test to convert transactionDTO to transaction");
        assertNotNull(transactionConverted.getUserSender());
    }
}
