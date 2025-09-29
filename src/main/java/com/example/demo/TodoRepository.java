package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {
    // Find all TodoItems for a given user
    List<TodoItem> findByUser(User user);
}