package com.russ.done.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.russ.done.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    
}
