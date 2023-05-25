package com.Projet6.PayMyBuddy.paymybuddy.controller;

import com.Projet6.PayMyBuddy.paymybuddy.model.BankAccount;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.service.BankAccountService;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class BankAccountController {

    @Autowired
    public BankAccountService bankAccountService;

    @Autowired
    public UserService userService;

    @GetMapping("/bankaccounts")
    public List<BankAccount> getALlBankAccount() throws IOException
    {
        return bankAccountService.getBankAccounts();
    }

    @GetMapping("/bankaccountById")
    public BankAccount getBankAccountById(@RequestParam(name = "id") int id)
    {
        return bankAccountService.getBankAccountById(id);
    }

    @GetMapping("/bankaccountByIban")
    public BankAccount getBankAccountByIban(@RequestParam(name = "iban") String iban)
    {
        return bankAccountService.getBankAccountByIban(iban);
    }

//    @GetMapping("/bankaccountByUser")
//    public BankAccount getBankAccountByUser(@RequestBody User user)
//    {
//        return bankAccountService.getBankAccountByUser(user);
//    }

    @GetMapping("/bankaccountByUserInfo")
    public List<BankAccount> getBankAccountByUserFirstNameAndLastName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName)
    {
        return bankAccountService.getBankAccountByUserFirstNameAndLastName(firstName, lastName);
    }


//    @PostMapping("/bankaccount")
//    public ResponseEntity<HttpStatus> postNewBankAccount(@RequestBody BankAccount bankAccount)
//    {
//        if(bankAccountService.saveBankAccount(bankAccount))
//        {
//            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
//        }
//        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
//    }

    @PostMapping("/bankaccount")
    public ResponseEntity<HttpStatus> postNewBankAccount(@RequestParam(name = "userId") int userId, @RequestBody BankAccount bankAccount)
    {
        User user = userService.getUserById(userId);
        bankAccount.setUser(user);

        if(bankAccountService.saveBankAccount(bankAccount))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/bankaccount")
    public void deleteBankAccount(@RequestParam(name = "iban") String iban)
    {
        bankAccountService.deleteByIban(iban);
    }

    @PutMapping("/bankaccount")
    public ResponseEntity<HttpStatus> upDateBankAccount(@RequestBody BankAccount bankAccount, @RequestParam(name = "id") int id)
    {
        if(bankAccountService.upDateBankAccount(bankAccount, id))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

}
