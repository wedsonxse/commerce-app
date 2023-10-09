package com.commerce.commerce.controllers;

import com.commerce.commerce.dto.TransactionDTO;
import com.commerce.commerce.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/transaction")
@RestController
public class TransactionController {
    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity executeTransaction(@RequestBody TransactionDTO entity) throws Exception {
        try{
            this.service.performTransaction(entity);
            return ResponseEntity.ok("Transação efetuada com sucesso!");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na execução da transação:" + e.getMessage());
        }
    }
}
