package com.Projet6.PayMyBuddy.paymybuddy.repository;

import com.Projet6.PayMyBuddy.paymybuddy.model.Transaction;
import com.Projet6.PayMyBuddy.paymybuddy.model.TransactionBankaccount;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionWithBankAccountRepository extends CrudRepository<TransactionBankaccount, Integer> {

    public Iterable<TransactionBankaccount> findAll();


    public Iterable<TransactionBankaccount> findByUserId(int userId);


}
