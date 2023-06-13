package com.Projet6.PayMyBuddy.paymybuddy.model;



public class TransactionBankaccountDTO {

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


    public BankAccount getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(BankAccount bankaccount) {
        this.bankaccount = bankaccount;
    }


    public boolean getOrigin() {
        return origin;
    }

    public void setOrigin(boolean origin) {
        this.origin = origin;
    }


    private String description;

    private double amount;

    private BankAccount bankaccount;

    private boolean origin;
}
