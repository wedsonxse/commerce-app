package com.commerce.commerce.services;

import com.commerce.commerce.entities.User;
import com.commerce.commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public Optional<User> findUserById(Long id){
        return this.repository.findById(id);
    }
}
