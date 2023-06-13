package com.Projet6.PayMyBuddy.paymybuddy;

import com.Projet6.PayMyBuddy.paymybuddy.model.BankAccount;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.repository.BankAccountRepository;
import com.Projet6.PayMyBuddy.paymybuddy.repository.UserRepository;
import com.Projet6.PayMyBuddy.paymybuddy.service.BankAccountService;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {


    @Mock(lenient = true)
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private BankAccountService bankAccountService = new BankAccountService();

    @BeforeEach
    public void setUp()
    {
        User user = new User();
        user.setId(1);
        user.setFirstName("Bob");
        user.setLastName("Sponge");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(0);
        bankAccount.setIban("FR000000000000");
        bankAccount.setBic("00");
        bankAccount.setUser(user);
        bankAccount.setStatus("actif");

        List<BankAccount> listOfBankAccountForUserTested = new ArrayList<>();
        listOfBankAccountForUserTested.add(bankAccount);



        when(bankAccountRepository.findByIban(bankAccount.getIban())).thenReturn(Optional.of(bankAccount));
        when(bankAccountRepository.findByUserId(anyInt())).thenReturn(listOfBankAccountForUserTested);
    }

    @Test
    public void testToGetBankAccountByIban()
    {
        BankAccount bankAccountExpected =  bankAccountService.getBankAccountByIban("FR000000000000");

        assertEquals(bankAccountExpected.getBic(), "00");
    }

    @Test
    public void testToGetBankAccountByUserId()
    {
        List<BankAccount> listBankAccountExpected =  bankAccountService.getBankAccountByUserId(1);

        assertEquals(listBankAccountExpected.get(0).getBic(), "00");
    }


}
