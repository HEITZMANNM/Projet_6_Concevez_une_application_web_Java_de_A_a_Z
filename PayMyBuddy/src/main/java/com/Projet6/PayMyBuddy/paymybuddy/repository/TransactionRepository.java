package com.Projet6.PayMyBuddy.paymybuddy.repository;

import com.Projet6.PayMyBuddy.paymybuddy.model.Transaction;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    public Iterable<Transaction> findAll();

    public Optional<Transaction> findById(int id);

    public Iterable<Transaction> findByDateTransaction(Date dateTransaction);

    public Iterable<Transaction> findByUserSenderFirstNameAndUserSenderLastName(String firstName, String lastName);

    public Iterable<Transaction> findByUserReceiverFirstNameAndUserReceiverLastName(String firstName, String lastName);

}
