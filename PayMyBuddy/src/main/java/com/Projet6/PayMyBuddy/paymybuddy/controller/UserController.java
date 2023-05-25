package com.Projet6.PayMyBuddy.paymybuddy.controller;

import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() throws IOException
    {
        return userService.getUsers();
    }

    @GetMapping("/user")
    public User getUserById(@RequestParam(name = "id") int id)
    {
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    public ResponseEntity<HttpStatus> postNewUser(@RequestBody User user)
    {
        if(userService.saveUser(user))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName)
    {
        userService.deleteUserByFirstNameAndLastName(firstName, lastName);
    }

    @PutMapping("/user")
    public ResponseEntity<HttpStatus>  putUser(@RequestBody User user)
    {
        if(userService.updateUser(user))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }

        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

}
