package com.Projet6.PayMyBuddy.paymybuddy.repository;

import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public Iterable<User> findByEmail(String email);
}
