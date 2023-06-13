package com.Projet6.PayMyBuddy.paymybuddy;

import com.Projet6.PayMyBuddy.paymybuddy.model.BankAccount;
import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionBankaccount;
import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionBankaccountDTO;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.repository.TransactionWithBankAccountRepository;
import com.Projet6.PayMyBuddy.paymybuddy.service.TransactionWithBankaccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransactionWithBankaccountServiceTest {


    @Mock(lenient = true)
    TransactionWithBankAccountRepository transactionWithBankAccountRepository;

    @InjectMocks
    TransactionWithBankaccountService transactionWithBankaccountService = new TransactionWithBankaccountService();

    private List<TransactionBankaccount> listOfTransactionsByUserId;

    private User userTest;

    private BankAccount bankAccountTest;

    private TransactionBankaccountDTO transactionBankaccountDTO;



    @BeforeEach
    public void setUp()
    {
        listOfTransactionsByUserId = new ArrayList<>();

        userTest = new User();
        userTest.setId(1);
        userTest.setBalance(100);
        userTest.setPassword("newUser");
        userTest.setEmail("newuser@gmail.user");
        userTest.setFirstName("Bernard");
        userTest.setLastName("Dupont");

        bankAccountTest = new BankAccount();
        bankAccountTest.setUser(userTest);
        bankAccountTest.setStatus("actif");
        bankAccountTest.setBic("55");
        bankAccountTest.setIban("FRTTTTTTTTTTTTTTTT");
        bankAccountTest.setId(1);

        TransactionBankaccount transactionBankaccount = new TransactionBankaccount();
        transactionBankaccount.setDate(new Date());
        transactionBankaccount.setUser(userTest);
        transactionBankaccount.setBankaccount(bankAccountTest);
        transactionBankaccount.setDescription("test");
        transactionBankaccount.setAmount(100);
        transactionBankaccount.setId(1);
        transactionBankaccount.setOrigin(true);

        listOfTransactionsByUserId.add(transactionBankaccount);

        transactionBankaccountDTO = new TransactionBankaccountDTO();
        transactionBankaccountDTO.setBankaccount(bankAccountTest);
        transactionBankaccountDTO.setAmount(1000);
        transactionBankaccountDTO.setDescription("test convertion transactionBankaccountDTO to transactionBankaccount");
        transactionBankaccountDTO.setOrigin(true);

        when(transactionWithBankAccountRepository.findByUserId(1)).thenReturn(listOfTransactionsByUserId);

    }

    //test to get list of transactions with bankaccount by user id
    @Test
    public void testToGetTransactionWithBankaccountByUserId()
    {
        List<TransactionBankaccount> listExpected =  transactionWithBankaccountService.getTransactionsWithBankByUserId(1);

        assertEquals(listExpected.get(0).getDescription(), "test");
    }

    //test to convert a transactionBankaccountDTO to a transactionBankaccount
    @Test
    public void testToConvertTransactionBankaccountDTOToTransactionBankAccount()
    {
        TransactionBankaccount transactionConverted = transactionWithBankaccountService.convertTransactionBankaccountDTOToTransactionBankAccount(transactionBankaccountDTO);

        assertEquals(transactionConverted.getUser().getFirstName(), "Bernard");
        assertEquals(transactionConverted.getDescription(), "test convertion transactionBankaccountDTO to transactionBankaccount");
    }
}
