package com.commerce.commerce.services;

import com.commerce.commerce.repository.UserRepository;
import com.commerce.commerce.repository.WalletRepository;
import com.commerce.commerce.requests.TransactionPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final UserRepository userService;
    private final WalletRepository walletService;

    @Autowired
    public TransactionService(UserRepository userService,WalletRepository walletService){
        this.userService = userService;
        this.walletService = walletService;
    }

    public void performTransaction(TransactionPostRequest request){
        System.out.println("bateu aqui..." + request.getCredits());
    }
}
