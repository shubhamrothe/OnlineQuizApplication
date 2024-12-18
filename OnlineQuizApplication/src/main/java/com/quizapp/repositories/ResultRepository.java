package com.quizapp.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizapp.entities.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    
  List<Result> findByUser_UserId(Long userId);


}
