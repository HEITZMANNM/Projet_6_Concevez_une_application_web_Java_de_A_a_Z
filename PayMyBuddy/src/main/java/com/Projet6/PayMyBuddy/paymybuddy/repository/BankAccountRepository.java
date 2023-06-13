package com.Projet6.PayMyBuddy.paymybuddy.repository;

import com.Projet6.PayMyBuddy.paymybuddy.model.BankAccount;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {

    public Iterable<BankAccount> findAll();
    public Optional<BankAccount> findByIban(String iban);

    public Iterable<BankAccount> findByUserId(int userId);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteByIban(String iban);

}
