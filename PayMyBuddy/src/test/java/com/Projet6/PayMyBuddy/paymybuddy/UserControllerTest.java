package com.Projet6.PayMyBuddy.paymybuddy;


import com.Projet6.PayMyBuddy.paymybuddy.model.User;

import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private User newUser;

    private User userTest;

    private User userTestFriend;

    private User newFriendForUserTest;

    @Autowired
    private UserService userService;



    @BeforeEach
    public void setUp()
    {
        newUser = new User();
        newUser.setBalance(100);
        newUser.setPassword("newUser");
        newUser.setEmail("newuser@gmail.user");
        newUser.setFirstName("Bernard");
        newUser.setLastName("Dupont");

        userTestFriend = new User();
        userTestFriend.setBalance(200);
        userTestFriend.setPassword("userTestFriend");
        userTestFriend.setEmail("userTestFriend@gmail.user");
        userTestFriend.setFirstName("UserTestFriend");
        userTestFriend.setLastName("UserTestFriend");

        newFriendForUserTest = new User();
        newFriendForUserTest.setBalance(0);
        newFriendForUserTest.setPassword("newFriendForUserTest");
        newFriendForUserTest.setEmail("newFriendForUserTest@gmail.user");
        newFriendForUserTest.setFirstName("newFriendForUserTest");
        newFriendForUserTest.setLastName("newFriendForUserTest");

        userTest = new User();
        userTest.setBalance(200);
        userTest.setPassword("userTest");
        userTest.setEmail("userTest@gmail.user");
        userTest.setFirstName("UserTest");
        userTest.setLastName("UserTest");
        List<User> friends = new ArrayList<>();
        friends.add(userTestFriend);
        userTest.setFriends(friends);

        userService.saveUser(userTestFriend);
        userService.saveUser(userTest);
        userService.saveUser(newFriendForUserTest);
    }

    @AfterEach
    public void clearDataBase()
    {
        userService.deleteUserByFirstNameAndLastName(newUser.getFirstName(), newUser.getLastName());
        userService.deleteUserByFirstNameAndLastName(userTest.getFirstName(), userTest.getLastName());
        userService.deleteUserByFirstNameAndLastName(userTestFriend.getFirstName(), userTestFriend.getLastName());
        userService.deleteUserByFirstNameAndLastName(newFriendForUserTest.getFirstName(), newFriendForUserTest.getLastName());
    }

    @Test
    public void testToSaveANewUser() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/user")
                        .content(asJsonString(newUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testToGetUserByEmail() throws Exception {
        String email = "userTest@gmail.user";
        this.mockMvc.perform(get("/userByEmail").param("email", email))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("UserTest"));
    }

    @Test
    public void testToGetUserById() throws Exception {
        int id = userService.getUserByEmail("userTest@gmail.user").getId();
        this.mockMvc.perform(get("/user").param("id", String.valueOf(id)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("UserTest"));
    }

    @Test
    public void testToGetUserByEmailAndPassword() throws Exception {
        String email = userTest.getEmail();
        String password = userTest.getPassword();
        this.mockMvc.perform(get("/userByEmailAndPassword?email=userTest@gmail.user&password=userTest"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("UserTest"));
    }

    @Test
    public void testToGetUserFriends() throws Exception {
        String email = userTest.getEmail();
        this.mockMvc.perform(get("/userFriends").param("email", email))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").value("UserTestFriend"));
    }

    @Test
    public void testToDeleteUserFriends() throws Exception {
        int id = userTest.getId();
        int idFriend = userTestFriend.getId();
        this.mockMvc.perform(delete("/friend").param("userId", String.valueOf(id))
                        .param("friendId", String.valueOf(idFriend)) )
                .andExpect(status().isOk());
    }

    @Test
    public void testToSaveANewFriend() throws Exception {

        int id = userService.getUserByEmail("userTest@gmail.user").getId();
        String newFriendEmail = "newFriendForUserTest@gmail.user";

        mockMvc.perform( MockMvcRequestBuilders
                        .get("/newFriend").param("userId", String.valueOf(id))
                        .param("friendEmail", newFriendEmail)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }




//    @Test
//    public void testToGetUserByEmail() throws Exception
//    {
//        userService.saveUser(newUser);

//    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
