package com.Projet6.PayMyBuddy.paymybuddy.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public String getEmail() {
        return email;
    }

    @Column(name = "email")
    private String email;
}
