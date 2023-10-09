package com.commerce.commerce.repository;

import com.commerce.commerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    Optional<User> findById(@Param("userId") Long userId);
    @Override
    User save(User entity);

    @Override
    void deleteById(Long aLong);

    @Override
    List<User> findAll();

}
