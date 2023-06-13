package com.Projet6.PayMyBuddy.paymybuddy.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicUpdate
@Table(name = "transaction_with_bankaccount")
public class TransactionBankaccount {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(View.TransactionBankaccountId.class)
    private int id;

    @Column(name = "description")
    @JsonView(View.TransactionBankaccountDescription.class)
    private String description;

    @Column(name = "amount")
    @JsonView(View.TransactionBankaccountAmount.class)
    private double amount;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bankaccount_id")
    @JsonView(View.TransactionBankaccountBankAccount.class)
    private BankAccount bankaccount;

    @Column(name = "date")
    @JsonView(View.TransactionBankaccountDate.class)
    private Date date;

    @Column(name = "origin")
    @JsonView(View.TransactionBankaccountOrigin.class)
    private boolean origin;

    public void setBankaccount(BankAccount bankaccount) {
        this.bankaccount = bankaccount;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BankAccount getBankaccount() {
        return bankaccount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getOrigin() {
        return origin;
    }

    public void setOrigin(boolean origin) {
        this.origin = origin;
    }

}
