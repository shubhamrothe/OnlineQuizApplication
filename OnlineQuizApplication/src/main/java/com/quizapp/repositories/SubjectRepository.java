package com.quizapp.repositories;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quizapp.entities.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, String> {

    @Transactional
    public int deleteByName(String name);
    
    public boolean existsByName(String name);
}

