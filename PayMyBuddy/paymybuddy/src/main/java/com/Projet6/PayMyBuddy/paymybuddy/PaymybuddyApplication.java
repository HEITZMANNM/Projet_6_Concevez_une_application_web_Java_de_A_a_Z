package com.Projet6.PayMyBuddy.paymybuddy;

import com.Projet6.PayMyBuddy.paymybuddy.model.User;

import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication
public class PaymybuddyApplication implements CommandLineRunner {

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(PaymybuddyApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Iterable<User> utilisateurs = userService.getUsers();
		utilisateurs.forEach(utilisateur -> System.out.println(utilisateur.getEmail()));
	}
}
