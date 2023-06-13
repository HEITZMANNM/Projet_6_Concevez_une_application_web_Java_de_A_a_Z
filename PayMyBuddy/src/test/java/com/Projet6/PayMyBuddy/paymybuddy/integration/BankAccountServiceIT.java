package com.Projet6.PayMyBuddy.paymybuddy.integration;

import com.Projet6.PayMyBuddy.paymybuddy.model.BankAccount;
import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionBankaccount;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.repository.BankAccountRepository;
import com.Projet6.PayMyBuddy.paymybuddy.service.BankAccountService;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class BankAccountServiceIT {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private UserService userService;

    private User user;

    private User userForUpDate;

    private BankAccount bankAccount;

    @BeforeEach
    public void setUp()
    {
        user = new User();
        user.setPassword("userTest");
        user.setEmail("userTest@test.ts");
        user.setFirstName("Bob");
        user.setLastName("Sponge");

        userService.saveUser(user);

        bankAccount = new BankAccount();
        bankAccount.setIban("FR000000000000");
        bankAccount.setBic("00");
        bankAccount.setUser(user);
        bankAccount.setStatus("actif");

        userForUpDate = new User();
        userForUpDate.setPassword("userTestUpDate");
        userForUpDate.setEmail("userTestUpdate@test.ts");
        userForUpDate.setFirstName("userTestToUpDate");
        userForUpDate.setLastName("userTestToUpdate");

        userService.saveUser(userForUpDate);

        List<TransactionBankaccount> transactionBankaccountList = new ArrayList<>();
        bankAccount.setTransactionBankaccountList(transactionBankaccountList);

    }

    @AfterEach
    public void cleanDataBae()
    {

        userService.deleteUserByFirstNameAndLastName(user.getFirstName(), user.getLastName());
        userService.deleteUserByFirstNameAndLastName(userForUpDate.getFirstName(), userForUpDate.getLastName());
    }

    @Test
    public void testToSavedABankAccount()
    {
        bankAccountService.saveBankAccount(bankAccount);

        List<BankAccount> bankAccountSaved = bankAccountService.getBankAccountByUserId(user.getId());

        assertEquals(bankAccountSaved.get(0).getBic(), "00");

        bankAccountService.deleteByIban("FR000000000000");

    }

    @Test
    public void testToDeleteBankAccount()
    {
        User user = new User();
        user.setPassword("userTestDelete");
        user.setEmail("userTestDelete@test.ts");
        user.setFirstName("userTestToDelete");
        user.setLastName("userTestToDelete");

        userService.saveUser(user);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setIban("bankaccountToDelete");
        bankAccount.setBic("Delete");
        bankAccount.setUser(user);
        bankAccount.setStatus("actif");

        bankAccountService.saveBankAccount(bankAccount);

        bankAccountService.deleteByIban("bankaccountToDelete");
        userService.deleteUserByFirstNameAndLastName("userTestToDelete", "userTestToDelete");

        BankAccount bankAccountSearch = bankAccountService.getBankAccountByIban("bankaccountToDelete");

        assertNull(bankAccountSearch.getIban());
    }

    @Test
    public void testToUpDateBankAccount()
    {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setIban("bankaccountToUpDate");
        bankAccount.setBic("UP");
        bankAccount.setUser(userForUpDate);
        bankAccount.setStatus("actif");

        bankAccountService.saveBankAccount(bankAccount);

        BankAccount bankAccountToUpDate = bankAccountService.getBankAccountByIban("bankaccountToUpDate");
        bankAccountToUpDate.setBic("55");

        bankAccountService.upDateBankAccount(bankAccountToUpDate, bankAccountToUpDate.getId());

        BankAccount bankAccountUpdated = bankAccountService.getBankAccountByIban("bankaccountToUpDate");

        assertEquals(bankAccountUpdated.getBic(), "55");

        bankAccountService.deleteByIban("bankaccountToUpDate");



    }


}
