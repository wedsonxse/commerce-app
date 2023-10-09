package com.commerce.commerce.controllers;

import com.commerce.commerce.entities.User;
import com.commerce.commerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/user")
@Controller
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(this.service.findById(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na busca do usuário: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody User request) {
        try{
            return ResponseEntity.ok(this.service.save(request));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na criação do usuário: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity listAll() throws Exception {
        try{
            return ResponseEntity.ok(this.service.listAll());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na listagem dos usuários: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try{
            return ResponseEntity.ok(this.service.delete(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na deleção do usuário: " + e.getMessage());
        }
    }

}
