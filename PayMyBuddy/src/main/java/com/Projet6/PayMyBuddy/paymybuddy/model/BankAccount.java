package com.Projet6.PayMyBuddy.paymybuddy.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "bankaccount")
public class BankAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(View.BanaccountId.class)
    private int id;

    @Column(name = "iban")
    @JsonView(View.Iban.class)
    private String iban;

    @Column(name = "bic")
    @JsonView(View.Bic.class)
    private String bic;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(
            mappedBy = "bankaccount"
    )
    List<TransactionBankaccount> transactionBankaccountList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }



}
