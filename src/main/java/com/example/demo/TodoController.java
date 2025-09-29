package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoController {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoController(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    // Helper method to get the current user
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByUsername(currentPrincipalName)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @GetMapping("/")
    public String index(Model model) {
        User currentUser = getCurrentUser();
        // Only find tasks for the logged-in user
        model.addAttribute("items", todoRepository.findByUser(currentUser));
        model.addAttribute("username", currentUser.getUsername());
        return "index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute TodoItem item) {
        // Associate the new item with the current user
        item.setUser(getCurrentUser());
        todoRepository.save(item);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User currentUser = getCurrentUser();
        TodoItem item = todoRepository.findById(id)
          .filter(todo -> todo.getUser().getId().equals(currentUser.getId())) // Security check
          .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
        
        model.addAttribute("item", item);
        return "edit-form";
    }

    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable("id") long id, @ModelAttribute TodoItem itemDetails) {
        User currentUser = getCurrentUser();
        TodoItem item = todoRepository.findById(id)
            .filter(todo -> todo.getUser().getId().equals(currentUser.getId())) // Security check
            .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));

        item.setTitle(itemDetails.getTitle());
        item.setDueDate(itemDetails.getDueDate());
        todoRepository.save(item);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        User currentUser = getCurrentUser();
        TodoItem item = todoRepository.findById(id)
            .filter(todo -> todo.getUser().getId().equals(currentUser.getId())) // Security check
            .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
        
        todoRepository.delete(item);
        return "redirect:/";
    }

    @GetMapping("/complete/{id}")
    public String complete(@PathVariable Long id) {
        User currentUser = getCurrentUser();
        TodoItem item = todoRepository.findById(id)
            .filter(todo -> todo.getUser().getId().equals(currentUser.getId())) // Security check
            .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));

        item.setCompleted(true);
        todoRepository.save(item);
        return "redirect:/";
    }
}