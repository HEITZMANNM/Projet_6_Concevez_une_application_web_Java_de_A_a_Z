package com.Projet6.PayMyBuddy.paymybuddy;

import com.Projet6.PayMyBuddy.paymybuddy.model.BankAccount;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.service.BankAccountService;
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


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BankAccountController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private UserService userService;

    private BankAccount bankAccount;

    private User userTest;
    @BeforeEach
    public void setUp()
    {
        userTest = new User();
        userTest.setEmail("userTest@gmail.ts");
        userTest.setFirstName("userTestControllerTest");
        userTest.setLastName("userTestControllerTest");
        userTest.setPassword("userTestTest");
        userTest.setBalance(100);

        bankAccount = new BankAccount();
        bankAccount.setBic("55");
        bankAccount.setIban("FRtest");
        bankAccount.setUser(userTest);
        bankAccount.setStatus("actif");

        userService.saveUser(userTest);
        bankAccountService.saveBankAccount(bankAccount);
    }

    @AfterEach
    public void clearDataBase()
    {
        bankAccountService.deleteByIban(bankAccount.getIban());
        userService.deleteUserByFirstNameAndLastName(userTest.getFirstName(), userTest.getLastName());
    }

    //test to get all user's bankaccounts, by its id
    @Test
    public void testToGetAllBankAccountByUserId() throws Exception {
        int userId = userService.getUserByEmailAndPassword(userTest.getEmail(), userTest.getPassword()).getId();
        this.mockMvc.perform(get("/bankaccountByUserInfo").param("userId", String.valueOf(userId)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].iban").value("FRtest"));
    }

    //test to save a new bankaccount
    @Test
    public void testToSaveANewBankAccount() throws Exception {
        BankAccount newBankaccount = new BankAccount();
        newBankaccount.setBic("00");
        newBankaccount.setIban("FRnewtest");
        newBankaccount.setStatus("actif");

        int userId = userService.getUserByEmailAndPassword(userTest.getEmail(), userTest.getPassword()).getId();


        mockMvc.perform( MockMvcRequestBuilders
                        .post("/bankaccount")
                        .content(asJsonString(newBankaccount))
                        .param("userId", String.valueOf(userId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        bankAccountService.deleteByIban(newBankaccount.getIban());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
