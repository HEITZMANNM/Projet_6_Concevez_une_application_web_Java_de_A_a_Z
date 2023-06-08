package com.Projet6.PayMyBuddy.paymybuddy.service;

import com.Projet6.PayMyBuddy.paymybuddy.model.BankAccount;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.repository.BankAccountRepository;
import com.Projet6.PayMyBuddy.paymybuddy.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




@Service
public class BankAccountService {

    private static final Logger logger = LogManager.getLogger("BankAccountService");
    @Autowired
    BankAccountRepository bankAccountRepository;

    public List<BankAccount> getBankAccounts() {
        List<BankAccount> bankAccounts = new ArrayList<>();
        try {
            Iterable<BankAccount> iterableBankAccounts = bankAccountRepository.findAll();
            for(BankAccount bankAccount : iterableBankAccounts)
            {
                bankAccounts.add(bankAccount);
            }
            logger.debug("All Bank Account were found");
        } catch (Exception ex) {
            logger.error("Error fetching the list of Bank account", ex);
        }
        return bankAccounts;
    }

    public BankAccount getBankAccountById(int id)
    {
        BankAccount bankAccount = new BankAccount();
        try
        {
            Optional<BankAccount> bankAccountSearch = bankAccountRepository.findById(id);
            bankAccount= bankAccountSearch.get();
            logger.debug("The bank account was find");
        }
        catch(Exception ex){
            logger.error("Error fetching bank account", ex);
        }
        return bankAccount;

    }
    public BankAccount getBankAccountByIban(String iban)
    {
        BankAccount bankAccount = new BankAccount();
        try
        {
            Optional<BankAccount> bankAccountSearch = bankAccountRepository.findByIban(iban);
            bankAccount= bankAccountSearch.get();
            logger.debug("The bank account was find");
        }
        catch(Exception ex){
            logger.error("Error fetching bank account", ex);
        }
        return bankAccount;
    }

    public BankAccount getBankAccountByUser(User user)
    {
        BankAccount bankAccount = new BankAccount();
        try
        {
            Optional<BankAccount> bankAccountSearch = bankAccountRepository.findByUser(user);
            bankAccount= bankAccountSearch.get();
            logger.debug("The bank account was find");
        }
        catch(Exception ex){
            logger.error("Error fetching bank account", ex);
        }
        return bankAccount;
    }

    public List<BankAccount> getBankAccountByUserId(int userId)
    {
        List<BankAccount> listOfBankAccount = new ArrayList<>();
        try
        {
            Iterable<BankAccount> bankAccountSearch = bankAccountRepository.findByUserId(userId);
            for(BankAccount bankAccount : bankAccountSearch)
            {
                listOfBankAccount.add(bankAccount);
            }

            logger.debug("The bank account was find");
        }
        catch(Exception ex){
            logger.error("Error fetching bank account", ex);
        }
        return listOfBankAccount;
    }

    public boolean saveBankAccount(BankAccount bankAccount)
    {
        boolean answer = false;
        try
        {
            bankAccountRepository.save(bankAccount);
            answer = true;
            logger.debug("the bank account was saved");
        }
        catch(Exception ex){
            logger.error("Error save bank account", ex);
        }
        return answer;
    }

    public void deleteById(Integer id)
    {
        bankAccountRepository.deleteById(id);
    }

    public void deleteByIban(String iban)
    {
        bankAccountRepository.deleteByIban(iban);
    }

    public boolean upDateBankAccount(BankAccount bankAccount, int id)
    {
        Optional<BankAccount> optionalExistingBankAccount = bankAccountRepository.findById(id);
        BankAccount existingBankAccount = optionalExistingBankAccount.get();
        boolean answer = false;
        try
        {
            existingBankAccount.setIban(bankAccount.getIban());
            existingBankAccount.setBic(bankAccount.getBic());

            bankAccountRepository.save(existingBankAccount);
            answer = true;
            logger.debug("the bank account was saved");
        }
        catch(Exception ex){
            logger.error("Error save bank account", ex);
        }
        return answer;
    }
}
