package com.Projet6.PayMyBuddy.paymybuddy;

import com.Projet6.PayMyBuddy.paymybuddy.model.Transaction;
import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionDTO;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.service.TransactionService;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
        userSender.setEmail("userSender@gmail.ts");
        userSender.setFirstName("userSenderControllerTest");
        userSender.setLastName("userSenderControllerTest");
        userSender.setPassword("userSenderTest");
        userSender.setBalance(100);


        userReceiver = new User();
        userReceiver.setEmail("userReceiver@gmail.ts");
        userReceiver.setPassword("userReceiverTest");
        userReceiver.setBalance(500);
        userReceiver.setLastName("userReceiverControllerTest");
        userReceiver.setFirstName("userReceiverControllerTest");

        List<User> friends = new ArrayList<>();
        friends.add(userReceiver);


        userSender.setFriends(friends);

        userService.saveUser(userReceiver);
        userService.saveUser(userSender);

        transaction = new Transaction();
        transaction.setDescription("test transaction");
        transaction.setAmount(10);
        transaction.setUserSender(userSender);
        transaction.setUserReceiver(userReceiver);

        transactionService.saveNewTransaction(transaction);
    }

    @AfterEach
    public void clearDataBase()
    {
        int userId = userService.getUserByEmailAndPassword(userSender.getEmail(), userSender.getPassword()).getId();
        int friendId = userService.getUserByEmailAndPassword(userReceiver.getEmail(), userReceiver.getPassword()).getId();
        Transaction transactionTestToDelete = transactionService.getTransactionByUserId(userId).get(0);

        transactionService.deleteTransactionForTest(transactionTestToDelete);
        userService.deleteAFriend(userId, friendId);
        userService.deleteUserByFirstNameAndLastName(userSender.getFirstName(), userSender.getLastName());
        userService.deleteUserByFirstNameAndLastName(userReceiver.getFirstName(), userReceiver.getLastName());

    }

    @Test
    public void testToGetTransactionByUserId() throws Exception {
        int userId = userService.getUserByEmailAndPassword(userSender.getEmail(), userSender.getPassword()).getId();
        this.mockMvc.perform(get("/transactionByUserId").param("userId", String.valueOf(userId)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description").value("test transaction"));
    }

    @Test
    public void testToSaveANewTransaction() throws Exception {
        TransactionDTO transaction = new TransactionDTO();
        transaction.setDescription("test to save a new transaction");
        transaction.setAmount(5);

        int userSenderId= userService.getUserByEmailAndPassword(userSender.getEmail(), userSender.getPassword()).getId();
        int userReceiverId = userService.getUserByEmailAndPassword(userReceiver.getEmail(), userReceiver.getPassword()).getId();

        transaction.setUserSenderId(userSenderId);
        transaction.setUserReceiverId(userReceiverId);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/transaction")
                        .content(asJsonString(transaction))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Transaction transactionTestToDelete = transactionService.getTransactionByUserId(userSenderId).get(1);

        transactionService.deleteTransactionForTest(transactionTestToDelete);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
