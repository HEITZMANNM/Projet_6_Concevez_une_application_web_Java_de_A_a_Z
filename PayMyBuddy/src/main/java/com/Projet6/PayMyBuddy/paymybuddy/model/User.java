package com.Projet6.PayMyBuddy.paymybuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(View.UserId.class)
    private int id;

    @Column(name = "email")
    @JsonView(View.Email.class)
    private String email;

    @Column(name = "password")
    @JsonView(View.Password.class)
    private String password;

    @Column(name = "firstName")
    @JsonView(View.FirstName.class)
    private String firstName;

    @Column(name = "lastName")
    @JsonView(View.LastName.class)
    private String lastName;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friends_id"))
    @JsonIgnore
    private List<User> friends = new ArrayList<>();


    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(name = "transaction",
            joinColumns = @JoinColumn(name = "user_sender_id"),
            inverseJoinColumns = @JoinColumn(name = "user_receiver_id"))
    @JsonIgnore
    private List<Transaction> transactionList = new ArrayList<>();

    @OneToMany(
            mappedBy = "user"
    )
    @JsonIgnore
    List<TransactionBankaccount> transactionBankaccountList = new ArrayList<>();


    @Column(name = "balance")
    @JsonView(View.Balance.class)
    private double balance;



    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
