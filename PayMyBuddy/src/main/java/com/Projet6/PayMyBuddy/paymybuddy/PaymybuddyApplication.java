package com.Projet6.PayMyBuddy.paymybuddy;




import com.Projet6.PayMyBuddy.paymybuddy.model.Transaction;
import com.Projet6.PayMyBuddy.paymybuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class PaymybuddyApplication  {
//	implements CommandLineRunner

//	@Autowired
//	UserService userService;
//
//	@Autowired
//	BankAccountService bankAccountService;
//
//	@Autowired
//	PayMyBuddyAccountService payMyBuddyAccountService;

//	@Autowired
//	TransactionService transactionService;


	public static void main(String[] args) {
		SpringApplication.run(PaymybuddyApplication.class, args);
	}

//	@Override
//	@Transactional
//	public void run(String... args) throws Exception {
//
//		Transaction transaction = new Transaction();
//
//		transaction = transactionService.getTransactions().get(0);
//
//		System.out.println("valeur Date de transaction " + transaction.getDateTransaction());
//		System.out.println("valeur montant de transaction "+transaction.getAmount());
//		System.out.println("valeur description de transaction "+ transaction.getDescription());
//
//
////test to save new paymybuddyaccount
//		PayMyBuddyAccount payMyBuddyAccountToSave = new PayMyBuddyAccount();
//		payMyBuddyAccountToSave.setBalance(1000);
//		List<User>listOfUsers = new ArrayList<>();
////		listOfUsers.add(utilisateur1);
////		payMyBuddyAccountToSave.setUsers(listOfUsers);
//		payMyBuddyAccountService.saveAccount(payMyBuddyAccountToSave);
//
//		System.out.println("valeur du compte pay my buddy ="+payMyBuddyAccountService.findById(1).get().getBalance());
//
//		//test user/bankAccount
////		User utilisateur1 = new User();
////		utilisateur1.setEmail("Dutton@gmail.ye");
////		utilisateur1.setPassword("DD");
////		utilisateur1.setAccount(100);
////		List<BankAccount>listOfBankAccount = new ArrayList<>();
////		BankAccount bankAccountUtilisateur1 = new BankAccount();
////		bankAccountUtilisateur1.setUser(utilisateur1);
////		bankAccountUtilisateur1.setIban("YE75");
////		listOfBankAccount.add(bankAccountUtilisateur1);
////		utilisateur1.setBankAccounts(listOfBankAccount);
////		utilisateur1.setPayMyBuddyAccount(payMyBuddyAccountToSave);
////
////		userService.saveUser(utilisateur1);
////		bankAccountService.saveBankAccount(bankAccountUtilisateur1);
////
////
////		User utilisateur2 = new User();
////		utilisateur2.setEmail("Wolf@gmail.ge");
////		utilisateur2.setPassword("WW");
////		utilisateur2.setAccount(500);
////		List<BankAccount>listOfBankAccountUtil2 = new ArrayList<>();
////		BankAccount bankAccountUtilisateur2 = new BankAccount();
////		bankAccountUtilisateur2.setUser(utilisateur2);
////		bankAccountUtilisateur2.setIban("GE99");
////		listOfBankAccountUtil2.add(bankAccountUtilisateur2);
////		utilisateur2.setBankAccounts(listOfBankAccountUtil2);
////		userService.saveUser(utilisateur2);
////		bankAccountService.saveBankAccount(bankAccountUtilisateur2);
//
//
//		//test relation user friends
////		Iterable<User> utilisateurs = userService.getUsers();
////		Optional<User> utilisateurs1 = userService.getUserById(1);
////		System.out.println("les amis de l'utilisateur 1 sont :"+utilisateurs1.get().getFriends());
////		List<User> friends = utilisateurs1.get().getFriends();
////		User user2 = userService.getUserById(2).get();
////		friends.add(user2);
////		System.out.println("les amis de l'utilisateur 5 sont :"+utilisateurs1.get().getFriends().get(0).getEmail());
//
//
////test relation user bankaccount
////		Iterable<BankAccount> bankAccounts = bankAccountService.getBankAccounts();
////		bankAccounts.forEach(bankAccount -> System.out.println(bankAccount.getIban()));
////		BankAccount bankAccountToSaved = new BankAccount();
////		bankAccountToSaved.setIban("FR75 7777 7777 7777 68");
////		bankAccountToSaved.setUser(utilisateursById.get());
////		bankAccountService.saveBankAccount(bankAccountToSaved);
////
////		bankAccounts = bankAccountService.getBankAccounts();
////		bankAccounts.forEach(bankAccount -> System.out.println(bankAccount.getIban()));





//	}
}
