package com.tecsup.aurora_admin.service;

import com.tecsup.aurora_admin.model.User;
import com.tecsup.aurora_admin.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getStaffMembers() {
        // Devuelve solo los administradores/soporte
        return userRepository.findByIsStaffTrue();
    }
    
    // Método analítico simple
    public long countActiveUsers() {
        return userRepository.count(); // O findByIsActiveTrue().size()
    }
}