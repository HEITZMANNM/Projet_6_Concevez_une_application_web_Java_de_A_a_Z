package com.Projet6.PayMyBuddy.paymybuddy.service;

import com.Projet6.PayMyBuddy.paymybuddy.model.*;
import com.Projet6.PayMyBuddy.paymybuddy.repository.TransactionWithBankAccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

@Service
public class TransactionWithBankaccountService {

    @Autowired
    TransactionWithBankAccountRepository transactionWithBankAccountRepository;

    @Autowired
    private UserService userService;

    private static final Logger logger = LogManager.getLogger("TransactionWithBankaccountService");


    public List<TransactionBankaccount> getTransactionsWithBankByUserId(int userId)
    {
        List<TransactionBankaccount> listOfTransactionsByUserId = new ArrayList<>();
        try {
            Iterable<TransactionBankaccount> transactions = transactionWithBankAccountRepository.findByUserId(userId);

            for (TransactionBankaccount transaction : transactions) {
                listOfTransactionsByUserId.add(transaction);
                logger.debug("The transactions were find");
            }
        }
        catch(Exception ex){
            logger.error("Error fetching the list of transactions", ex);
        }
        return listOfTransactionsByUserId;
    }

    public boolean saveNewTransactionBankAccount(TransactionBankaccountDTO transaction)
    {
        boolean answer = false;

        try
        {
            TransactionBankaccount transactionBankaccount = convertTransactionBankaccountDTOToTransactionBankAccount(transaction);
            User userSender = transactionBankaccount.getUser();

            double amount = transaction.getAmount();
            boolean origin = transaction.getOrigin();

            if (userSender.getBalance()>(amount) && origin == true) {

                transactionWithBankAccountRepository.save(transactionBankaccount);
                double userSenderBalance = userSender.getBalance() - amount;
                userSender.setBalance(userSenderBalance);
                userService.saveUser(userSender);

                answer = true;
                logger.debug("The transaction was saved");
            }
            else if (userSender.getBalance()>(amount) && origin == false) {

                transactionWithBankAccountRepository.save(transactionBankaccount);
                double userSenderBalance = userSender.getBalance() + amount;
                userSender.setBalance(userSenderBalance);
                userService.saveUser(userSender);

                answer = true;
                logger.debug("The transaction was saved");

            } else {
                logger.error("Error saving the transaction, the sender don't have enough money ");
            }
        }
        catch(Exception ex){
            logger.error("Error saving the transaction", ex);
        }
        return answer;
    }

    public TransactionBankaccount convertTransactionBankaccountDTOToTransactionBankAccount(TransactionBankaccountDTO transactionBankaccountDTO)
    {
        User userSender = transactionBankaccountDTO.getBankaccount().getUser();
        double amount = transactionBankaccountDTO.getAmount();
        Date date = new Date();
        String description = transactionBankaccountDTO.getDescription();
        BankAccount bankAccount = transactionBankaccountDTO.getBankaccount();
        boolean origin = transactionBankaccountDTO.getOrigin();

        TransactionBankaccount transactionBankaccount = new TransactionBankaccount();
        transactionBankaccount.setAmount(amount);
        transactionBankaccount.setBankaccount(bankAccount);
        transactionBankaccount.setDescription(description);
        transactionBankaccount.setUser(userSender);
        transactionBankaccount.setDate(date);
        transactionBankaccount.setOrigin(origin);

        return transactionBankaccount;
    }

    //methode for controller test only
    public void deleteTransactionWithBankaccountByUserId(int userId)
    {
        List<TransactionBankaccount> listOfTransactionBankaccountByUserId = this.getTransactionsWithBankByUserId(userId);

        listOfTransactionBankaccountByUserId.removeAll(listOfTransactionBankaccountByUserId);
    }
}
