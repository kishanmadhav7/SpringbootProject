package com.example.demo;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users") // Specify the table name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    
    // A user can have many to-do items
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TodoItem> todoItems;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Set<TodoItem> getTodoItems() { return todoItems; }
    public void setTodoItems(Set<TodoItem> todoItems) { this.todoItems = todoItems; }
}