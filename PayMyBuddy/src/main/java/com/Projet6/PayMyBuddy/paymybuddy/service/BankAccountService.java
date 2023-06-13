package com.Projet6.PayMyBuddy.paymybuddy.service;

import com.Projet6.PayMyBuddy.paymybuddy.model.BankAccount;
import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionBankaccount;

import com.Projet6.PayMyBuddy.paymybuddy.repository.BankAccountRepository;

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


    public List<BankAccount> getBankAccountByUserId(int userId)
    {
        List<BankAccount> listOfBankAccount = new ArrayList<>();
        try
        {
            Iterable<BankAccount> bankAccountSearch = bankAccountRepository.findByUserId(userId);
            for(BankAccount bankAccount : bankAccountSearch)
            {
                if(bankAccount.getStatus().equals("actif")) {
                    listOfBankAccount.add(bankAccount);
                }
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
        String ibanNewBankaccount =  bankAccount.getIban();
        BankAccount existingBankaccount = this.getBankAccountByIban(bankAccount.getIban());
        if(existingBankaccount.getId() == 0) {
            try {
                bankAccountRepository.save(bankAccount);
                answer = true;
                logger.debug("the bank account was saved");
            } catch (Exception ex) {
                logger.error("Error save bank account", ex);
            }
        }
        else{
            existingBankaccount.setStatus("actif");
            this.upDateBankAccount(existingBankaccount, existingBankaccount.getId());
            logger.debug("the bank account was update to actif");
            answer = true;
        }
        return answer;
    }

    public void deleteById(Integer id)
    {
        bankAccountRepository.deleteById(id);
    }

    public void deleteByIban(String iban)
    {
        BankAccount bankaccountToDelete = bankAccountRepository.findByIban(iban).get();
        List<TransactionBankaccount> listOfTransactionsWithBankaccount = bankaccountToDelete.getTransactionBankaccountList();
        if(listOfTransactionsWithBankaccount.isEmpty())
        {
            bankAccountRepository.deleteByIban(iban);
            logger.debug("the bank account was delete");
        }
        else {
            bankaccountToDelete.setStatus("non actif");
            this.upDateBankAccount(bankaccountToDelete, bankaccountToDelete.getId());
            logger.debug("the bank account was desactivated");
        }
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
            existingBankAccount.setStatus(bankAccount.getStatus());

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
