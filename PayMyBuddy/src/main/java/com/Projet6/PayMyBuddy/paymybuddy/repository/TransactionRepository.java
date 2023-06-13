package com.Projet6.PayMyBuddy.paymybuddy.repository;

import com.Projet6.PayMyBuddy.paymybuddy.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    public Iterable<Transaction> findAll();


    public Iterable<Transaction> findByUserSenderId(int userSenderId);

    public Iterable<Transaction> findByUserReceiverId(int userReceiverId);


}
