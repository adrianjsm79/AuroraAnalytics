package com.tecsup.aurora_admin.controller;

import com.tecsup.aurora_admin.model.User;
import com.tecsup.aurora_admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin("*") // Permitir acceso desde cualquier frontend
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/staff")
    public List<User> getStaffUsers() {
        return userService.getStaffMembers();
    }

    @GetMapping("/count")
    public long countUsers() {
        return userService.countActiveUsers();
    }
}