package com.quizapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
    Optional<User> findByEmail(String email);
    
    Optional<User> findByUserName(String username);
    
}