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

    public Optional<User> findByFirstName(String firstName);

   public Optional<User> findByLastName(String lastName);

    public Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    public void deleteById(int id);


//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Modifying
//    @Query(value = "DELETE FROM user WHERE first_name =:firstname AND last_name=:lastname", nativeQuery = true)
//    public void deleteByFirstNameAndLastName(@Param("firstname") String firstName, @Param("lastname") String lastName);


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteByFirstNameAndLastName(String firstName, String lastName);


}
