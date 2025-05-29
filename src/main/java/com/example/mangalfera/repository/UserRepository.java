package com.example.mangalfera.repository;

import com.example.mangalfera.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByContactNumber(String contactNumber);
}
