package com.Projet6.PayMyBuddy.paymybuddy.integration;

import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.repository.UserRepository;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceIT {

//    @Autowired
//    private static UserRepository userRepository;

    @Autowired
    private static UserService userService;

    private User newUser;

    @BeforeEach
    public void setUp()
    {
        newUser = new User();
        newUser.setBalance(100);
        newUser.setPassword("newUser");
        newUser.setEmail("newuser@gmail.user");
        newUser.setFirstName("Bernard");
        newUser.setLastName("Dupont");

//        userService = new UserService();

//        userService.setUserRepository(userRepository);
    }

    @Test
    public void testToSaveANewUser()
    {
//        userService.setUserRepository(userRepository);
        userService.saveUser(newUser);

        User userSaved = userService.getUserByEmail("newuser@gmail.user");

        assertEquals(userSaved.getFirstName(), "Bernard");
        assertEquals(userSaved.getLastName(), "Dupont");

    }



}
