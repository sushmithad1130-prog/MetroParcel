package com.metroparcel.service;

import org.springframework.stereotype.Service;

import com.metroparcel.model.User;
import com.metroparcel.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User register(User user) {

        if (repository.existsByEmail(user.getEmail())) {

            throw new RuntimeException(
                    "An account already exists with this email."
            );
        }

        user.setRole("CUSTOMER");

        return repository.save(user);
    }

    public User login(String email, String password) {

        User user = repository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found."
                        )
                );

        if (!user.getPassword().equals(password)) {

            throw new RuntimeException(
                    "Incorrect password."
            );
        }

        return user;
    }
}