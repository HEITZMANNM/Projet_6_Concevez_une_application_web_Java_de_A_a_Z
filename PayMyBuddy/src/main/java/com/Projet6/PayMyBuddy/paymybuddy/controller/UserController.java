package com.Projet6.PayMyBuddy.paymybuddy.controller;

import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.model.View;
import com.Projet6.PayMyBuddy.paymybuddy.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  @Autowired
  private UserService userService;


    @GetMapping("/userByEmail")
    @CrossOrigin(origins = "http://localhost:4200")
    public User getUserByEmail(@RequestParam(name = "email") String email)
    {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/user")
    @CrossOrigin(origins = "http://localhost:4200")
    public User getUserById(@RequestParam(name = "id") int id)
    {
        return userService.getUserById(id);
    }

    @GetMapping("/userByEmailAndPassword")
    @CrossOrigin(origins = "http://localhost:4200")
    public User getUserByEmailAndPassword(@RequestParam(name = "email") String email, @RequestParam(name ="password") String password)
    {
      return userService.getUserByEmailAndPassword(email, password);
    }
    @JsonView(View.UserIdentityOnly.class)
    @GetMapping("/userFriends")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<User> getUsersFriends(@RequestParam(name = "email") String email)
    {
        return userService.getUsersFriends(email);
    }

    @PostMapping("/user")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<HttpStatus> postNewUser(@RequestBody User user)
    {
        if(userService.saveUser(user))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/friend")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteFriend(@RequestParam(name = "userId") int userId, @RequestParam(name = "friendId") int friendId)
    {
        userService.deleteAFriend(userId, friendId);
    }

    @GetMapping("/newFriend")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<HttpStatus> postNewFriend(@RequestParam(name = "userId") int userId, @RequestParam(name = "friendEmail") String friendEmail )
    {
        if(userService.addNewFriend(userId, friendEmail))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}
