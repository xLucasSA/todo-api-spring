package com.lucasabreu.simpletodo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasabreu.simpletodo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
