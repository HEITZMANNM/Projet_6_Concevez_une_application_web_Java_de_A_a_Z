package com.Projet6.PayMyBuddy.paymybuddy;

import com.Projet6.PayMyBuddy.paymybuddy.controller.UserController;
import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private User newUser;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @BeforeEach
    public void setUp()
    {
        newUser = new User();
        newUser.setBalance(100);
        newUser.setPassword("newUser");
        newUser.setEmail("newuser@gmail.user");
        newUser.setFirstName("Bernard");
        newUser.setLastName("Dupont");


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
    public void testToGetUserByEmail() throws Exception
    {
        userService.saveUser(newUser);

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
