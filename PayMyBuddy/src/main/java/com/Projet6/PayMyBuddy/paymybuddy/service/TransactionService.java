package com.Projet6.PayMyBuddy.paymybuddy.service;

import com.Projet6.PayMyBuddy.paymybuddy.model.Transaction;

import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.repository.TransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    private static final Logger logger = LogManager.getLogger("TransactionService");


    public List<Transaction> getTransactionByUserId(int userId)
    {
        List<Transaction> listOfTransactionByUser = new ArrayList<>();

        try
        {
            Iterable<Transaction> transactionsByUserReceiver = transactionRepository.findByUserReceiverId(userId);
            Iterable<Transaction> transactionsByUserSender = transactionRepository.findByUserSenderId(userId);

            for (Transaction transaction : transactionsByUserReceiver)
            {
                listOfTransactionByUser.add(transaction);
            }
            for (Transaction transaction : transactionsByUserSender)
            {
                listOfTransactionByUser.add(transaction);
            }

            logger.debug("The list of transactions by receiver was fetched");
        }
        catch(Exception ex){
            logger.error("Error fetching list of transactions by receiver", ex);
        }
        return listOfTransactionByUser;
    }



    public boolean saveNewTransaction(Transaction transaction)
    {
        boolean answer = false;

        try
        {
            transaction.setFees(transaction.getAmount()*0.005);
            transaction.setDateTransaction(new Date());


            double amount = convertDoubleWithTwoDecimal(transaction.getAmount());

            double fees = convertDoubleWithTwoDecimal(transaction.getFees());
            User userSender = transaction.getUserSender();
            User userReceiver = transaction.getUserReceiver();
            User paymybuddyOfficiel = userService.getUserById(28);
            double balancePayMyBuddy = paymybuddyOfficiel.getBalance();

            if (userSender.getBalance()>(amount+fees)) {
                transactionRepository.save(transaction);
                balancePayMyBuddy = balancePayMyBuddy + fees;
                paymybuddyOfficiel.setBalance(balancePayMyBuddy);
                double userSenderBalance = convertDoubleWithTwoDecimal(userSender.getBalance() - amount - fees);
                double userReceiverBalance = convertDoubleWithTwoDecimal(userReceiver.getBalance() + amount);

                userSender.setBalance(userSenderBalance);
                userReceiver.setBalance(userReceiverBalance);

                userService.saveUser(userSender);
                userService.saveUser(userReceiver);

                answer = true;
                logger.debug("The transaction was saved");
            }
            else {
                logger.error("Error saving the transaction, the sender don't have enough money ");
            }
        }
        catch(Exception ex){
            logger.error("Error saving the transaction", ex);
        }
        return answer;
    }

    public double convertDoubleWithTwoDecimal(double amount)
    {
        return Math.round(amount * 100.0) / 100.0;
    }


    //used only for integration test
    public void deleteTransactionForTest(Transaction transaction)
    {
        transactionRepository.delete(transaction);
    }

}
