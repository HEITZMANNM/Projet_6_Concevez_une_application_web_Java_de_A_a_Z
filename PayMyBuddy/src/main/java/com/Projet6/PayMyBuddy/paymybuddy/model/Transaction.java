package com.Projet6.PayMyBuddy.paymybuddy.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;


@Entity
@DynamicUpdate
@Table(name = "transaction")
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(View.TransactionId.class)
    private int id;

    @Column(name = "datetransaction")
    @JsonView(View.DateTransaction.class)
    private Date dateTransaction;

    @Column(name = "description")
    @JsonView(View.Description.class)
    private String description;

    @Column(name = "amount")
    @JsonView(View.Amount.class)
    private double amount;

    @Column(name = "fees")
    @JsonView(View.Fees.class)
    private double fees;

    @ManyToOne(
    )
    @JoinColumn(name="user_sender_id")
    @JsonView(View.UserSender.class)
    private User userSender;

    @ManyToOne(
    )
    @JoinColumn(name="user_receiver_id ")
    @JsonView(View.UserReceiver.class)
    private User userReceiver;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    public User getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(User userReceiver) {
        this.userReceiver = userReceiver;
    }

}
