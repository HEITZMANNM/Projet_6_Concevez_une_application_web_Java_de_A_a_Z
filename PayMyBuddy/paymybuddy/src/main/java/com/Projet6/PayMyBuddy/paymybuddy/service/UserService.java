package com.Projet6.PayMyBuddy.paymybuddy.service;

import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository utilisateurRepository;

    public Iterable<User> getUsers() {
        return utilisateurRepository.findAll();
    }
}

