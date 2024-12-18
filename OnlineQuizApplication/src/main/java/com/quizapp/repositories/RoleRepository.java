package com.quizapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizapp.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String string);

}
