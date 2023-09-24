package com.commerce.commerce.repository;

import com.commerce.commerce.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    @Override
    Optional<Wallet> findById(Long aLong);
}
