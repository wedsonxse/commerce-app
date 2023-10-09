package com.commerce.commerce.services;

import com.commerce.commerce.entities.User;
import com.commerce.commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public User findById(Long id) throws Exception {
        User user = this.repository.findById(id).orElse(null);

        if(user == null){
            throw new Exception("Usuário não encontrado!");
        }

        return user;
    }
    public User save(User user) { return this.repository.save(user); }

    public List<User> listAll() {
        try{
            return this.repository.findAll();
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean delete(Long id){
        try{
            this.repository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
