package com.Projet6.PayMyBuddy.paymybuddy.service;

import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import com.Projet6.PayMyBuddy.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LogManager.getLogger("UserService");

    public List<User> getUsers() throws IOException {

        List<User> userList = new ArrayList<>();

        try {
            Iterable<User> users = userRepository.findAll();

            for (User user : users) {
                userList.add(user);
                logger.debug("The users were find");
            }
        }
        catch(Exception ex){
            logger.error("Error fetching the list of users", ex);
        }
        return userList;
    }


    public User getUserById(int id)
    {
        User user = new User();
        try
        {
            Optional<User> userSearch = userRepository.findById(id);
            user= userSearch.get();
            logger.debug("The user was find");
        }
        catch(Exception ex){
            logger.error("Error fetching user", ex);
        }
        return user;

    }
    public User getUserByEmail(String email)
    {
        User user = new User();
        try
        {
            Optional<User> userSearch = userRepository.findByEmail(email);
            user= userSearch.get();
            logger.debug("The user was find");
        }
        catch(Exception ex){
            logger.error("Error fetching user", ex);
        }
        return user;
    }


    public boolean saveUser(User user)
    {
        boolean answer = false;
        try
        {
            userRepository.save(user);
            answer = true;
            logger.debug("the user was saved");
        }
        catch(Exception ex){
            logger.error("Error save user", ex);
        }
        return answer;
    }

    public void deleteUserById(Integer id)
    {
        userRepository.deleteById(id);
    }

//    public void deleteUserByFirstNameAndLastName(String firstName, String lastName)
//    {
//        Optional<User> optionalUser = userRepository.findByFirstNameAndLastName(firstName, lastName);
//        User userToDelete = optionalUser.get();
//
//        userRepository.deleteById(userToDelete.getId());
//    }

    public void deleteUserByFirstNameAndLastName(String firstName, String lastName)
    {
        userRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }


    public boolean updateUser(User user)
    {
        boolean answer = false;
        try
        {
            Optional<User> optionalExistingUser = userRepository.findByEmail(user.getEmail());
            User existingUser = optionalExistingUser.get();

            existingUser.setBalance(user.getBalance());
            existingUser.setPassword(user.getPassword());
            existingUser.setFriends(user.getFriends());

            userRepository.save(existingUser);
            answer = true;
            logger.debug("the user was update");
        }
        catch(Exception ex){
            logger.error("Error to update user", ex);
        }
        return answer;
    }

    public void addNewFriend(int userId, int friendId)
    {
        User user = this.getUserById(userId);
        List<User> firends = user.getFriends();
        User friend = this.getUserById(friendId);

        firends.add(friend);

        this.updateUser(user);
    }

    public void deleteAFriend(int userId, int friendId)
    {
        User user = this.getUserById(userId);
        List<User> firends = user.getFriends();
        User friend = this.getUserById(friendId);

        firends.remove(friend);

        this.updateUser(user);
    }
}

