package com.Projet6.PayMyBuddy.paymybuddy.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Table(name = "paymybuddyaccount")
public class PayMyBuddyAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "balance")
    private double balance;
}
