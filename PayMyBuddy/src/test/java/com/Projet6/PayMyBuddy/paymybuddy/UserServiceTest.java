package com.Projet6.PayMyBuddy.paymybuddy;

import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.repository.UserRepository;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock(lenient = true)
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserService();

    private User userOne;
    private User userTwo;



    public void setUpListOfAllUser()
    {
        List<User> listOfAllUser = new ArrayList<>();

        userOne = new User();
        userOne.setEmail("Dutton@gmail.ye");
        userOne.setPassword("DD");
        userOne.setBalance(100);
        userOne.setId(1);

        userTwo = new User();
        userTwo.setEmail("Wolf@gmail.ge");
        userTwo.setPassword("WW");
        userTwo.setBalance(500);
        userTwo.setId(2);

        listOfAllUser.add(userOne);
        listOfAllUser.add(userTwo);

        when(userRepository.findAll()).thenReturn(listOfAllUser);
        when(userRepository.findById(1)).thenReturn(Optional.of(userOne));


    }

    public void setUpFriends()
    {
        userOne = new User();
        userOne.setEmail("Dutton@gmail.ye");
        userOne.setPassword("DD");
        userOne.setBalance(100);
        userOne.setId(1);

        userTwo = new User();
        userTwo.setEmail("Wolf@gmail.ge");
        userTwo.setPassword("WW");
        userTwo.setBalance(500);
        userTwo.setId(2);

        List<User>friends = new ArrayList<>();
        friends.add(userTwo);
        userOne.setFriends(friends);



        when(userRepository.findById(1)).thenReturn(Optional.of(userOne));
        when(userRepository.findByEmail("Wolf@gmail.ge")).thenReturn(Optional.of(userTwo));
        when(userRepository.findByEmail("Dutton@gmail.ye")).thenReturn(Optional.ofNullable(userOne));

    }

    //test to get all users
    @Test
    public void testToGetUsers() throws IOException
    {
        setUpListOfAllUser();
        List<User> listOfAllUser = userService.getUsers();

        assertEquals(listOfAllUser.size(), 2);
        assertEquals(listOfAllUser.get(0).getEmail(), "Dutton@gmail.ye");
    }

//test to add new friend
    @Test
    public void testToAddNewFriend()
    {
        setUpFriends();
        userService.addNewFriend(1, "Wolf@gmail.ge");

        assertEquals(userOne.getFriends().get(0).getPassword(), "WW");
    }

    @Test
    public void testFindUserById()
    {
        setUpListOfAllUser();
        userService.getUserById(1);

        assertEquals(userOne.getEmail(), "Dutton@gmail.ye");
    }

    @Test
    public void testFindUserByEmail()
    {
        setUpListOfAllUser();
        userService.getUserByEmail("Dutton@gmail.ye");

        assertEquals(userOne.getPassword(), "DD");
    }

    @Test
    public void testToGetUserFriends()
    {
        setUpFriends();
       List<User> listOfFirendsFind =  userService.getUsersFriends("Dutton@gmail.ye");

        assertEquals(listOfFirendsFind.size(), 1);
        assertEquals(listOfFirendsFind.get(0).getEmail(), "Wolf@gmail.ge");
    }



}
