package com.quizapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizapp.entities.Quiz;



@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}

