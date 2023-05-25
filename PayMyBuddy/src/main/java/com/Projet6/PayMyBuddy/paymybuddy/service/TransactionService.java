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
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    private static final Logger logger = LogManager.getLogger("TransactionService");

    public List<Transaction> getTransactions()
    {
        List<Transaction> listOfAllTransactions = new ArrayList<>();
        try {
            Iterable<Transaction> transactions = transactionRepository.findAll();

            for (Transaction transaction : transactions) {
                listOfAllTransactions.add(transaction);
                logger.debug("The transactions were find");
            }
        }
        catch(Exception ex){
            logger.error("Error fetching the list of transactions", ex);
        }
        return listOfAllTransactions;
    }

    public Transaction getTransactionById(int id)
    {
        Transaction transaction = new Transaction();
        try
        {
            Optional<Transaction> transactionSearch = transactionRepository.findById(id);
            transaction= transactionSearch.get();
            logger.debug("The transaction was find");
        }
        catch(Exception ex){
            logger.error("Error fetching transaction", ex);
        }
        return transaction;
    }

    public List<Transaction> getTransactionByDate(Date date)
    {
        List<Transaction> listOfTransactionByDate = new ArrayList<>();
        try
        {
            Iterable<Transaction> transactionsBydate = transactionRepository.findByDateTransaction(date);

            for (Transaction transaction : transactionsBydate)
            {
                listOfTransactionByDate.add(transaction);
            }
            logger.debug("The list of transactions by date was fetched");
        }
        catch(Exception ex){
            logger.error("Error fetching list of transactions by date", ex);
        }
        return listOfTransactionByDate;
    }

    public List<Transaction> getTransactionByUserSender(String firstName, String lastName)
    {
        List<Transaction> listOfTransactionByUserSender = new ArrayList<>();
        try
        {
            Iterable<Transaction> transactionsByUserSender = transactionRepository.findByUserSenderFirstNameAndUserSenderLastName(firstName, lastName);

            for (Transaction transaction : transactionsByUserSender)
            {
                listOfTransactionByUserSender.add(transaction);
            }
            logger.debug("The list of transactions by sender was fetched");
        }
        catch(Exception ex){
            logger.error("Error fetching list of transactions by sender", ex);
        }
        return listOfTransactionByUserSender;
    }

    public List<Transaction> getTransactionByUserReceiver(String firstName, String lastName)
    {
        List<Transaction> listOfTransactionByUserReceiver = new ArrayList<>();
        try
        {
            Iterable<Transaction> transactionsByUserReceiver = transactionRepository.findByUserReceiverFirstNameAndUserReceiverLastName(firstName, lastName);

            for (Transaction transaction : transactionsByUserReceiver)
            {
                listOfTransactionByUserReceiver.add(transaction);
            }
            logger.debug("The list of transactions by receiver was fetched");
        }
        catch(Exception ex){
            logger.error("Error fetching list of transactions by receiver", ex);
        }
        return listOfTransactionByUserReceiver;

    }

    public boolean saveNewTransaction(Transaction transaction)
    {
        boolean answer = false;
        try
        {
            transaction.setFees(transaction.getAmount()*0.5);
            transaction.setDateTransaction(new Date());
            transactionRepository.save(transaction);
            answer=true;
            logger.debug("The transaction was saved");
        }
        catch(Exception ex){
            logger.error("Error saving the transaction", ex);
        }
        return answer;
    }

}
