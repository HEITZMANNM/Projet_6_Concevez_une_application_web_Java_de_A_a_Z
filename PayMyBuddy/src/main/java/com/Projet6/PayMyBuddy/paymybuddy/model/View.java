package com.Projet6.PayMyBuddy.paymybuddy.model;

public class View {

    public  interface UserId{};

    public interface Email{};

    public interface Password{};

    public interface FirstName{};

    public interface LastName{};




    public interface Balance{};


    public interface Fees {
    }


    public interface Amount {
    }

    public interface Description {
    }

    public interface DateTransaction {
    }

    public interface TransactionId {
    }

    public interface TransactionBankaccountOrigin {
    }

    public interface TransactionBankaccountDate {
    }

    public interface TransactionBankaccountAmount {
    }

    public interface TransactionBankaccountDescription {
    }

    public interface TransactionBankaccountId {
    }

    public interface Bic {
    }

    public interface Iban {
    }

    public interface BanaccountId {
    }

    public interface UserReceiver {
    }

    public interface UserSender {
    }
    public interface TransactionBankaccountBankAccount {
    }

    public interface UserIdentityOnly extends UserId, FirstName, LastName{};

    public interface TransactionAmountDescriptionAndDateOnly extends UserSender, UserReceiver, Amount, Description, DateTransaction, UserIdentityOnly{};

    public interface TransactionBankaccountAmountDescriptionDateAndIbanOnly extends TransactionBankaccountBankAccount, TransactionBankaccountAmount, TransactionBankaccountDate, TransactionBankaccountDescription, Iban, TransactionBankaccountOrigin, UserIdentityOnly{};

}
