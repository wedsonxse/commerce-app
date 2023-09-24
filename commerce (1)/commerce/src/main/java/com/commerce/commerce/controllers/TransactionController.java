package com.commerce.commerce.controllers;

import com.commerce.commerce.requests.TransactionPostRequest;
import com.commerce.commerce.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void executeTransaction(@RequestBody TransactionPostRequest entity) {
        this.service.performTransaction(entity);
    }
}
