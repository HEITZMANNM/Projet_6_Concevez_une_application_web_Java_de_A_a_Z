package com.Projet6.PayMyBuddy.paymybuddy.integration;

import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.repository.UserRepository;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import org.junit.jupiter.api.AfterEach;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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

    @AfterEach
    public void clearDataBase()
    {
        userService.deleteUserByFirstNameAndLastName(newUser.getFirstName(), newUser.getLastName());
    }

    @Test
    public void testToSaveANewUser()
    {
//saved the new user (method tested)
        userService.saveUser(newUser);

        //get the user in the database
        User userSaved = userService.getUserByEmail("newuser@gmail.user");

        //assert that the user was correctly saved, and that we have access to his informations
        assertEquals(userSaved.getFirstName(), "Bernard");
        assertEquals(userSaved.getLastName(), "Dupont");

    }

    @Test
    public void testToDeleteAUserById()
    {
        //save the new user
        userService.saveUser(newUser);

        //get in the database this new user
        User userSaved = userService.getUserByEmail("newuser@gmail.user");

        //delete this user (method tested)
        userService.deleteUserById(userSaved.getId());

        //try to retake him in the database
        User userSavedAfterDelete = userService.getUserByEmail("newuser@gmail.user");

        //assert that the recovered user is empty
        assertEquals(userSavedAfterDelete.getEmail(), null);
        assertEquals(userSavedAfterDelete.getId(), 0);


    }

    @Test
    public void testToUpdateAUser()
    {
        //save the new user
        userService.saveUser(newUser);

        //set modifications to the newUser's balance
        newUser.setBalance(300);

        //update the newUser with the new balance (method tested)
        userService.updateUser(newUser);

        //get the newUser updated
       User newUserUpdated = userService.getUserByEmail("newuser@gmail.user");

       //assert that the newUser's balance was correctly updated
        assertEquals(newUserUpdated.getBalance(), 300);

    }



}
