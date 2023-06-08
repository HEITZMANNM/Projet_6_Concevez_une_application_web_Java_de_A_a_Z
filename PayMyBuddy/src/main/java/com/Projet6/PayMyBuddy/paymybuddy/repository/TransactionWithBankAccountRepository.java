package com.Projet6.PayMyBuddy.paymybuddy.repository;

import com.Projet6.PayMyBuddy.paymybuddy.model.Transaction;
import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionBankaccount;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TransactionWithBankAccountRepository extends CrudRepository<TransactionBankaccount, Integer> {

    public Iterable<TransactionBankaccount> findAll();

    public Optional<TransactionBankaccount> findById(int id);

    public Iterable<TransactionBankaccount> findByDate(Date date);


    public Iterable<TransactionBankaccount> findByUser(User user);

    public Iterable<TransactionBankaccount> findByUserId(int userId);
}
