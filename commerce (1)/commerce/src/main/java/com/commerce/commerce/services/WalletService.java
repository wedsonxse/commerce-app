package com.commerce.commerce.services;

import com.commerce.commerce.entities.Wallet;
import com.commerce.commerce.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository repository;
    @Autowired
    public WalletService(WalletRepository repository){
        this.repository = repository;
    }

    public Optional<Wallet> findWalletById(Long id){
        return this.repository.findById(id);
    }
}
