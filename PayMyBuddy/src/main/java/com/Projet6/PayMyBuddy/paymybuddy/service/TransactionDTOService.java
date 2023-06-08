package com.Projet6.PayMyBuddy.paymybuddy.service;

import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionDTO;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.repository.TransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Projet6.PayMyBuddy.paymybuddy.model.Transaction;

import java.util.Date;

@Service
public class TransactionDTOService {

    @Autowired
    UserService userService;

    private static final Logger logger = LogManager.getLogger("TransactionDTOService");

    public Transaction convertTransactionDTOToTransaction(TransactionDTO transactionDTO)
    {

        Transaction transaction = new Transaction();

        try {
            transaction.setDescription(transactionDTO.getDescription());
            transaction.setAmount(transactionDTO.getAmount());

            User userSender = userService.getUserById(transactionDTO.getUserSenderId());
            User userReceiver = userService.getUserById(transactionDTO.getUserReceiverId());

            transaction.setUserSender(userSender);
            transaction.setUserReceiver(userReceiver);

        }
        catch (Exception ex)
        {
            logger.error("Error convert TransactionDTO to Transaction", ex);
        }

        return transaction;
    }

    public TransactionDTO convertTransactionToTransactionDTO (Transaction transaction)
    {

        TransactionDTO transactionDTO = new TransactionDTO();

        try {

            transactionDTO.setDescription(transaction.getDescription());
            transactionDTO.setAmount(transaction.getAmount());
            transactionDTO.setUserReceiverId(transaction.getUserReceiver().getId());
            transactionDTO.setUserSenderId(transaction.getUserSender().getId());

        }
        catch (Exception ex)
        {
            logger.error("Error convert TransactionDTO to Transaction", ex);
        }

        return transactionDTO;
    }


}
