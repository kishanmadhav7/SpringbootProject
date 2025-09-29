package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA will create the query for us based on the method name
    Optional<User> findByUsername(String username);
}