package com.Projet6.PayMyBuddy.paymybuddy.repository;


import com.Projet6.PayMyBuddy.paymybuddy.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public Iterable<User> findAll();

    public Optional<User> findById(int id);
    public Optional<User> findByEmail(String email);

    public Optional<User> findByEmailAndPassword(String email, String password);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteByFirstNameAndLastName(String firstName, String lastName);


}
