//package com.Projet6.PayMyBuddy.paymybuddy.integration;
//
//
//import com.Projet6.PayMyBuddy.paymybuddy.model.BankAccount;
//import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionBankaccount;
//import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionBankaccountDTO;
//import com.Projet6.PayMyBuddy.paymybuddy.model.User;
//import com.Projet6.PayMyBuddy.paymybuddy.repository.TransactionWithBankAccountRepository;
//import com.Projet6.PayMyBuddy.paymybuddy.repository.UserRepository;
//import com.Projet6.PayMyBuddy.paymybuddy.service.BankAccountService;
//import com.Projet6.PayMyBuddy.paymybuddy.service.TransactionWithBankaccountService;
//import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.List;
//
//@SpringBootTest
//public class TransactionWithBankaccountServiceIT {
//
//    @Autowired
//    private TransactionWithBankaccountService transactionWithBankaccountService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private BankAccountService bankAccountService;
//
//    @Autowired
//    private TransactionWithBankAccountRepository transactionWithBankAccountRepository;
//
//    private User newUser;
//
//    private BankAccount bankAccountTest;
//
//    private TransactionBankaccountDTO transactionBankaccountDTO;
//
//    @BeforeEach
//    public void setUp()
//    {
//        newUser = new User();
//        newUser.setBalance(100);
//        newUser.setPassword("newUser");
//        newUser.setEmail("newuser@gmail.user");
//        newUser.setFirstName("Bernard");
//        newUser.setLastName("Dupont");
//
//        bankAccountTest = new BankAccount();
//        bankAccountTest.setUser(newUser);
//        bankAccountTest.setStatus("actif");
//        bankAccountTest.setBic("55");
//        bankAccountTest.setIban("FRTTTTTTTTTTTTTTTT");
//        bankAccountTest.setId(1);
//
//        transactionBankaccountDTO = new TransactionBankaccountDTO();
//        transactionBankaccountDTO.setBankaccount(bankAccountTest);
//        transactionBankaccountDTO.setAmount(10);
//        transactionBankaccountDTO.setDescription("test convertion transactionBankaccountDTO to transactionBankaccount");
//        transactionBankaccountDTO.setOrigin(true);
//
//userService.saveUser(newUser);
//bankAccountService.saveBankAccount(bankAccountTest);
//    }
//
//    @AfterEach
//    public void clearDataBase()
//    {
//        User usertested = userService.getUserByEmail("newuser@gmail.user");
//       transactionWithBankaccountService.deleteTransactionWithBankaccountByUserId(usertested.getId());
//
//       bankAccountService.deleteByIbanForTest(bankAccountTest.getIban());
//
//        userService.deleteUserById(usertested.getId());
//    }
//
//    //test to save a new transaction between a user and a bankaccount
//    @Test
//    public void testToSavedANewTransactionWithBankaccount()
//    {
//        transactionWithBankaccountService.saveNewTransactionBankAccount(transactionBankaccountDTO);
//
//        List<TransactionBankaccount> transactionListExpected = transactionWithBankaccountService.getTransactionsWithBankByUserId(newUser.getId());
//
//        assertEquals(transactionListExpected.size(), 1);
//        assertEquals(transactionListExpected.get(0).getDescription(), "test convertion transactionBankaccountDTO to transactionBankaccount");
//    }
//
//}
